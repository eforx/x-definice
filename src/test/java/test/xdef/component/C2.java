// This file was generated by org.xdef.component.GenXComponent.
// XDPosition: "C#Town/Street/House".
// Any modifications to this file will be lost upon recompilation.
package test.xdef.component;
public class C2 implements org.xdef.component.XComponent{
  public Integer getNum() {return _Num;}
  public java.util.List<C1> listOfPerson() {return _Person;}
  public void setNum(Integer x) {_Num = x;}
  public void addPerson(C1 x) {
    if (x != null) {
        if (x.xGetXPos() == null)
          x.xInit(this, "Person", null, "C#Town/Street/House/Person");
      _Person.add(x);
    }
  }
  public String xposOfNum(){return XD_XPos + "/@Num";}
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
    if (doc == null) {
      doc = org.xdef.xml.KXmlUtils.newDocument(
        XD_NamespaceURI, XD_NodeName, null);
      el = doc.getDocumentElement();
    } else {
      el = doc.createElementNS(XD_NamespaceURI, XD_NodeName);
      if (doc.getDocumentElement() == null) doc.appendChild(el);
    }
    if (getNum() != null)
      el.setAttribute("Num", String.valueOf(getNum()));
    for (org.xdef.component.XComponent x: XD_List==null?xGetNodeList():XD_List)
      el.appendChild(x.toXml(doc));
    XD_List = null;
    return el;
  }
  @Override
  public java.util.List<org.xdef.component.XComponent> xGetNodeList() {
    java.util.ArrayList<org.xdef.component.XComponent> a =
      new java.util.ArrayList<org.xdef.component.XComponent>();
    org.xdef.component.XComponentUtil.addXC(a, listOfPerson());
    return XD_List = a;
  }
  public C2() {}
  public C2(org.xdef.component.XComponent p,
    String name, String ns, String xPos, String XDPos) {
    XD_NodeName=name; XD_NamespaceURI=ns;
    XD_XPos=xPos;
    XD_Model=XDPos;
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
  }
  public C2(org.xdef.component.XComponent p, org.xdef.proc.XXNode xx){
    org.w3c.dom.Element el=xx.getElement();
    XD_NodeName=el.getNodeName(); XD_NamespaceURI=el.getNamespaceURI();
    XD_XPos=xx.getXPos();
    XD_Model=xx.getXMElement().getXDPosition();
    XD_Object = (XD_Parent=p)!=null ? p.xGetObject() : null;
    if (!"C08AD32FE3D734C66EFCAD9AB991FE6A".equals(
      xx.getXMElement().getDigest())) { //incompatible element model
      throw new org.xdef.sys.SRuntimeException(
        org.xdef.msg.XDEF.XDEF374);
    }
  }
  private Integer _Num;
  private final java.util.List<C1> _Person = new java.util.ArrayList<C1>();
  private org.xdef.component.XComponent XD_Parent;
  private Object XD_Object;
  private String XD_NodeName = "House";
  private String XD_NamespaceURI;
  private int XD_Index = -1;
  private int XD_ndx;
  private String XD_XPos;
  private java.util.List<org.xdef.component.XComponent> XD_List;
  private String XD_Model="C#Town/Street/House";
  @Override
  public void xSetText(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {}
  @Override
  public void xSetAttr(org.xdef.proc.XXNode xx,
    org.xdef.XDParseResult parseResult) {
    setNum(parseResult.getParsedValue().intValue());
  }
  @Override
  public org.xdef.component.XComponent xCreateXChild(org.xdef.proc.XXNode xx)
    {return new C1(this, xx);}
  @Override
  public void xAddXChild(org.xdef.component.XComponent xc) {
    xc.xSetNodeIndex(XD_ndx++);
    listOfPerson().add((C1) xc); //C#Town/Street/House/Person
  }
  @Override
  public void xSetAny(org.w3c.dom.Element el) {}
// </editor-fold>
}