// This file was generated by org.xdef.component.GenXComponent.
// XDPosition: "SouborD1A#TrolejbusDN".
// Any modifications to this file will be lost upon recompilation.
package test.xdef.component;
public class TrolejbusDN implements org.xdef.component.XComponent{
  public String getOznSegmentu() {return _OznSegmentu;}
  public String getLinka() {return _Linka;}
  public Integer getLinkaPoradi() {return _LinkaPoradi;}
  public String getEvidCislo() {return _EvidCislo;}
  public String getTypVozidla() {return _TypVozidla;}
  public String getTypBrzd() {return _TypBrzd;}
  public String getStavBrzd() {return _StavBrzd;}
  public String getKodPojistitele() {return _KodPojistitele;}
  public String getNazevPojistitele() {return _NazevPojistitele;}
  public String getCisloDokladuPojisteni() {return _CisloDokladuPojisteni;}
  public String getPojisteniText() {return _PojisteniText;}
  public String getDruhVozidla() {return _DruhVozidla;}
  public Z3 getSkoda() {return _Skoda;}
  public Z3 getJinaSkoda() {return _JinaSkoda;}
  public TrolejbusDN.Vlastnik getVlastnik() {return _Vlastnik;}
  public void setOznSegmentu(String x) {_OznSegmentu = x;}
  public void setLinka(String x) {_Linka = x;}
  public void setLinkaPoradi(Integer x) {_LinkaPoradi = x;}
  public void setEvidCislo(String x) {_EvidCislo = x;}
  public void setTypVozidla(String x) {_TypVozidla = x;}
  public void setTypBrzd(String x) {_TypBrzd = x;}
  public void setStavBrzd(String x) {_StavBrzd = x;}
  public void setKodPojistitele(String x) {_KodPojistitele = x;}
  public void setNazevPojistitele(String x) {_NazevPojistitele = x;}
  public void setCisloDokladuPojisteni(String x) {_CisloDokladuPojisteni = x;}
  public void setPojisteniText(String x) {_PojisteniText = x;}
  public void setDruhVozidla(String x) {_DruhVozidla = x;}
  public void setSkoda(Z3 x) {
    if (x!=null && x.xGetXPos() == null)
      x.xInit(this, "Skoda", null, "SouborD1A#TrolejbusDN/$mixed/Skoda");
    _Skoda = x;
  }
  public void setJinaSkoda(Z3 x) {
    if (x!=null && x.xGetXPos() == null)
      x.xInit(this, "JinaSkoda", null, "SouborD1A#TrolejbusDN/$mixed/JinaSkoda");
    _JinaSkoda = x;
  }
  public void setVlastnik(TrolejbusDN.Vlastnik x) {
    if (x!=null && x.xGetXPos() == null)
      x.xInit(this, "Vlastnik", null, "SouborD1A#TrolejbusDN/$mixed/Vlastnik");
    _Vlastnik = x;
  }
  public String xposOfOznSegmentu(){return XD_XPos + "/@OznSegmentu";}
  public String xposOfLinka(){return XD_XPos + "/@Linka";}
  public String xposOfLinkaPoradi(){return XD_XPos + "/@LinkaPoradi";}
  public String xposOfEvidCislo(){return XD_XPos + "/@EvidCislo";}
  public String xposOfTypVozidla(){return XD_XPos + "/@TypVozidla";}
  public String xposOfTypBrzd(){return XD_XPos + "/@TypBrzd";}
  public String xposOfStavBrzd(){return XD_XPos + "/@StavBrzd";}
  public String xposOfKodPojistitele(){return XD_XPos + "/@KodPojistitele";}
  public String xposOfNazevPojistitele(){return XD_XPos + "/@NazevPojistitele";}
  public String xposOfCisloDokladuPojisteni(){return XD_XPos + "/@CisloDokladuPojisteni";}
  public String xposOfPojisteniText(){return XD_XPos + "/@PojisteniText";}
  public String xposOfDruhVozidla(){return XD_XPos + "/@DruhVozidla";}
//<editor-fold defaultstate="collapsed" desc="XComponent interface">
  @Override
  public org.w3c.dom.Element toXml()
    {return (org.w3c.dom.Element) toXml((org.w3c.dom.Document) null);}
  @Override
  public String xGetNodeName() {return XD_NodeName;}
  @Override
  public void xInit(org.xdef.component.XComponent p,
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
  public org.xdef.component.XComponent xGetParent() {return XD_Parent;}
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
    if (doc==null) {
      doc = org.xdef.xml.KXmlUtils.newDocument(XD_NamespaceURI,
        XD_NodeName, null);
      el = doc.getDocumentElement();
    } else {
      el = doc.createElementNS(XD_NamespaceURI, XD_NodeName);
      if (doc.getDocumentElement()==null) doc.appendChild(el);
    }
    if (getOznSegmentu() != null)
      el.setAttribute("OznSegmentu", getOznSegmentu());
    if (getLinka() != null)
      el.setAttribute("Linka", getLinka());
    if (getLinkaPoradi() != null)
      el.setAttribute("LinkaPoradi", String.valueOf(getLinkaPoradi()));
    if (getEvidCislo() != null)
      el.setAttribute("EvidCislo", getEvidCislo());
    if (getTypVozidla() != null)
      el.setAttribute("TypVozidla", getTypVozidla());
    if (getTypBrzd() != null)
      el.setAttribute("TypBrzd", getTypBrzd());
    if (getStavBrzd() != null)
      el.setAttribute("StavBrzd", getStavBrzd());
    if (getKodPojistitele() != null)
      el.setAttribute("KodPojistitele", getKodPojistitele());
    if (getNazevPojistitele() != null)
      el.setAttribute("NazevPojistitele", getNazevPojistitele());
    if (getCisloDokladuPojisteni() != null)
      el.setAttribute("CisloDokladuPojisteni", getCisloDokladuPojisteni());
    if (getPojisteniText() != null)
      el.setAttribute("PojisteniText", getPojisteniText());
    if (getDruhVozidla() != null)
      el.setAttribute("DruhVozidla", getDruhVozidla());
    for (org.xdef.component.XComponent x: XD_List==null?xGetNodeList():XD_List)
      el.appendChild(x.toXml(doc));
    XD_List = null;
    return el;
  }
  @Override
  public java.util.List<org.xdef.component.XComponent> xGetNodeList() {
    java.util.ArrayList<org.xdef.component.XComponent> a =
      new java.util.ArrayList<org.xdef.component.XComponent>();
    org.xdef.component.XComponentUtil.addXC(a, getSkoda());
    org.xdef.component.XComponentUtil.addXC(a, getJinaSkoda());
    org.xdef.component.XComponentUtil.addXC(a, getVlastnik());
    return XD_List = a;
  }
  public TrolejbusDN() {}
  public TrolejbusDN(org.xdef.component.XComponent p,
    String name, String ns, String xPos, String XDPos) {
    XD_NodeName=name; XD_NamespaceURI=ns;
    XD_XPos=xPos;
    XD_Model=XDPos;
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
  }
  public TrolejbusDN(org.xdef.component.XComponent p, org.xdef.proc.XXNode xx){
    org.w3c.dom.Element el=xx.getElement();
    XD_NodeName=el.getNodeName(); XD_NamespaceURI=el.getNamespaceURI();
    XD_XPos=xx.getXPos();
    XD_Model=xx.getXMElement().getXDPosition();
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
    if (!"D4BF570CA602EC4C5D205CD463EB5A01".equals(
      xx.getXMElement().getDigest())) { //incompatible element model
      throw new org.xdef.sys.SRuntimeException(
        org.xdef.msg.XDEF.XDEF374);
    }
  }
  private String _OznSegmentu;
  private String _Linka;
  private Integer _LinkaPoradi;
  private String _EvidCislo;
  private String _TypVozidla;
  private String _TypBrzd;
  private String _StavBrzd;
  private String _KodPojistitele;
  private String _NazevPojistitele;
  private String _CisloDokladuPojisteni;
  private String _PojisteniText;
  private String _DruhVozidla;
  private Z3 _Skoda;
  private Z3 _JinaSkoda;
  private TrolejbusDN.Vlastnik _Vlastnik;
  private org.xdef.component.XComponent XD_Parent;
  private Object XD_Object;
  private String XD_NodeName = "TrolejbusDN";
  private String XD_NamespaceURI;
  private int XD_Index = -1;
  private int XD_ndx;
  private String XD_XPos;
  private java.util.List<org.xdef.component.XComponent> XD_List;
  private String XD_Model="SouborD1A#TrolejbusDN";
  @Override
  public void xSetText(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {}
  @Override
  public void xSetAttr(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {
    if (xx.getXMNode().getXDPosition().endsWith("/@CisloDokladuPojisteni"))
      setCisloDokladuPojisteni(parseResult.getParsedValue().stringValue());
    else if (xx.getXMNode().getXDPosition().endsWith("/@DruhVozidla"))
      setDruhVozidla(parseResult.getParsedValue().stringValue());
    else if (xx.getXMNode().getXDPosition().endsWith("/@EvidCislo"))
      setEvidCislo(parseResult.getParsedValue().stringValue());
    else if (xx.getXMNode().getXDPosition().endsWith("/@KodPojistitele"))
      setKodPojistitele(parseResult.getParsedValue().stringValue());
    else if (xx.getXMNode().getXDPosition().endsWith("/@Linka"))
      setLinka(parseResult.getParsedValue().stringValue());
    else if (xx.getXMNode().getXDPosition().endsWith("/@LinkaPoradi"))
      setLinkaPoradi(parseResult.getParsedValue().intValue());
    else if (xx.getXMNode().getXDPosition().endsWith("/@NazevPojistitele"))
      setNazevPojistitele(parseResult.getParsedValue().stringValue());
    else if (xx.getXMNode().getXDPosition().endsWith("/@OznSegmentu"))
      setOznSegmentu(parseResult.getParsedValue().stringValue());
    else if (xx.getXMNode().getXDPosition().endsWith("/@PojisteniText"))
      setPojisteniText(parseResult.getParsedValue().stringValue());
    else if (xx.getXMNode().getXDPosition().endsWith("/@StavBrzd"))
      setStavBrzd(parseResult.getParsedValue().stringValue());
    else if (xx.getXMNode().getXDPosition().endsWith("/@TypBrzd"))
      setTypBrzd(parseResult.getParsedValue().stringValue());
    else setTypVozidla(parseResult.getParsedValue().stringValue());
  }
  @Override
  public org.xdef.component.XComponent xCreateXChild(org.xdef.proc.XXNode xx) {
    String s = xx.getXMElement().getXDPosition();
    if ("SouborD1A#TrolejbusDN/$mixed/JinaSkoda".equals(s))
      return new test.xdef.component.Z3(this, xx);
    if ("SouborD1A#TrolejbusDN/$mixed/Skoda".equals(s))
      return new test.xdef.component.Z3(this, xx);
    return new Vlastnik(this, xx); // SouborD1A#TrolejbusDN/$mixed/Vlastnik
  }
  @Override
  public void xAddXChild(org.xdef.component.XComponent xc) {
    xc.xSetNodeIndex(XD_ndx++);
    String s = xc.xGetModelPosition();
    if ("SouborD1A#TrolejbusDN/$mixed/JinaSkoda".equals(s))
      setJinaSkoda((test.xdef.component.Z3) xc);
    else if ("SouborD1A#TrolejbusDN/$mixed/Skoda".equals(s))
      setSkoda((test.xdef.component.Z3) xc);
    else
      setVlastnik((Vlastnik) xc); //SouborD1A#TrolejbusDN/$mixed/Vlastnik
  }
  @Override
  public void xSetAny(org.w3c.dom.Element el) {}
// </editor-fold>
public static class Vlastnik implements org.xdef.component.XComponent{
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
  public void xInit(org.xdef.component.XComponent p,
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
  public org.xdef.component.XComponent xGetParent() {return XD_Parent;}
  @Override
  public Object xGetObject() {return XD_Object;}
  @Override
  public void xSetObject(final Object obj) {XD_Object = obj;}
  @Override
  public String toString() {return "XComponent: "+xGetModelPosition();}
  @Override
  public String xGetModelPosition() {return XD_Model;}
  @Override
  public int xGetModelIndex() {return 3;}
  @Override
  public org.w3c.dom.Node toXml(org.w3c.dom.Document doc) {
    org.w3c.dom.Element el;
    if (doc==null) {
      doc = org.xdef.xml.KXmlUtils.newDocument(XD_NamespaceURI,
        XD_NodeName, null);
      el = doc.getDocumentElement();
    } else {
      el = doc.createElementNS(XD_NamespaceURI, XD_NodeName);
    }
    for (org.xdef.component.XComponent x: XD_List==null?xGetNodeList():XD_List)
      el.appendChild(x.toXml(doc));
    XD_List = null;
    return el;
  }
  @Override
  public java.util.List<org.xdef.component.XComponent> xGetNodeList() {
    java.util.ArrayList<org.xdef.component.XComponent> a =
      new java.util.ArrayList<org.xdef.component.XComponent>();
    if (get$value() != null)
      org.xdef.component.XComponentUtil.addText(this,
        "SouborD1A#text/$text", a, get$value(), _$$value);
    return XD_List = a;
  }
  public Vlastnik() {}
  public Vlastnik(org.xdef.component.XComponent p,
    String name, String ns, String xPos, String XDPos) {
    XD_NodeName=name; XD_NamespaceURI=ns;
    XD_XPos=xPos;
    XD_Model=XDPos;
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
  }
  public Vlastnik(org.xdef.component.XComponent p, org.xdef.proc.XXNode xx){
    org.w3c.dom.Element el=xx.getElement();
    XD_NodeName=el.getNodeName(); XD_NamespaceURI=el.getNamespaceURI();
    XD_XPos=xx.getXPos();
    XD_Model=xx.getXMElement().getXDPosition();
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
    if (!"0BBC8E2A504A9E2D3C354DD465C51838".equals(
      xx.getXMElement().getDigest())) { //incompatible element model
      throw new org.xdef.sys.SRuntimeException(
        org.xdef.msg.XDEF.XDEF374);
    }
  }
  private String _$value;
  private char _$$value= (char) -1;
  private org.xdef.component.XComponent XD_Parent;
  private Object XD_Object;
  private String XD_NodeName = "Vlastnik";
  private String XD_NamespaceURI;
  private int XD_Index = -1;
  private int XD_ndx;
  private String XD_XPos;
  private java.util.List<org.xdef.component.XComponent> XD_List;
  private String XD_Model="SouborD1A#TrolejbusDN/$mixed/Vlastnik";
  @Override
  public void xSetText(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {
    _$$value=(char) XD_ndx++;
    set$value(parseResult.getParsedValue().stringValue());
  }
  @Override
  public void xSetAttr(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {}
  @Override
  public org.xdef.component.XComponent xCreateXChild(org.xdef.proc.XXNode xx)
    {return null;}
  @Override
  public void xAddXChild(org.xdef.component.XComponent xc) {}
  @Override
  public void xSetAny(org.w3c.dom.Element el) {}
// </editor-fold>
}
}