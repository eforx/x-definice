package org.xdef.impl.util.gencollection;

import org.xdef.XDConstants;
import org.xdef.msg.XDEF;
import org.xdef.sys.SBuffer;
import org.xdef.sys.SRuntimeException;
import org.xdef.sys.SUtils;
import org.xdef.xml.KXmlUtils;
import org.xdef.XDBuilder;
import org.xdef.XDConstants;
import org.xdef.XDFactory;
import org.xdef.XDPool;
import org.xdef.impl.compile.XScriptMacro;
import org.xdef.impl.compile.XScriptMacroResolver;
import org.xdef.impl.compile.XScriptParser;
import org.xdef.sys.ArrayReporter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TreeMap;
import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/** Generate collection from X-definitions.
 * @author Vaclav Trojan
 */
public class XDGenCollection {

	/** The created document */
	private final Document _doc;
	/** Root element of collection. */
	private Element _collection;
	/** XML parser. */
	private XdParser _xdParser;
	/** List of names of X-definitions. */
	private final ArrayList<String> _defNames;
	/** List of sources. */
	private final ArrayList<String> _includeList;
	/** List of parsed sources */
	private final ArrayList<String> _parsedList;
	/** List of thesaurus. */
	private final ArrayList<Element> _thesaurusList;
	/** List of macro definitions. */
	private final HashMap<String, XScriptMacro> _macros;
	/** Actual name space URI of X-definition. */
//	private String _xdNS;

	private static final SAXParserFactory SPF = SAXParserFactory.newInstance();

	static {
		try {
			SPF.setNamespaceAware(true);
			SPF.setXIncludeAware(true);
			SPF.setValidating(false);
			SPF.setFeature("http://xml.org/sax/features/namespaces", true);
			SPF.setFeature("http://xml.org/sax/features/namespace-prefixes",
				false);
			SPF.setFeature("http://apache.org/xml/features/allow-java-encodings",
				true);
			SPF.setFeature("http://xml.org/sax/features/string-interning",
				true);
			SPF.setFeature("http://apache.org/xml/features/xinclude", true);
			SPF.setFeature(
				"http://apache.org/xml/features/xinclude/fixup-base-uris",
				false); // do not create xml:base attributes
			SPF.setSchema(null);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/** Private XML parser reads X-definitions or collections. */
	private class XdParser extends DefaultHandler {
		private Element _element;
		private Element _root;
		Document _doc;

		private final String _pathname;
		private final StringBuilder _text = new StringBuilder();
		public final Map<String, String> _prefixes =
			new TreeMap<String, String>();

		XdParser(Document doc, String pathname) throws Exception {
			_pathname = pathname;
			_doc = doc;
			_includeList.add(pathname);
			_element = null;
			_root = null;
		}
////////////////////////////////////////////////////////////////////////////////
		private void addText() {
			if (_text.length() > 0) {
				String s = _text.toString().trim();
				if (!s.isEmpty()) {
					_element.appendChild(_doc.createTextNode(s));
				}
				_text.setLength(0);
			}
		}

		@Override
		public void startElement(final String uri,
			final String localName,
			final String qName,
			final Attributes atts) throws SAXException {
			Element el = _doc.createElementNS(uri, qName);
			if (_root == null) {
				_element = _root = el;
				_text.setLength(0);
			} else {
				addText();
				_element.appendChild(el);
				_element = el;
			}
			for (int i = 0; i < atts.getLength(); i++) {
				el.setAttributeNS(atts.getURI(i),
					atts.getQName(i), atts.getValue(i));
			}
			for (Map.Entry<String, String> x: _prefixes.entrySet()) {
				String name = x.getKey();
				name = !name.isEmpty() ? "xmlns:" + name : "xmlns";
				if (!el.hasAttribute(name)) {
					el.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
						name, x.getValue());
				}
			}
			_prefixes.clear();
		}
		@Override
		public void endElement(final String uri,
			final String localName,
			final String qName) throws SAXException {
			addText();
			Node n = _element.getParentNode();
			_element = n != null && n.getNodeType() == Node.ELEMENT_NODE
				? (Element) n : null;
		}

		@Override
		public void characters(final char[] ch,
			final int start,
			final int length) throws SAXException {
			_text.append(String.valueOf(ch, start, length));
		}

		@Override
		public void ignorableWhitespace(final char[] ch,
			final int start,
			final int length) throws SAXException {
			_text.append(String.valueOf(ch, start, length));
		}

		@Override
		public void startPrefixMapping(String prefix, String uri) throws
			SAXException {
			_prefixes.put(prefix, uri);
		}

		private void parseDocument() {
			try {
				InputSource is;
				if (_pathname.charAt(0) == '<') {
					is = new InputSource(new ByteArrayInputStream(
						_pathname.getBytes(Charset.forName("UTF-8"))));
					is.setSystemId("STRING");
				} else {
					is = new InputSource(_pathname);
				}
				SAXParser parser = SPF.newSAXParser();
				parser.parse(is, this);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}

	}

	private XDGenCollection() {
		_defNames = new ArrayList<String>();
		_includeList = new ArrayList<String>();
		_parsedList = new ArrayList<String>();
		_thesaurusList = new ArrayList<Element>();
		_macros = new HashMap<String, XScriptMacro>();
		_xdParser = null;
		_doc = KXmlUtils.newDocument();
	}

	private Element importDefinition(Node node) {
		if (node == null) {
			return null;
		}
		if (node.getNodeType() == Node.COMMENT_NODE) {
			_collection.appendChild(node.cloneNode(true)); //we append coment
			return null;
		}
		if (node.getNodeType() != Node.ELEMENT_NODE) {
			return null;
		}
		String uri = getXDNodeNS(node);
		if (uri == null) {
			return null;
		}
		Element el = (Element) node.cloneNode(true);
		if ("thesaurus".equals(el.getLocalName())) {
			_thesaurusList.add(el);
			_collection.appendChild(el);
		} else if ("def".equals(el.getLocalName())) {
			String root = getXdefAttr(el, uri, "root", true);
			if (root.length() > 0) {
				el.setAttribute("root", root); //canonize root attribute.
			}
			String name = getXdefAttr(el, uri, "name", true);
			if (name.length() > 0) {
				el.setAttribute("name", name); //canonize name attribute.
			}
			String s = getXdefAttr(el, uri, "messages", true);
			if (s.length() > 0) {
				el.setAttribute("messages", s); //canonize name attribute.
			}
			if (_defNames.indexOf(name) >= 0) {
				return null; //X-definition exists
			}
			_defNames.add(name);
			_collection.appendChild(el);
			return el;
		}
		return null;
	}

	private void parse(File[] files) throws Exception {
		if (files == null) {
			return;
		}
		for (int i = 0; i < files.length; i++) {
			parse(files[i]);
		}
	}

	private void parse(File file) throws Exception {
		parse(file.toURI().toURL());
	}

	private void parse(URL u) throws Exception {
		parse(u.toExternalForm());
	}

	private void parse(URL[] urls) throws Exception {
		for (int i = 0; i < urls.length; i++) {
			parse(urls[i].toExternalForm());
		}
	}

	private void parse(String[] sources) throws Exception {
		if (sources == null) {
			return;
		}
		for (int i = 0; i < sources.length; i++) {
			parse(sources[i]);
		}
	}

	private void genCollection(final String uri) {
		_collection = _doc.createElementNS(uri, "xd:collection");
		_doc.appendChild(_collection);
		_collection = _doc.getDocumentElement();
		_collection.setAttribute("xmlns:xd", uri);
	}

	private void parse(String source) throws Exception {
		if (source == null) {
			return;
		}
		if (source == null || _parsedList.indexOf(source) >= 0) {
			return;
		}
		_xdParser = new XdParser(_doc, source);
		_xdParser.parseDocument();
		Element root = _xdParser._root;
		String sourcePath;
		if (source.startsWith("<")) {
			sourcePath = "";
		} else {
			String file = "file:/";
			sourcePath = source.startsWith(file)
				? source.substring(file.length()): source;
			File f = new File(sourcePath);
			sourcePath = f.getParentFile().getAbsolutePath();
			sourcePath += File.separator;
		}
		String uri = getXDNodeNS(root);
		if ("collection".equals(root.getLocalName())
			&& (XDConstants.XDEF20_NS_URI.equals(uri)
			|| XDConstants.XDEF31_NS_URI.equals(uri))) {
			if (_collection == null) {
				genCollection(uri);
			}
			processIncludeList(root, sourcePath);
			NodeList nl = root.getChildNodes();
			if (nl != null && nl.getLength() > 0) {
				for (int j = 0, len = nl.getLength(); j < len; j++) {
					Element def = importDefinition(nl.item(j));
					if (def != null) {
						processIncludeList(def, sourcePath);
						_collection.appendChild(def);
					}
				}
			}
		} else {
			if (!XDConstants.XDEF20_NS_URI.equals(uri)
				&& !XDConstants.XDEF31_NS_URI.equals(uri)) {
				uri = XDConstants.XDEF20_NS_URI;
			}
			if (_collection == null && _doc.getDocumentElement() == null) {
				genCollection(uri);
			}
			processIncludeList(root, sourcePath);
			_collection.appendChild(root);
		}
		_parsedList.add(source);
	}

	/** Process include list from header of X-definition. */
	private void processIncludeList(Element def, String sourcePath) {
		/** let's check some attributes of X-definition.*/
		String include =
			getXdefAttr(def, def.getNamespaceURI(), "include", true);
		if (include.length() == 0) {
			return;
		}
		StringTokenizer st = new StringTokenizer(include, " \t\n\r\f,");
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			if (s.startsWith("https:") || s.startsWith("http:")
				|| s.startsWith("ftp:") || s.startsWith("file:")) {
				try {
					URL url = new URL(URLDecoder.decode(s, "UTF-8"));
					if (_includeList.contains(url.toExternalForm())) {
						continue;
					}
					_includeList.add(url.toExternalForm());
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			} else {
				File[] list = SUtils.getFileGroup(sourcePath + s);
				for (int i = 0; list != null && i < list.length; i++) {
					try {
						String fname = list[i].getCanonicalPath();
						if (list[i].canRead() && !_includeList.contains(fname)){
							_includeList.add(fname);
						}
					} catch (Exception ex) {} // igore
				}
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////
	// static methods
	////////////////////////////////////////////////////////////////////////////

	/** Check if given node is XDEF element.
	 * @param n node to be inspected.
	 * @return <tt>true</tt> if node is element with X-definition namespace.
	 */
	public static boolean isXdefElement(final Node n) {
		String uri = n.getNamespaceURI();
		if (n.getNodeType() != Node.ELEMENT_NODE
			|| uri == null || uri.length() == 0) {
			return false;
		}
		String xdUri =
			n.getOwnerDocument().getDocumentElement().getNamespaceURI();
		return uri.equals(xdUri);
	}

	/** Check if given node is the XDEF element.
	 * @param n node to be inspected.
	 * @param name required name of element.
	 * @return <tt>true</tt> if node is element with X-definition name space.
	 */
	public static boolean isXdefElement(final Node n, final String name) {
		return name.equals(n.getLocalName()) && isXdefElement(n);
	}

	/** Check XDEF attribute.
	 * @param el element to be inspected.
	 * @param xdUri name space URI of X-definition.
	 * @param name name of attribute
	 * @return value of attribute or an empty string.
	 */
	public static boolean hasXdefAttr(final Element el,
		final String xdUri,
		final String name) {
		return el.hasAttribute(name) || el.hasAttributeNS(xdUri, name);
	}

	/** Get xdef attribute from an element.
	 * @param el element from which an attribute should be taken.
	 * @param xdUri name space URI of X-definition.
	 * @param name name of attribute
	 * @param remove if <tt>true</tt> then the attribute is removed.
	 * @return value of attribute or an empty string.
	 */
	public static String getXdefAttr(final Element el,
		final String xdUri,
		final String name,
		final boolean remove) {
		String result = "";
		if (el.hasAttribute(name)) {
			result = el.getAttribute(name).trim();
			if (remove) {
				el.removeAttribute(name);
			}
		} else if (el.hasAttributeNS(xdUri, name)) {
			result = el.getAttributeNS(xdUri, name).trim();
			if (remove) {
				el.removeAttributeNS(xdUri, name);
			}
		}
		return result;
	}

	/** Reads all macros to the table macros. If parameter resolve is specified
	 * macros are expanded and macro definitions are removed from collection.
	 * @param collection Collection of X-definitions.
	 * @param macros HashMap with macros.
	 * @param resolve swith if macros will be expanded and removed.
	 */
	private static void processMacros(final Element collection,
		final HashMap<String, XScriptMacro> macros,
		final boolean resolve) {
/**/
		NodeList nl = collection.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);
			if (n.getNodeType() != Node.ELEMENT_NODE
				|| !"def".equals(n.getLocalName())
				|| n.getNamespaceURI() == null) {
				continue;
			}
			Element def = (Element) n;
			String xdUri = def.getNamespaceURI();
			NodeList macs = KXmlUtils.getChildElementsNS(def, xdUri, "macro");
			int len;
			if (macs == null || (len = macs.getLength()) == 0) {
				continue;
			}
			String defName = getXdefAttr(def, xdUri, "name", false);
			for (int j = 0; j < len; j++) {
				Element macro = (Element) macs.item(j);
				Node macNameNode = macro.getAttributeNodeNS(xdUri, "name");
				if (macNameNode == null) {
					macNameNode = macro.getAttributeNode("name");
				}
				String macName =
					macNameNode == null ? "?" : macNameNode.getNodeValue();
				HashMap<String, String> params = new HashMap<String, String>();
				NamedNodeMap nm = macro.getAttributes();
				for (int k = 0; k < nm.getLength(); k++) {
					if ((n = nm.item(k)) != macNameNode) {
						params.put(n.getNodeName(), n.getNodeValue());
					}
				}
				SBuffer v = new SBuffer(KXmlUtils.getTextContent(macro).trim());
				XScriptMacro m =
						new XScriptMacro(macName, defName, params, v , null);
				if (macros.containsKey(m.getName())) {
					//Macro '&{0}' redefinition
					throw new SRuntimeException(XDEF.XDEF482, m.getName());
				} else {
					macros.put(m.getName(), m);
				}
			}
		}
		if (resolve) {
			for (int i = 0; i < nl.getLength(); i++) {
				Element def = (Element) nl.item(i);
				String xdUri = def.getNamespaceURI();
				NodeList macs =
					KXmlUtils.getChildElementsNS(def, xdUri, "macro");
				int len;
				if (macs != null && (len = macs.getLength()) > 0) {
					for (int j = 0; j < len; j++) {
						def.removeChild(macs.item(j));
					}
				}
			}
			for (int i = 0; i < nl.getLength(); i++) {
				Element def = (Element) nl.item(i);
				String defName =
					getXdefAttr(def, def.getNamespaceURI(), "name", false);
				expandMacros(def, defName, macros);
			}
		}
/**/
		}

	/** Expand all macros in given element and its attributes and child nodes.
	 * @param el Element in which macros are expanded.
	 * @param defName name of actual X-definition.
	 * @param macros HashMasp with macros.
	 */
	private static void expandMacros(final Element el,
		final String defName,
		final HashMap<String, XScriptMacro> macros) {
		XScriptMacroResolver mr = new XScriptMacroResolver(defName,
			"1.1".equals(el.getOwnerDocument().getXmlVersion())
				? (byte) 11 : (byte) 10,
			macros,
			new ArrayReporter());
		NodeList nl = el.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);
			switch (n.getNodeType()) {
				case Node.CDATA_SECTION_NODE: {
					CDATASection c = (CDATASection) n;
					SBuffer sb = new SBuffer(c.getData());
					mr.expandMacros(sb);
					c.setData(sb.getString());
					continue;
				}
				case Node.TEXT_NODE: {
					Text c = (Text) n;
					SBuffer sb = new SBuffer(c.getData());
					mr.expandMacros(sb);
					c.setData(sb.getString());
					continue;
				}
				case Node.ELEMENT_NODE:
					expandMacros((Element) n, defName, macros);
			}
		}
		NamedNodeMap nm = el.getAttributes();
		for (int i = 0; nm != null && i < nm.getLength(); i++) {
			Attr a = (Attr) nm.item(i);
			SBuffer sb = new SBuffer(a.getValue());
			mr.expandMacros(sb);
			a.setValue(sb.getString());
		}
	}

	/** Canonize script and generate type table.
	 * @param script script source.
	 * @param defName name of actual X-definition.
	 * @param removeActions if true all actions except validation are removed.
	 * @param isValue if true the script describes a value of an attribute or
	 * of a text node.
	 * @return canonized script.
	 */
	static String canonizeScript(final String script,
		final String defName,
		final boolean removeActions,
		final boolean isValue) {
		XScriptParser sp = new XScriptParser((byte) 10);
		SBuffer sb = new SBuffer(script.trim());
		sp.setSource(sb, defName, XDConstants.XD20_ID);
		XDParsedScript xp = new XDParsedScript(sp, isValue);
		return xp.getCanonizedScript(removeActions);
	}

	/** Changes all XD:text elements to text nodes.
	 * @param el inspected element
	 * @param xdUri name space of X-definitions.
	 * @param defName name of actual X-definition.
	 * @param removeActions if true all actions except validation are removed.
	 */
	private static void canonizeXDText(final Element el,
		final String xdUri,
		final String defName,
		final boolean removeActions) {
		Document doc = el.getOwnerDocument();
		if (!xdUri.equals(el.getNamespaceURI())) {
			NamedNodeMap nm = el.getAttributes();
			for (int i = 0; nm != null && i < nm.getLength(); i++) {
				Attr a = (Attr) nm.item(i);
				if (!a.getName().startsWith("xmlns")) {
					boolean isValue = !"script".equals(a.getLocalName())
						|| !xdUri.equals(a.getNamespaceURI());
					String s = canonizeScript(a.getValue(),
						defName, removeActions, isValue);
					a.setValue(s);
				}
			}
		}
		NodeList nl = el.getChildNodes();
		for (int i = nl.getLength() - 1; i >= 0; i--) {
			Node n = nl.item(i);
			if (n.getNodeType() == Node.COMMENT_NODE) {
				n.removeChild(n);
			} else if (n.getNodeType() == Node.CDATA_SECTION_NODE) {
				CDATASection c = (CDATASection) n;
				Text t = n.getOwnerDocument().createTextNode(c.getData());
				el.replaceChild(t, n);
				n = t;
			}
			if (n.getNodeType() == Node.TEXT_NODE) {
				Text c = (Text) n;
				String s = c.getData().trim();
				if (s.length() == 0) {
					el.removeChild(c);
				} else {
					c.setData(s);
				}
			}
		}
		nl = el.getChildNodes();
		for (int i = nl.getLength() - 1; i >= 0; i--) {
			Node n = nl.item(i);
			if (i > 0 && n.getNodeType() == Node.TEXT_NODE) {
				Node nn = nl.item(i - 1);
				while (nn.getNodeType() == Node.TEXT_NODE) {
					String s = n.getNodeValue();
					if (s.trim().length() > 0) {
						((Text) nn).setData(nn.getNodeValue() + s);
					}
					el.removeChild(nn);
					if (--i > 0) {
						nn = nl.item(i - 1);
					} else {
						break;
					}
				}
			}
		}
		nl = el.getChildNodes();
		for (int i = nl.getLength() - 1; i >= 0; i--) {
			Node n = nl.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) nl.item(i);
				if (xdUri.equals(e.getNamespaceURI())) {
					if ("text".equals(e.getLocalName())
						&& xdUri.equals(e.getNamespaceURI())) {
						String s = hasXdefAttr(e, xdUri, "script") ?
							getXdefAttr(e, xdUri, "script", true)
							: KXmlUtils.getTextValue(e);
						s = canonizeScript(s, defName, removeActions, true);
						if (s.length() == 0) {
							s = "required string()";
						}
						Element txtEl = doc.createElementNS(xdUri, "xd:text");
						txtEl.appendChild(doc.createTextNode(s));
						el.replaceChild(txtEl, e);
						continue;
					} else if ("sequence".equals(e.getLocalName())
							|| "mixed".equals(e.getLocalName())
							|| "choice".equals(e.getLocalName())
							|| "list".equals(e.getLocalName())) {
						String s = getXdefAttr(e, xdUri, "empty", true);
						if (s.length() > 0) {
							if ((s = s.trim()).equals("true")) {
								e.setAttribute("empty", s.trim());
							}
						}
					}
				}
				canonizeXDText(e, xdUri, defName, removeActions);
			} else if (n.getNodeType() == Node.TEXT_NODE) {
				if (!("declaration".equals(el.getLocalName())
					|| "component".equals(el.getLocalName())
					|| "BNFGrammar".equals(el.getLocalName())
					|| "thesaurus".equals(el.getLocalName()))
					|| !xdUri.equals(el.getNamespaceURI())) {
					Text txt = (Text) n;
					String s = ((Text) n).getData();
					if ("thesaurus".equals(el.getLocalName())) {
						if (s.trim().isEmpty()) {
							el.removeChild(n);
						}
					} else {
						s = canonizeScript(s,
							defName, removeActions, true);
						if (s.length() > 0) {
							Element txtEl = doc.createElementNS(xdUri, "xd:text");
							txtEl.appendChild(doc.createTextNode(s));
							el.replaceChild(txtEl, txt);
						} else {
							el.removeChild(txt);
						}
					}
				}
			}
		}
	}

	/** Changes all XD:text elements to text nodes.
	 * @param xdef element with a X-definition.
	 * @param removeActions if true all actions except validation are removed.
	 * @param genModelVariants if true generate alternate models if in the
	 * reference there exists an attribute redefining type or occurrence
	 * (important for XML schema generation).
	 */
	public static void canonizeXDefinition(final Element xdef,
		final boolean removeActions,
		final boolean genModelVariants) {
		NodeList nl = KXmlUtils.getChildElements(xdef);
		String xdUri = xdef.getNamespaceURI();
		String defName = xdef.hasAttribute("name") ? xdef.getAttribute("name")
			: xdef.getAttributeNS(xdUri,"name");
		for (int i = 0; i < nl.getLength(); i++) {
			canonizeXDText((Element) nl.item(i), xdUri, defName, removeActions);
		}
	}

	/** Reads all X-definitions in collection and changes XD:text elements
	 * to text nodes.
	 * @param collection Collection of X-definitions.
	 * @param removeActions if true all actions except validation are removed.
	 * @param genModelVariants if true generate alternate models if in the
	 * reference there exists an attribute redefining type or occurrence
	 * (important for XML schema generation).
	 */
	public static void canonizeCollection(final Element collection,
		final boolean removeActions,
		final boolean genModelVariants) {
		NodeList nl = collection.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE
				&& "def".equals(n.getLocalName())
				&& n.getNamespaceURI() != null) {
				canonizeXDefinition((Element) n,removeActions,genModelVariants);
			}
		}
	}

	private static Element getRefModel(Element collection,
		Element xdef,
		String ref) {
		String modelName;
		Element xd = xdef;
		int ndx;
		if ((ndx = ref.indexOf('#')) < 0) {
			modelName = ref;
		} else {
			String xdName = ref.substring(0, ndx);
			modelName = ref.substring(ndx + 1);
			String xdNs = getXDNodeNS(xdef);
			String s = getXdefAttr(xdef, xdNs, "name", false);
			if (!xdName.equals(s)) {
				xd = null;
				NodeList nl = KXmlUtils.getChildElements(collection);
				for (int j = 0; j < nl.getLength(); j++) {
					Element e = (Element) nl.item(j);
					String uri = e.getNamespaceURI();
					if ("def".equals(e.getLocalName())
						&& (XDConstants.XDEF20_NS_URI.equals(uri)
							|| XDConstants.XDEF31_NS_URI.equals(uri))) {
						s = getXdefAttr(e, uri, "name", false);
						if (xdName.equals(s)) {
							xd = e;
							break;
						}
					}
				}
			}
		}
		String modelLocalName;
		String modelNSURI;
		if ((ndx = modelName.indexOf(':')) < 0) {
			modelNSURI = null;
			modelLocalName = modelName;
		} else {
			modelNSURI = xdef.getAttributeNS(
				XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
				modelName.substring(0, ndx)); //prefix
			modelLocalName = modelName.substring(ndx + 1); //local name
		}
		NodeList nl = xd.getChildNodes();
		for (int j = 0; j < nl.getLength(); j++) {
			Element child = (Element) nl.item(j);
			String childLocalName = child.getLocalName();
			String childNsURI = child.getNamespaceURI();
			if (modelLocalName.equals(childLocalName)
				&& (modelNSURI == null
					? childNsURI == null :modelNSURI.equals(childNsURI))) {
				return (Element) nl.item(j);
			}
		}
		return null;
	}

	/** Create new element with the new name as a copy of given element.
	 * @param newName name of the created element.
	 * @param orig element from which the copy is generated.
	 * @return created element.
	 */
	public static Element copyToNewElement(String newName, Element orig) {
		String u = orig.getNamespaceURI();
		Document doc = orig.getOwnerDocument();
		Element result = u == null
			? doc.createElement(newName) : doc.createElementNS(u, newName);
		NamedNodeMap nm1 = orig.getAttributes();
		for (int k = 0; k < nm1.getLength(); k++) {
			Attr ak = (Attr) nm1.item(k);
			String u1 = ak.getNamespaceURI();
			if (u1 == null) {
				result.setAttribute(ak.getName(), ak.getValue());
			} else {
				result.setAttributeNS(u1, ak.getName(), ak.getValue());
			}
		}
		NodeList nl = orig.getChildNodes();
		for (int k = 0; k < nl.getLength(); k++) {
			result.appendChild(nl.item(k).cloneNode(true));
		}
		return result;
	}

	/** Get id which creates unique identifier of child element. If result is 0
	 * then original name itself is unique.
	 * @param el element where id is searched.
	 * @param origName tested identifier.
	 * @return id of unique identifier or 0.
	 */
	public static int genUniqueID(Element el, String origName) {
		NodeList nl = el.getChildNodes();
		int j = 0;
		boolean found;
		String s = origName;
		for (;;) {
			found = true;
			for (int k = 0; k < nl.getLength(); k++) {
				Node n = nl.item(k);
				if (s.equals(n.getNodeName())) {
					s = origName + "_" + ++j;
					found = false;
					break;
				}
			}
			if (found) {
				return j;
			}
		}
	}

	/** Lookup if "extensions" of attribute descriptions in the model are not
	 * redefining the attribute descriptions which are already defined in the
	 * model.
	 * @param collection root of collection of X-definitions.
	 * @param xdef "actual" X-definition.
	 * @param xel inspected model.
	 * @param genModelVariants if true generate alternate models if in the
	 * reference there exists an attribute redefining type or occurrence
	 * (important for XML schema generation).
	 */
	private static void preprocXModel(Element collection,
		Element xdef,
		Element xel,
		final boolean genModelVariants) {
		String xdURI = getXDNodeNS(xdef);
		Attr a;
		if (!xdURI.equals(xel.getNamespaceURI())
			&& (a = xel.getAttributeNodeNS(xdURI, "script")) != null) {
			String ref = XDParsedScript.getXdScript(a)._reference;
			if (ref.length() != 0) {
				Element refModel = getRefModel(collection, xdef, ref);
				Element newRefModel = null;
				NamedNodeMap nm = xel.getAttributes();
				for (int i = 0; i < nm.getLength(); i++) {
					Attr aa = (Attr) nm.item(i);
					if (aa == a || xdURI.equals(aa.getNamespaceURI())) {
						continue;
					}
					String u = aa.getNamespaceURI();
					Attr ab; //the "same" atribute from model
					if (refModel == null) {
						ab = null;
					} else if (u == null) {
						ab = refModel.getAttributeNode(aa.getName());
					} else {
						ab = refModel.getAttributeNodeNS(u, aa.getName());
					}
					if (ab != null) {//attribude description exists in the model
						XDParsedScript x1 = XDParsedScript.getXdScript(aa);
						XDParsedScript x2 = XDParsedScript.getXdScript(ab);
						String t1 = x1._type;
						String t2 = x2._type;
						xel.removeAttributeNode(aa); //remove the attribute
						//check if type differs and/or if occurrence differs
						if (genModelVariants
							&& (!x1._xOccurrence.equals(x2._xOccurrence)
							|| (t1.length() != 0 && !t1.equals(t2)))) {
							//differs
							if (newRefModel == null) { //create new model
								String refName = refModel.getNodeName();
								Element xdef1=(Element)refModel.getParentNode();
								int id = genUniqueID(xdef1, refName);
								newRefModel =
									copyToNewElement(refName+"_"+id, refModel);
								xdef1.appendChild(newRefModel);
								XDParsedScript xa=XDParsedScript.getXdScript(a);
								String t = xa._type;
								// modify reference
								a.setNodeValue(xa.getxOccurrence()
									.toString(true)
									+ (t.length() > 0 ? ";" + t : "")
									+ ";ref " + ref + "_" + id);
							}
							if (t1.length() == 0) {
								t1 = t2;
							}
							//set the attribute description in the model.
							String s =x1.getxOccurrence().toString(true)+" "+t1;
							if (u == null) {
								newRefModel.setAttribute(aa.getName(), s);
							} else {
								newRefModel.setAttributeNS(u, aa.getName(), s);
							}
						}
					}
				}
			}
		}
		NodeList nl = xel.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				preprocXModel(collection, xdef, (Element) n, genModelVariants);
			}
		}
	}

	/** Lookup if "extensions" of attribute descriptions in the model are not
	 * redefining the attribute descriptions which are already defined in tne
	 * model.
	 * @param collection root of collection of X-definitions.
	 * @param xdef "actual" X-definition.
	 * @param genModelVariants if true generate alternate models if in the
	 * reference there exists an attribute re defining type or occurrence
	 * (important for XML schema generation).
	 */
	public static void preprocXdef(Element collection,
		Element xdef,
		final boolean genModelVariants) {
		NodeList nl = xdef.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) { //model
				Element e = (Element) n;
				preprocXModel(collection, xdef, e, genModelVariants);
			}
		}
	}

	/** Check if given string contains correct X-definition.
	 * @param source string with X-definition.
	 * @return X-definition namespace.
	 * @throws SRuntimeException if an error occurs.
	 */
	public static XDPool chkXdef(String source) throws SRuntimeException {
		Properties props = new Properties();
		props.setProperty(XDConstants.XDPROPERTY_IGNORE_UNDEF_EXT,
			XDConstants.XDPROPERTYVALUE_IGNORE_UNDEF_EXT_TRUE);
		XDBuilder xf = XDFactory.getXDBuilder(props);
		xf.setSource(source);
		return xf.compileXD();
	}

	/** Check if given String sources contains correct X-definition.
	 * @param sources X-definition sources.
	 * @return X-definition namespace.
	 * @throws SRuntimeException if an error occurs.
	 */
	public static XDPool chkXdef(String[] sources) throws SRuntimeException {
		Properties props = new Properties();
		props.setProperty(XDConstants.XDPROPERTY_IGNORE_UNDEF_EXT,
			XDConstants.XDPROPERTYVALUE_IGNORE_UNDEF_EXT_TRUE);
		XDBuilder xf = XDFactory.getXDBuilder(props);
		xf.setSource(sources);
		try {
			return xf.compileXD();
		} catch (SRuntimeException ex) {
			throw ex;
		}

	}

	/** Check if given file contains correct X-definition.
	 * @param file X-definition source.
	 * @return X-definition namespace.
	 * @throws SRuntimeException if an error occurs.
	 */
	public static XDPool chkXdef(File file) throws SRuntimeException {
		Properties props = new Properties();
		props.setProperty(XDConstants.XDPROPERTY_IGNORE_UNDEF_EXT,
			XDConstants.XDPROPERTYVALUE_IGNORE_UNDEF_EXT_TRUE);
		XDBuilder xf = XDFactory.getXDBuilder(props);
		xf.setSource(file);
		return xf.compileXD();
	}

	/** Check if given files contains correct X-definition.
	 * @param files X-definition sources.
	 * @return X-definition namespace.
	 * @throws SRuntimeException if an error occurs.
	 */
	public static XDPool chkXdef(File[] files) throws SRuntimeException {
		Properties props = new Properties();
		props.setProperty(XDConstants.XDPROPERTY_IGNORE_UNDEF_EXT, "true");
		XDBuilder xf = XDFactory.getXDBuilder(props);
		xf.setSource(files);
		return xf.compileXD();
	}

	/** Check if given URL contains correct X-definition.
	 * @param url X-definition source.
	 * @return X-definition namespace.
	 * @throws SRuntimeException if an error occurs.
	 */
	public static XDPool chkXdef(URL url) throws SRuntimeException {
		Properties props = new Properties();
		props.setProperty(XDConstants.XDPROPERTY_IGNORE_UNDEF_EXT,
			XDConstants.XDPROPERTYVALUE_IGNORE_UNDEF_EXT_TRUE);
		XDBuilder xf = XDFactory.getXDBuilder(props);
		xf.setSource(url);
		return xf.compileXD();
	}

	/** Check if given URLs contains correct X-definition.
	 * @param urls X-definition sources.
	 * @return X-definition namespace.
	 * @throws SRuntimeException if an error occurs.
	 */
	public static XDPool chkXdef(URL[] urls) throws SRuntimeException {
		Properties props = new Properties();
		props.setProperty(XDConstants.XDPROPERTY_IGNORE_UNDEF_EXT,
			XDConstants.XDPROPERTYVALUE_IGNORE_UNDEF_EXT_TRUE);
		XDBuilder xf = XDFactory.getXDBuilder(props);
		xf.setSource(urls);
		return xf.compileXD();
	}

	public static String getXDAttrNS(final Attr n) {
		String uri;
		if (n == null || (uri = n.getNamespaceURI()) == null || uri.isEmpty()) {
			return null;
		}
		return findXDNS(n.getOwnerElement());
	}

	public static String findXDNS(final Node n) {
		if (n != null) {
			for (Node x = n; x != null && x.getNodeType() != Node.DOCUMENT_NODE;
				x = x.getParentNode()) {
				String ns = getXDNodeNS(x);
				if (ns != null) {
					return ns;
				}
			}
		}
		return null;
	}

	public static byte getXDVersion(final Node n) {
		String s = findXDNS(n);
		return XDConstants.XDEF20_NS_URI.equals(s) ? XDConstants.XD20_ID
			: XDConstants.XDEF31_NS_URI.equals(s) ? XDConstants.XD31_ID : 0;
	}

	public final static String getXDNodeNS(final Node n) {
		if (n.getNodeType() != Node.ELEMENT_NODE) {
			return null;
		}
		Element e = (Element) n;
		String uri = e.getNamespaceURI();
		if (uri == null || uri.isEmpty()) {
			return null;
		}
		String localName = e.getLocalName();
		if ("collection".equals(localName)) {
			if (e.hasAttributeNS(XDConstants.XDEF20_NS_URI, "metaNamespace")) {
				return XDConstants.XDEF20_NS_URI;
			}
			if (e.hasAttributeNS(XDConstants.XDEF31_NS_URI, "metaNamespace")) {
				return XDConstants.XDEF31_NS_URI;
			}
			return uri.equals(XDConstants.XDEF20_NS_URI)
				|| uri.equals(XDConstants.XDEF31_NS_URI) ? uri : null;
		}
		if ("def".equals(localName)) {
			if (e.hasAttributeNS(XDConstants.XDEF20_NS_URI, "metaNamespace")) {
				return XDConstants.XDEF20_NS_URI;
			}
			if (e.hasAttributeNS(XDConstants.XDEF31_NS_URI, "metaNamespace")) {
				return XDConstants.XDEF31_NS_URI;
			}
			String s = uri.equals(XDConstants.XDEF20_NS_URI)
				|| uri.equals(XDConstants.XDEF31_NS_URI) ? uri : null;
			if (s != null) {
				return s;
			}
			return getXDNodeNS(e.getParentNode());
		}
		return null;
	}

	/** Create collection element from sources.
	 * @param sources array of source paths, wildcards are permitted.
	 * @param resolvemacros if true then macros are resolved.
	 * @param removeActions if true all actions except validation are removed.
	 * @param genModelVariants if true generate alternate models if in the
	 * reference there exists an attribute to redefine type or occurrence
	 * (important for XML schema generation).
	 * @return element with collection of X-definitions.
	 * @throws java.lang.Exception if an error occurs.
	 */
	public static Element genCollection(final String[] sources,
		final boolean resolvemacros,
		final boolean removeActions,
		final boolean genModelVariants) throws Exception {
		if (!isXML(sources)) {
			// File paths
			File[] files = SUtils.getFileGroup(sources);
			return genCollection(
				files, resolvemacros, removeActions, genModelVariants);
		} else {
			// XML sources
			if (sources == null || sources.length == 0) {
				throw new SRuntimeException(
					"Unavailable source with X-definition");
			}
			chkXdef(sources);
			XDGenCollection x = new XDGenCollection();
			x.parse(sources);
			for (int i = 0; i < x._includeList.size(); i++) {
				x.parse(x._includeList.get(i));
			}
			if (resolvemacros) {
				processMacros(x._collection, x._macros, true);
			}
			canonizeCollection(x._collection, removeActions, genModelVariants);
			NodeList nl = x._collection.getChildNodes();
			boolean found = false;
			for (int i = 0; i < nl.getLength(); i++) {
				Node n = nl.item(i);
				if ("def".equals(n.getLocalName()) &&
					getXDNodeNS(n) != null) {
					found = true;
					break;
				}
			}
			if (!found) {
				//XDEF269=X-definition &{0}{'}{' }doesn't exist
				throw new SRuntimeException(XDEF.XDEF269);
			}
			if (removeActions) {
				for (int i = 0; i < nl.getLength(); i++) {
					preprocXdef(x._collection,
						(Element) nl.item(i), genModelVariants);
				}
			}
			return x._collection;
		}
	}

	/** Create collection element from sources.
	 * @param files array of source files.
	 * @param resolvemacros if true then macros are resolved.
	 * @param removeActions if true all actions except validation are removed.
	 * @param genModelVariants if true generate alternate models if in the
	 * reference there exists an attribute redefining type or occurrence
	 * (important for XML schema generation).
	 * @return element with collection of X-definitions.
	 * @throws java.lang.Exception if an error occurs.
	 */
	public static Element genCollection(final File[] files,
		final boolean resolvemacros,
		final boolean removeActions,
		final boolean genModelVariants) throws Exception {
		if (files == null || files.length == 0) {
			//XDEF269=X-definition &{0}{'}{' }doesn't exist
			throw new SRuntimeException(XDEF.XDEF269);
		}
		XDPool xp = chkXdef(files);
		XDGenCollection x = new XDGenCollection();
		x.parse(files);
		for (int i = 0; i < x._includeList.size(); i++) {
			x.parse(x._includeList.get(i));
		}
		if (resolvemacros) {
			processMacros(x._collection, x._macros, true);
		}
		canonizeCollection(x._collection, removeActions, genModelVariants);
		NodeList nl = x._collection.getChildNodes();
		boolean found = false;
		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);
			if ("def".equals(n.getLocalName())) {
				found = true;
				break;
			}
		}
		if (!found) {
			//XDEF269=X-definition &{0}{'}{' }doesn't exist
			throw new SRuntimeException(XDEF.XDEF269);
		}
		if (removeActions) {
			for (int i = 0; i < nl.getLength(); i++) {
				preprocXdef(x._collection,
					(Element) nl.item(i), genModelVariants);
			}
		}
		return x._collection;
	}

	/** Create collection element from sources.
	 * @param urls array of source urls.
	 * @param resolvemacros if true then macros are resolved.
	 * @param removeActions if true all actions except validation are removed.
	 * @param genModelVariants if true generate alternate models if in the
	 * reference there exists an attribute redefining type or occurrence
	 * (important for XML schema generation).
	 * @return element with collection of X-definitions.
	 * @throws java.lang.Exception if an error occurs.
	 */
	public static Element genCollection(URL[] urls,
		boolean resolvemacros,
		final boolean removeActions,
		final boolean genModelVariants) throws Exception {
		XDPool xp = chkXdef(urls);
		XDGenCollection x = new XDGenCollection();
		x.parse(urls);
		for (int i = 0; i < x._includeList.size(); i++) {
			x.parse(x._includeList.get(i));
		}
		if (resolvemacros) {
			processMacros(x._collection, x._macros, true);
		}
		canonizeCollection(x._collection, removeActions, genModelVariants);
		return x._collection;
	}

	/** Find if string starts with '&gt;'
	 * @param source source file name.
	 * @return true if the source starts with '&gt;'
	 */
	public static boolean isXML(final String source) {
		return source.length() > 0 ? '<' == source.charAt(0) : false;
	}

	/** Find if all strings starts with '&gt;'
	 * @param sources Array with source file names.
	 * @return true if all strings starts with '&gt;'
	 */
	public static boolean isXML(final String[] sources) {
		if (sources.length < 1) {
			return false;
		}
		for (int i = 0; i < sources.length; i++) {
			String string = sources[i];
			if (!isXML(string)) {
				return false;
			}
		}
		return true;
	}
}