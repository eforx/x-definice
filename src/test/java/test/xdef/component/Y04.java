// This file was generated by cz.syntea.xdef.component.GenXComponent.
// XDPosition: "Y04#Part".
// Any modifications to this file will be lost upon recompilation.
package test.xdef.component;
public class Y04 extends test.xdef.TestXComponents_Y04 implements java.io.Serializable,Runnable,cz.syntea.xdef.component.XComponent{
  public String getTwo() {return _Two;}
  public void setTwo(String x) {_Two = x;}
  public String xposOfTwo(){return XD_XPos + "/@Two";}
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
    if (getJedna() != null)
      el.setAttribute("One", getJedna());
    if (getTwo() != null)
      el.setAttribute("Two", getTwo());
    return el;
  }
  @Override
  public java.util.List<cz.syntea.xdef.component.XComponent> xGetNodeList() {
    return new java.util.ArrayList<cz.syntea.xdef.component.XComponent>();}
  public Y04() {}
  public Y04(cz.syntea.xdef.component.XComponent p,
    String name, String ns, String xPos, String XDPos) {
    XD_NodeName=name; XD_NamespaceURI=ns;
    XD_XPos=xPos;
    XD_Model=XDPos;
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
  }
  public Y04(cz.syntea.xdef.component.XComponent p, cz.syntea.xdef.proc.XXNode xx){
    org.w3c.dom.Element el=xx.getElement();
    XD_NodeName=el.getNodeName(); XD_NamespaceURI=el.getNamespaceURI();
    XD_XPos=xx.getXPos();
    XD_Model=xx.getXMElement().getXDPosition();
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
    if (!"8BE503F9D2FE84E7FED4540CA9FAEEF9".equals(
      xx.getXMElement().getDigest())) { //incompatible element model
      throw new cz.syntea.xdef.sys.SRuntimeException(
        cz.syntea.xdef.msg.XDEF.XDEF374);
    }
  }
  private String _Two;
  private cz.syntea.xdef.component.XComponent XD_Parent;
  private Object XD_Object;
  private String XD_NodeName = "Part";
  private String XD_NamespaceURI;
  private int XD_Index = -1;
  private String XD_XPos;
  private String XD_Model="Y04#Part";
  @Override
  public void xSetText(cz.syntea.xdef.proc.XXNode xx,
    cz.syntea.xdef.XDParseResult parseResult) {}
  @Override
  public void xSetAttr(cz.syntea.xdef.proc.XXNode xx,
    cz.syntea.xdef.XDParseResult parseResult) {
    if (xx.getXMNode().getXDPosition().endsWith("/@One"))
      setJedna(parseResult.getParsedValue().stringValue());
    else setTwo(parseResult.getParsedValue().stringValue());
  }
  @Override
  public cz.syntea.xdef.component.XComponent xCreateXChild(cz.syntea.xdef.proc.XXNode xx)
    {return null;}
  @Override
  public void xAddXChild(cz.syntea.xdef.component.XComponent xc) {}
  @Override
  public void xSetAny(org.w3c.dom.Element el) {}
// </editor-fold>
}