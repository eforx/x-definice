// This file was generated by org.xdef.component.GenXComponent.
// XDPosition: "H#s:H".
// Any modifications to this file will be lost upon recompilation.
package test.xdef.component;
public class H implements org.xdef.component.XComponent{
  public String gets$encodingStyle() {return _s$encodingStyle;}
  public String getxmlns$s() {return "soap";}
  public H.s$Header gets$Header() {return _s$Header;}
  public H.s$Body gets$Body() {return _s$Body;}
  public void sets$encodingStyle(String x) {_s$encodingStyle = x;}
  public void sets$Header(H.s$Header x) {_s$Header = x;}
  public void sets$Body(H.s$Body x) {_s$Body = x;}
  public String xposOfs$encodingStyle(){return XD_XPos + "/@s$encodingStyle";}
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
    if (gets$encodingStyle() != null)
      el.setAttributeNS("soap", XD_Name_s$encodingStyle, gets$encodingStyle());
    el.setAttributeNS(javax.xml.XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
      "xmlns:s", "soap");
    for (org.xdef.component.XComponent x: xGetNodeList())
      el.appendChild(x.toXml(doc));
    return el;
  }
  @Override
  public java.util.List<org.xdef.component.XComponent> xGetNodeList() {
    java.util.List<org.xdef.component.XComponent> a =
      new java.util.ArrayList<org.xdef.component.XComponent>();
    org.xdef.component.XComponentUtil.addXC(a, gets$Header());
    org.xdef.component.XComponentUtil.addXC(a, gets$Body());
    return a;
  }
  public H() {}
  public H(org.xdef.component.XComponent p,
    String name, String ns, String xPos, String XDPos) {
    XD_NodeName=name; XD_NamespaceURI=ns;
    XD_XPos=xPos;
    XD_Model=XDPos;
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
  }
  public H(org.xdef.component.XComponent p, org.xdef.proc.XXNode xx){
    org.w3c.dom.Element el=xx.getElement();
    XD_NodeName=el.getNodeName(); XD_NamespaceURI=el.getNamespaceURI();
    XD_XPos=xx.getXPos();
    XD_Model=xx.getXMElement().getXDPosition();
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
    if (!"95EC82A4A80C427B99D80C7BA6E6C40E".equals(
      xx.getXMElement().getDigest())) { //incompatible element model
      throw new org.xdef.sys.SRuntimeException(
        org.xdef.msg.XDEF.XDEF374);
    }
  }
  private String XD_Name_s$encodingStyle="s$encodingStyle";
  private String _s$encodingStyle;
  private H.s$Header _s$Header;
  private H.s$Body _s$Body;
  private org.xdef.component.XComponent XD_Parent;
  private Object XD_Object;
  private String XD_NodeName = "s:H";
  private String XD_NamespaceURI = "soap";
  private int XD_Index = -1;
  private int XD_ndx;
  private String XD_XPos;
  private String XD_Model="H#s:H";
  @Override
  public void xSetText(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {}
  @Override
  public void xSetAttr(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {
    XD_Name_s$encodingStyle = xx.getNodeName();
    sets$encodingStyle(parseResult.getParsedValue().stringValue());
  }
  @Override
  public org.xdef.component.XComponent xCreateXChild(org.xdef.proc.XXNode xx) {
    String s = xx.getXMElement().getXDPosition();
    if ("H#s:H/s:Body".equals(s))
      return new s$Body(this, xx);
    return new s$Header(this, xx); // H#s:H/s:Header
  }
  @Override
  public void xAddXChild(org.xdef.component.XComponent xc) {
    xc.xSetNodeIndex(XD_ndx++);
    String s = xc.xGetModelPosition();
    if ("H#s:H/s:Body".equals(s))
      sets$Body((s$Body) xc);
    else
      sets$Header((s$Header) xc); //H#s:H/s:Header
  }
  @Override
  public void xSetAny(org.w3c.dom.Element el) {}
// </editor-fold>
public static class s$Header implements org.xdef.component.XComponent{
  public String getxmlns$s() {return "soap";}
  public test.xdef.component.H.s$Header.b$User getb$User() {return _b$User;}
  public test.xdef.component.H.s$Header.b$Request getb$Request() {return _b$Request;}
  public void setb$User(test.xdef.component.H.s$Header.b$User x) {_b$User = x;}
  public void setb$Request(test.xdef.component.H.s$Header.b$Request x) {_b$Request = x;}
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
  public int xGetModelIndex() {return 0;}
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
    el.setAttributeNS(javax.xml.XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
      "xmlns:s", "soap");
    for (org.xdef.component.XComponent x: xGetNodeList())
      el.appendChild(x.toXml(doc));
    return el;
  }
  @Override
  public java.util.List<org.xdef.component.XComponent> xGetNodeList() {
    java.util.List<org.xdef.component.XComponent> a =
      new java.util.ArrayList<org.xdef.component.XComponent>();
    org.xdef.component.XComponentUtil.addXC(a, getb$User());
    org.xdef.component.XComponentUtil.addXC(a, getb$Request());
    return a;
  }
  public s$Header() {}
  public s$Header(org.xdef.component.XComponent p,
    String name, String ns, String xPos, String XDPos) {
    XD_NodeName=name; XD_NamespaceURI=ns;
    XD_XPos=xPos;
    XD_Model=XDPos;
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
  }
  public s$Header(org.xdef.component.XComponent p, org.xdef.proc.XXNode xx){
    org.w3c.dom.Element el=xx.getElement();
    XD_NodeName=el.getNodeName(); XD_NamespaceURI=el.getNamespaceURI();
    XD_XPos=xx.getXPos();
    XD_Model=xx.getXMElement().getXDPosition();
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
    if (!"4C590D680B84F43CFDB6FF69E3CFFA5B".equals(
      xx.getXMElement().getDigest())) { //incompatible element model
      throw new org.xdef.sys.SRuntimeException(
        org.xdef.msg.XDEF.XDEF374);
    }
  }
  private test.xdef.component.H.s$Header.b$User _b$User;
  private test.xdef.component.H.s$Header.b$Request _b$Request;
  private org.xdef.component.XComponent XD_Parent;
  private Object XD_Object;
  private String XD_NodeName = "s:Header";
  private String XD_NamespaceURI = "soap";
  private int XD_Index = -1;
  private int XD_ndx;
  private String XD_XPos;
  private String XD_Model="H#s:H/s:Header";
  @Override
  public void xSetText(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {}
  @Override
  public void xSetAttr(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {}
  @Override
  public org.xdef.component.XComponent xCreateXChild(org.xdef.proc.XXNode xx) {
    String s = xx.getXMElement().getXDPosition();
    if ("H#s:H/s:Header/b:Request".equals(s))
      return new b$Request(this, xx);
    return new b$User(this, xx); // H#s:H/s:Header/b:User
  }
  @Override
  public void xAddXChild(org.xdef.component.XComponent xc) {
    xc.xSetNodeIndex(XD_ndx++);
    String s = xc.xGetModelPosition();
    if ("H#s:H/s:Header/b:Request".equals(s))
      setb$Request((b$Request) xc);
    else
      setb$User((b$User) xc); //H#s:H/s:Header/b:User
  }
  @Override
  public void xSetAny(org.w3c.dom.Element el) {}
// </editor-fold>
public static class b$User implements org.xdef.component.XComponent{
  public String gets$understand() {return _s$understand;}
  public String getIdentUser() {return _IdentUser;}
  public String getxmlns$s() {return "soap";}
  public String getxmlns$b() {return "request";}
  public void sets$understand(String x) {_s$understand = x;}
  public void setIdentUser(String x) {_IdentUser = x;}
  public String xposOfs$understand(){return XD_XPos + "/@s$understand";}
  public String xposOfIdentUser(){return XD_XPos + "/@IdentUser";}
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
  public int xGetModelIndex() {return 0;}
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
    if (gets$understand() != null)
      el.setAttributeNS("soap", XD_Name_s$understand, gets$understand());
    if (getIdentUser() != null)
      el.setAttribute(XD_Name_IdentUser, getIdentUser());
    el.setAttributeNS(javax.xml.XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
      "xmlns:s", "soap");
    el.setAttributeNS(javax.xml.XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
      "xmlns:b", "request");
    return el;
  }
  @Override
  public java.util.List<org.xdef.component.XComponent> xGetNodeList() {
    return new java.util.ArrayList<org.xdef.component.XComponent>();}
  public b$User() {}
  public b$User(org.xdef.component.XComponent p,
    String name, String ns, String xPos, String XDPos) {
    XD_NodeName=name; XD_NamespaceURI=ns;
    XD_XPos=xPos;
    XD_Model=XDPos;
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
  }
  public b$User(org.xdef.component.XComponent p, org.xdef.proc.XXNode xx){
    org.w3c.dom.Element el=xx.getElement();
    XD_NodeName=el.getNodeName(); XD_NamespaceURI=el.getNamespaceURI();
    XD_XPos=xx.getXPos();
    XD_Model=xx.getXMElement().getXDPosition();
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
    if (!"6F7BEDC2492CB28EB28743C5D84F978C".equals(
      xx.getXMElement().getDigest())) { //incompatible element model
      throw new org.xdef.sys.SRuntimeException(
        org.xdef.msg.XDEF.XDEF374);
    }
  }
  private String XD_Name_s$understand="s$understand";
  private String _s$understand;
  private String XD_Name_IdentUser="IdentUser";
  private String _IdentUser;
  private org.xdef.component.XComponent XD_Parent;
  private Object XD_Object;
  private String XD_NodeName = "b:User";
  private String XD_NamespaceURI = "request";
  private int XD_Index = -1;
  private String XD_XPos;
  private String XD_Model="H#s:H/s:Header/b:User";
  @Override
  public void xSetText(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {}
  @Override
  public void xSetAttr(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {
    if (xx.getXMNode().getXDPosition().endsWith("/@IdentUser")) {
      XD_Name_IdentUser = xx.getNodeName();
      setIdentUser(parseResult.getParsedValue().stringValue());
    } else {
      XD_Name_s$understand = xx.getNodeName();
      sets$understand(parseResult.getParsedValue().stringValue());
    }
  }
  @Override
  public org.xdef.component.XComponent xCreateXChild(org.xdef.proc.XXNode xx)
    {return null;}
  @Override
  public void xAddXChild(org.xdef.component.XComponent xc) {}
  @Override
  public void xSetAny(org.w3c.dom.Element el) {}
// </editor-fold>
}
public static class b$Request implements org.xdef.component.XComponent{
  public String gets$understand() {return _s$understand;}
  public String getIdentZpravy() {return _IdentZpravy;}
  public Integer getReqMsgId() {return _ReqMsgId;}
  public String getMode() {return _Mode;}
  public String getxmlns$s() {return "soap";}
  public String getxmlns$b() {return "request";}
  public void sets$understand(String x) {_s$understand = x;}
  public void setIdentZpravy(String x) {_IdentZpravy = x;}
  public void setReqMsgId(Integer x) {_ReqMsgId = x;}
  public void setMode(String x) {_Mode = x;}
  public String xposOfs$understand(){return XD_XPos + "/@s$understand";}
  public String xposOfIdentZpravy(){return XD_XPos + "/@IdentZpravy";}
  public String xposOfReqMsgId(){return XD_XPos + "/@ReqMsgId";}
  public String xposOfMode(){return XD_XPos + "/@Mode";}
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
  public int xGetModelIndex() {return 1;}
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
    if (gets$understand() != null)
      el.setAttributeNS("soap", XD_Name_s$understand, gets$understand());
    if (getIdentZpravy() != null)
      el.setAttribute(XD_Name_IdentZpravy, getIdentZpravy());
    if (getReqMsgId() != null)
      el.setAttribute(XD_Name_ReqMsgId, String.valueOf(getReqMsgId()));
    if (getMode() != null)
      el.setAttribute(XD_Name_Mode, getMode());
    el.setAttributeNS(javax.xml.XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
      "xmlns:s", "soap");
    el.setAttributeNS(javax.xml.XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
      "xmlns:b", "request");
    return el;
  }
  @Override
  public java.util.List<org.xdef.component.XComponent> xGetNodeList() {
    return new java.util.ArrayList<org.xdef.component.XComponent>();}
  public b$Request() {}
  public b$Request(org.xdef.component.XComponent p,
    String name, String ns, String xPos, String XDPos) {
    XD_NodeName=name; XD_NamespaceURI=ns;
    XD_XPos=xPos;
    XD_Model=XDPos;
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
  }
  public b$Request(org.xdef.component.XComponent p, org.xdef.proc.XXNode xx){
    org.w3c.dom.Element el=xx.getElement();
    XD_NodeName=el.getNodeName(); XD_NamespaceURI=el.getNamespaceURI();
    XD_XPos=xx.getXPos();
    XD_Model=xx.getXMElement().getXDPosition();
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
    if (!"F5EB053B28893DFFE9410E2DF8B74A33".equals(
      xx.getXMElement().getDigest())) { //incompatible element model
      throw new org.xdef.sys.SRuntimeException(
        org.xdef.msg.XDEF.XDEF374);
    }
  }
  private String XD_Name_s$understand="s$understand";
  private String _s$understand;
  private String XD_Name_IdentZpravy="IdentZpravy";
  private String _IdentZpravy;
  private String XD_Name_ReqMsgId="ReqMsgId";
  private Integer _ReqMsgId;
  private String XD_Name_Mode="Mode";
  private String _Mode;
  private org.xdef.component.XComponent XD_Parent;
  private Object XD_Object;
  private String XD_NodeName = "b:Request";
  private String XD_NamespaceURI = "request";
  private int XD_Index = -1;
  private String XD_XPos;
  private String XD_Model="H#s:H/s:Header/b:Request";
  @Override
  public void xSetText(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {}
  @Override
  public void xSetAttr(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {
    if (xx.getXMNode().getXDPosition().endsWith("/@IdentZpravy")) {
      XD_Name_IdentZpravy = xx.getNodeName();
      setIdentZpravy(parseResult.getParsedValue().stringValue());
    } else if (xx.getXMNode().getXDPosition().endsWith("/@Mode")) {
      XD_Name_Mode = xx.getNodeName();
      setMode(parseResult.getParsedValue().stringValue());
    } else if (xx.getXMNode().getXDPosition().endsWith("/@ReqMsgId")) {
      XD_Name_ReqMsgId = xx.getNodeName();
      setReqMsgId(parseResult.getParsedValue().intValue());
    } else {
      XD_Name_s$understand = xx.getNodeName();
      sets$understand(parseResult.getParsedValue().stringValue());
    }
  }
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
public static class s$Body implements org.xdef.component.XComponent{
  public String getxmlns$s() {return "soap";}
  public test.xdef.component.I getb$Ping() {return _b$Ping;}
  public test.xdef.component.H.s$Body.b$PingFlow getb$PingFlow() {return _b$PingFlow;}
  public void setb$Ping(test.xdef.component.I x) {
    if (x!=null && x.xGetXPos() == null)
      x.xInit(this, "b:Ping", "request", "H#s:H/s:Body/$choice/b:Ping");
    _b$Ping = x;
  }
  public void setb$PingFlow(test.xdef.component.H.s$Body.b$PingFlow x) {
    if (x!=null && x.xGetXPos() == null)
      x.xInit(this, "b:PingFlow", "request", "H#s:H/s:Body/$choice/b:PingFlow");
    _b$PingFlow = x;
  }
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
  public int xGetModelIndex() {return 1;}
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
    el.setAttributeNS(javax.xml.XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
      "xmlns:s", "soap");
    for (org.xdef.component.XComponent x: xGetNodeList())
      el.appendChild(x.toXml(doc));
    return el;
  }
  @Override
  public java.util.List<org.xdef.component.XComponent> xGetNodeList() {
    java.util.List<org.xdef.component.XComponent> a =
      new java.util.ArrayList<org.xdef.component.XComponent>();
    org.xdef.component.XComponentUtil.addXC(a, getb$Ping());
    org.xdef.component.XComponentUtil.addXC(a, getb$PingFlow());
    return a;
  }
  public s$Body() {}
  public s$Body(org.xdef.component.XComponent p,
    String name, String ns, String xPos, String XDPos) {
    XD_NodeName=name; XD_NamespaceURI=ns;
    XD_XPos=xPos;
    XD_Model=XDPos;
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
  }
  public s$Body(org.xdef.component.XComponent p, org.xdef.proc.XXNode xx){
    org.w3c.dom.Element el=xx.getElement();
    XD_NodeName=el.getNodeName(); XD_NamespaceURI=el.getNamespaceURI();
    XD_XPos=xx.getXPos();
    XD_Model=xx.getXMElement().getXDPosition();
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
    if (!"44AE5561A4FDCFE88F6118A3CE24E072".equals(
      xx.getXMElement().getDigest())) { //incompatible element model
      throw new org.xdef.sys.SRuntimeException(
        org.xdef.msg.XDEF.XDEF374);
    }
  }
  private test.xdef.component.I _b$Ping;
  private test.xdef.component.H.s$Body.b$PingFlow _b$PingFlow;
  private org.xdef.component.XComponent XD_Parent;
  private Object XD_Object;
  private String XD_NodeName = "s:Body";
  private String XD_NamespaceURI = "soap";
  private int XD_Index = -1;
  private int XD_ndx;
  private String XD_XPos;
  private String XD_Model="H#s:H/s:Body";
  @Override
  public void xSetText(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {}
  @Override
  public void xSetAttr(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {}
  @Override
  public org.xdef.component.XComponent xCreateXChild(org.xdef.proc.XXNode xx) {
    String s = xx.getXMElement().getXDPosition();
    if ("H#s:H/s:Body/$choice/b:Ping".equals(s))
      return new test.xdef.component.I(this, xx);
    return new b$PingFlow(this, xx); // H#s:H/s:Body/$choice/b:PingFlow
  }
  @Override
  public void xAddXChild(org.xdef.component.XComponent xc) {
    xc.xSetNodeIndex(XD_ndx++);
    String s = xc.xGetModelPosition();
    if ("H#s:H/s:Body/$choice/b:Ping".equals(s))
      setb$Ping((test.xdef.component.I) xc);
    else
      setb$PingFlow((b$PingFlow) xc); //H#s:H/s:Body/$choice/b:PingFlow
  }
  @Override
  public void xSetAny(org.w3c.dom.Element el) {}
// </editor-fold>
public static class b$PingFlow implements org.xdef.component.XComponent{
  public String getFlow() {return _Flow;}
  public String getxmlns$b() {return "request";}
  public void setFlow(String x) {_Flow = x;}
  public String xposOfFlow(){return XD_XPos + "/@Flow";}
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
  public int xGetModelIndex() {return 2;}
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
    if (getFlow() != null)
      el.setAttribute(XD_Name_Flow, getFlow());
    el.setAttributeNS(javax.xml.XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
      "xmlns:b", "request");
    return el;
  }
  @Override
  public java.util.List<org.xdef.component.XComponent> xGetNodeList() {
    return new java.util.ArrayList<org.xdef.component.XComponent>();}
  public b$PingFlow() {}
  public b$PingFlow(org.xdef.component.XComponent p,
    String name, String ns, String xPos, String XDPos) {
    XD_NodeName=name; XD_NamespaceURI=ns;
    XD_XPos=xPos;
    XD_Model=XDPos;
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
  }
  public b$PingFlow(org.xdef.component.XComponent p, org.xdef.proc.XXNode xx){
    org.w3c.dom.Element el=xx.getElement();
    XD_NodeName=el.getNodeName(); XD_NamespaceURI=el.getNamespaceURI();
    XD_XPos=xx.getXPos();
    XD_Model=xx.getXMElement().getXDPosition();
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
    if (!"823F6EAC471D3A494A491343318EB870".equals(
      xx.getXMElement().getDigest())) { //incompatible element model
      throw new org.xdef.sys.SRuntimeException(
        org.xdef.msg.XDEF.XDEF374);
    }
  }
  private String XD_Name_Flow="Flow";
  private String _Flow;
  private org.xdef.component.XComponent XD_Parent;
  private Object XD_Object;
  private String XD_NodeName = "b:PingFlow";
  private String XD_NamespaceURI = "request";
  private int XD_Index = -1;
  private String XD_XPos;
  private String XD_Model="H#s:H/s:Body/$choice/b:PingFlow";
  @Override
  public void xSetText(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {}
  @Override
  public void xSetAttr(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {
    XD_Name_Flow = xx.getNodeName();
    setFlow(parseResult.getParsedValue().stringValue());
  }
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
}