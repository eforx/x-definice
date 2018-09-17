package cz.syntea.xdef.impl.parsers;

import cz.syntea.xdef.sys.SParser;
import cz.syntea.xdef.sys.SUtils;
import cz.syntea.xdef.sys.StringParser;
import cz.syntea.xdef.XDValue;
import cz.syntea.xdef.proc.XXNode;
import cz.syntea.xdef.impl.code.DefString;

/** Parser of X-Script "ISOLanguages" type.
 * @author Vaclav Trojan
 */
public class XDParseISOLanguages extends XDParseNCNameList {
	private static final String ROOTBASENAME = "ISOlanguages";

	public XDParseISOLanguages() {
		super();
	}
	@Override
	XDValue parse(final XXNode xnode, final StringParser parser) {
		int pos = parser.getIndex();
		while(!parser.eos() && parser.isLetter() != SParser.NOCHAR) {}
		try {
			String s = parser.getParsedBufferPartFrom(pos);
			SUtils.getISO3Language(s);
			return new DefString(s);
		} catch (Exception ex) {}
		return null;
	}
	@Override
	public String parserName() {return ROOTBASENAME;}
}