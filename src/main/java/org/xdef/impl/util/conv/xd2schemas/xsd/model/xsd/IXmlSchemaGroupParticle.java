package org.xdef.impl.util.conv.xd2schemas.xsd.model.xsd;

import org.apache.ws.commons.schema.utils.XmlSchemaObjectBase;

import java.util.List;

public interface IXmlSchemaGroupParticle<M extends XmlSchemaObjectBase> {

    List<M> getItems();
    void addItem(final XmlSchemaObjectBase item);
    void addItems(final List<XmlSchemaObjectBase> items);

}
