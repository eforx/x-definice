// This file was generated by cz.syntea.xdef.component.GenXComponent.
// XDPosition: "SouborD1A#ZvireDN".
// Any modifications to this file will be lost upon recompilation.
package test.xdef.component;
public class ZvireDN implements cz.syntea.xdef.component.XComponent{
  public String getDruhZvirete() {return _DruhZvirete;}
  public String getNazevZvirete() {return _NazevZvirete;}
  public String getOznSegmentu() {return _OznSegmentu;}
  public Z3 getSkoda() {return _Skoda;}
  public ZvireDN.Vlastnik getVlastnik() {return _Vlastnik;}
  public void setDruhZvirete(String x) {_DruhZvirete = x;}
  public void setNazevZvirete(String x) {_NazevZvirete = x;}
  public void setOznSegmentu(String x) {_OznSegmentu = x;}
  public void setSkoda(Z3 x) {
    if (x != null && x.xGetXPos() == null)
      x.xInit(this, "Skoda", null, "SouborD1A#ZvireDN/$mixed/Skoda");
    _Skoda = x;
  }
  public void setVlastnik(ZvireDN.Vlastnik x) {
    if (x != null && x.xGetXPos() == null)
      x.xInit(this, "Vlastnik", null, "SouborD1A#ZvireDN/$mixed/Vlastnik");
    _Vlastnik = x;
  }
  public String xposOfDruhZvirete(){return XD_XPos + "/@DruhZvirete";}
  public String xposOfNazevZvirete(){return XD_XPos + "/@NazevZvirete";}
  public String xposOfOznSegmentu(){return XD_XPos + "/@OznSegmentu";}
//<editor-fold defaultstate="collapsed" desc="XComponent interface">
  @Override
  public org.w3c.dom.Element toXml()
    {return (org.w3c.dom.Element) toXml((org.w3c.dom.Document) null);}
  @Override
  public String xGetNodeName() {return XD_NodeName;}
  @Override
  public void xInit(cz.syntea.xdef.component.XComponent p,
    String name, String ns, String xdPos) {
    XD_Parent=p; XD_NodeName=name; XD_NamespaceURI=ns; XD_Model=xdPos;
  }
  @Override
  public String xGetNamespaceURI() {return XD_NamespaceURI;}
  @Override
  public String xGetXPos() {return XD_XPos;}
  @Override
  public void xSetXPos(String xpos){XD_XPos = xpos;}
  @Override
  public int xGetNodeIndex() {return XD_Index;}
  @Override
  public void xSetNodeIndex(int index) {XD_Index = index;}
  @Override
  public cz.syntea.xdef.component.XComponent xGetParent() {return XD_Parent;}
  @Override
  public Object xGetObject() {return XD_Object;}
  @Override
  public void xSetObject(final Object obj) {XD_Object = obj;}
  @Override
  public String toString() {return "XComponent: "+xGetModelPosition();}
  @Override
  public String xGetModelPosition() {return XD_Model;}
  @Override
  public int xGetModelIndex() {return -1;}
  @Override
  public org.w3c.dom.Node toXml(org.w3c.dom.Document doc) {
    org.w3c.dom.Element el;
    if (doc == null) {
      doc = cz.syntea.xdef.xml.KXmlUtils.newDocument(
        XD_NamespaceURI, XD_NodeName, null);
      el = doc.getDocumentElement();
    } else {
      el = doc.createElementNS(XD_NamespaceURI, XD_NodeName);
      if (doc.getDocumentElement() == null) doc.appendChild(el);
    }
    if (getDruhZvirete() != null)
      el.setAttribute("DruhZvirete", getDruhZvirete());
    if (getNazevZvirete() != null)
      el.setAttribute("NazevZvirete", getNazevZvirete());
    if (getOznSegmentu() != null)
      el.setAttribute("OznSegmentu", getOznSegmentu());
    for (cz.syntea.xdef.component.XComponent x: XD_List==null?xGetNodeList():XD_List)
      el.appendChild(x.toXml(doc));
    XD_List = null;
    return el;
  }
  @Override
  public java.util.List<cz.syntea.xdef.component.XComponent> xGetNodeList() {
    java.util.ArrayList<cz.syntea.xdef.component.XComponent> a =
      new java.util.ArrayList<cz.syntea.xdef.component.XComponent>();
    cz.syntea.xdef.component.XComponentUtil.addXC(a, getSkoda());
    cz.syntea.xdef.component.XComponentUtil.addXC(a, getVlastnik());
    return XD_List = a;
  }
  public ZvireDN() {}
  public ZvireDN(cz.syntea.xdef.component.XComponent p,
    String name, String ns, String xPos, String XDPos) {
    XD_NodeName=name; XD_NamespaceURI=ns;
    XD_XPos=xPos;
    XD_Model=XDPos;
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
  }
  public ZvireDN(cz.syntea.xdef.component.XComponent p, cz.syntea.xdef.proc.XXNode xx){
    org.w3c.dom.Element el=xx.getElement();
    XD_NodeName=el.getNodeName(); XD_NamespaceURI=el.getNamespaceURI();
    XD_XPos=xx.getXPos();
    XD_Model=xx.getXMElement().getXDPosition();
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
    if (!"E6DEFD300BBDF168BCB0CD1F00B9BB2F".equals(
      xx.getXMElement().getDigest())) { //incompatible element model
      throw new cz.syntea.xdef.sys.SRuntimeException(
        cz.syntea.xdef.msg.XDEF.XDEF374);
    }
  }
  private String _DruhZvirete;
  private String _NazevZvirete;
  private String _OznSegmentu;
  private Z3 _Skoda;
  private ZvireDN.Vlastnik _Vlastnik;
  private cz.syntea.xdef.component.XComponent XD_Parent;
  private Object XD_Object;
  private String XD_NodeName = "ZvireDN";
  private String XD_NamespaceURI;
  private int XD_Index = -1;
  private int XD_ndx;
  private String XD_XPos;
  private java.util.List<cz.syntea.xdef.component.XComponent> XD_List;
  private String XD_Model="SouborD1A#ZvireDN";
  @Override
  public void xSetText(cz.syntea.xdef.proc.XXNode xx,
    cz.syntea.xdef.XDParseResult parseResult) {}
  @Override
  public void xSetAttr(cz.syntea.xdef.proc.XXNode xx,
    cz.syntea.xdef.XDParseResult parseResult) {
    if (xx.getXMNode().getXDPosition().endsWith("/@DruhZvirete"))
      setDruhZvirete(parseResult.getParsedValue().stringValue());
    else if (xx.getXMNode().getXDPosition().endsWith("/@NazevZvirete"))
      setNazevZvirete(parseResult.getParsedValue().stringValue());
    else setOznSegmentu(parseResult.getParsedValue().stringValue());
  }
  @Override
  public cz.syntea.xdef.component.XComponent xCreateXChild(cz.syntea.xdef.proc.XXNode xx) {
    String s = xx.getXMElement().getXDPosition();
    if ("SouborD1A#ZvireDN/$mixed/Skoda".equals(s))
      return new test.xdef.component.Z3(this, xx);
    return new Vlastnik(this, xx); // SouborD1A#ZvireDN/$mixed/Vlastnik
  }
  @Override
  public void xAddXChild(cz.syntea.xdef.component.XComponent xc) {
    xc.xSetNodeIndex(XD_ndx++);
    String s = xc.xGetModelPosition();
    if ("SouborD1A#ZvireDN/$mixed/Skoda".equals(s))
      setSkoda((test.xdef.component.Z3) xc);
    else
      setVlastnik((Vlastnik) xc); //SouborD1A#ZvireDN/$mixed/Vlastnik
  }
  @Override
  public void xSetAny(org.w3c.dom.Element el) {}
// </editor-fold>
public static class Vlastnik implements cz.syntea.xdef.component.XComponent{
  public String get$value() {return _$value;}
  public void set$value(String x) {_$value = x;}
  public String xposOf$value(){return XD_XPos + "/$text";}
//<editor-fold defaultstate="collapsed" desc="XComponent interface">
  @Override
  public org.w3c.dom.Element toXml()
    {return (org.w3c.dom.Element) toXml((org.w3c.dom.Document) null);}
  @Override
  public String xGetNodeName() {return XD_NodeName;}
  @Override
  public void xInit(cz.syntea.xdef.component.XComponent p,
    String name, String ns, String xdPos) {
    XD_Parent=p; XD_NodeName=name; XD_NamespaceURI=ns; XD_Model=xdPos;
  }
  @Override
  public String xGetNamespaceURI() {return XD_NamespaceURI;}
  @Override
  public String xGetXPos() {return XD_XPos;}
  @Override
  public void xSetXPos(String xpos){XD_XPos = xpos;}
  @Override
  public int xGetNodeIndex() {return XD_Index;}
  @Override
  public void xSetNodeIndex(int index) {XD_Index = index;}
  @Override
  public cz.syntea.xdef.component.XComponent xGetParent() {return XD_Parent;}
  @Override
  public Object xGetObject() {return XD_Object;}
  @Override
  public void xSetObject(final Object obj) {XD_Object = obj;}
  @Override
  public String toString() {return "XComponent: "+xGetModelPosition();}
  @Override
  public String xGetModelPosition() {return XD_Model;}
  @Override
  public int xGetModelIndex() {return 2;}
  @Override
  public org.w3c.dom.Node toXml(org.w3c.dom.Document doc) {
    org.w3c.dom.Element el;
    if (doc == null) {
      doc = cz.syntea.xdef.xml.KXmlUtils.newDocument(
        XD_NamespaceURI, XD_NodeName, null);
      el = doc.getDocumentElement();
    } else {
      el = doc.createElementNS(XD_NamespaceURI, XD_NodeName);
    }
    for (cz.syntea.xdef.component.XComponent x: XD_List==null?xGetNodeList():XD_List)
      el.appendChild(x.toXml(doc));
    XD_List = null;
    return el;
  }
  @Override
  public java.util.List<cz.syntea.xdef.component.XComponent> xGetNodeList() {
    java.util.ArrayList<cz.syntea.xdef.component.XComponent> a =
      new java.util.ArrayList<cz.syntea.xdef.component.XComponent>();
    if (get$value() != null)
      cz.syntea.xdef.component.XComponentUtil.addText(this,
        "SouborD1A#text/$text", a, get$value(), _$$value);
    return XD_List = a;
  }
  public Vlastnik() {}
  public Vlastnik(cz.syntea.xdef.component.XComponent p,
    String name, String ns, String xPos, String XDPos) {
    XD_NodeName=name; XD_NamespaceURI=ns;
    XD_XPos=xPos;
    XD_Model=XDPos;
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
  }
  public Vlastnik(cz.syntea.xdef.component.XComponent p, cz.syntea.xdef.proc.XXNode xx){
    org.w3c.dom.Element el=xx.getElement();
    XD_NodeName=el.getNodeName(); XD_NamespaceURI=el.getNamespaceURI();
    XD_XPos=xx.getXPos();
    XD_Model=xx.getXMElement().getXDPosition();
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
    if (!"4DFC03ACF3E95404A09F28C955B74323".equals(
      xx.getXMElement().getDigest())) { //incompatible element model
      throw new cz.syntea.xdef.sys.SRuntimeException(
        cz.syntea.xdef.msg.XDEF.XDEF374);
    }
  }
  private String _$value;
  private char _$$value= (char) -1;
  private cz.syntea.xdef.component.XComponent XD_Parent;
  private Object XD_Object;
  private String XD_NodeName = "Vlastnik";
  private String XD_NamespaceURI;
  private int XD_Index = -1;
  private int XD_ndx;
  private String XD_XPos;
  private java.util.List<cz.syntea.xdef.component.XComponent> XD_List;
  private String XD_Model="SouborD1A#ZvireDN/$mixed/Vlastnik";
  @Override
  public void xSetText(cz.syntea.xdef.proc.XXNode xx,
    cz.syntea.xdef.XDParseResult parseResult) {
    _$$value=(char) XD_ndx++;
    set$value(parseResult.getParsedValue().stringValue());
  }
  @Override
  public void xSetAttr(cz.syntea.xdef.proc.XXNode xx,
    cz.syntea.xdef.XDParseResult parseResult) {}
  @Override
  public cz.syntea.xdef.component.XComponent xCreateXChild(cz.syntea.xdef.proc.XXNode xx)
    {return null;}
  @Override
  public void xAddXChild(cz.syntea.xdef.component.XComponent xc) {}
  @Override
  public void xSetAny(org.w3c.dom.Element el) {}
// </editor-fold>
}
}