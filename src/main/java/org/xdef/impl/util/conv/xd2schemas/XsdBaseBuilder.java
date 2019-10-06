package org.xdef.impl.util.conv.xd2schemas;

import org.apache.ws.commons.schema.*;
import org.xdef.XDParser;
import org.xdef.XDValue;
import org.xdef.impl.XElement;
import org.xdef.impl.XNode;
import org.xdef.model.XMData;
import org.xdef.model.XMOccurrence;

import javax.xml.namespace.QName;
import java.security.InvalidParameterException;

public class XsdBaseBuilder {

    private final XmlSchema schema;

    public XsdBaseBuilder(XmlSchema schema) {
        this.schema = schema;
    }

    public void addElement(final XmlSchemaElement element) {
        schema.getItems().add(element);
    }

    public void addComplexType(final XmlSchemaComplexType complexType) {
        schema.getItems().add(complexType);
    }

    /**
     * Create named xsd element
     * Example: <element name="elem_name">
     */
    public XmlSchemaElement createElement(final String name, final XElement xElement) {
        XmlSchemaElement elem = new XmlSchemaElement(schema, false);
        //elem.setName(name);
        elem.setMinOccurs(xElement.getOccurence().minOccurs());
        elem.setMaxOccurs((xElement.isUnbounded() || xElement.isMaxUnlimited()) ? Long.MAX_VALUE : xElement.getOccurence().maxOccurs());
        return elem;
    }

    public void resolveElementName(final XmlSchemaElement elem) {
        final String name = elem.getName();
        final String newName = getResolvedName(name);

        if (!name.equals(newName)) {
            elem.setName(newName);
        } else if (XmlSchemaForm.QUALIFIED.equals(schema.getElementFormDefault()) && isUnqualifiedName(name)) {
            elem.setForm(XmlSchemaForm.UNQUALIFIED);
        }
    }

    private String getResolvedName(final String name) {
        // Element's name contains target namespace prefix, we can remove this prefix
        if (schema.getSchemaNamespacePrefix() != null && name.startsWith(schema.getSchemaNamespacePrefix()) && name.charAt(schema.getSchemaNamespacePrefix().length()) == ':') {
            return name.substring(schema.getSchemaNamespacePrefix().length() + 1);
        }

        return name;
    }

    private boolean isUnqualifiedName(final String name) {
        // Element's name without namespace prefix, while xml is using target namespace
        return name.indexOf(':') == -1 && schema.getSchemaNamespacePrefix() != null;
    }

    /**
     * Create complexType element
     * Output: <complexType>
     */
    public XmlSchemaComplexType createComplexType() {
        return new XmlSchemaComplexType(schema, false);
    }

    public XmlSchemaAttribute createAttribute(final String name, final XMData xmData) {
        XmlSchemaAttribute attr = new XmlSchemaAttribute(schema, false);
        final String importNamespace = xmData.getNSUri();
        final String nodeName = xmData.getName();
        if (importNamespace != null && XD2XsdUtils.isExternalRef(nodeName, importNamespace, schema)) {
            attr.getRef().setTargetQName(new QName(importNamespace, nodeName));
        } else {
            attr.setName(name);
            // TODO: Handling of reference namespaces?
            if (xmData.getRefTypeName() != null) {
                attr.setSchemaTypeName(new QName("", xmData.getRefTypeName()));
            } else {
                attr.setSchemaTypeName(XD2XsdUtils.parserNameToQName(xmData.getValueTypeName()));
            }

            String newName = getResolvedName(name);
            if (!name.equals(newName)) {
                attr.setName(newName);
            } else if (XmlSchemaForm.QUALIFIED.equals(schema.getAttributeFormDefault()) && isUnqualifiedName(name)) {
                attr.setForm(XmlSchemaForm.UNQUALIFIED);
            }
        }

        if (xmData.isOptional() || xmData.getOccurence().isOptional()) {
            attr.setUse(XmlSchemaUse.OPTIONAL);
        } else if (xmData.isRequired() || xmData.getOccurence().isRequired()) {
            attr.setUse(XmlSchemaUse.REQUIRED);
        }

        return attr;
    }

    public XmlSchemaSimpleContent createSimpleContent(final XMData xmData) {
        XmlSchemaSimpleContent content = new XmlSchemaSimpleContent();
        XmlSchemaSimpleContentExtension contentExtension = new XmlSchemaSimpleContentExtension();

        final String parserName = xmData.getParserName();
        XDValue parseMethod = xmData.getParseMethod();
        // TODO: Has to be instance of XDParser?
        if (parseMethod instanceof XDParser) {
            contentExtension.setBaseTypeName(XD2XsdUtils.parserNameToQName(parserName));
        }

        content.setContent(contentExtension);
        return content;
    }

    /**
     *
     * @param groupType
     * @return
     */
    public XmlSchemaGroupParticle createGroup(short groupType, final XMOccurrence occurrence) {
        XmlSchemaGroupParticle particle = null;
        switch (groupType) {
            case XNode.XMSEQUENCE: {
                particle = new XmlSchemaSequence();
                break;
            }
            case XNode.XMMIXED: {
                particle = new XmlSchemaAll();
                break;
            }
            case XNode.XMCHOICE: {
                particle = new XmlSchemaChoice();
                break;
            }
            default: {
                throw new InvalidParameterException("Unknown groupType");
            }
        }

        particle.setMinOccurs(occurrence.minOccurs());
        particle.setMaxOccurs((occurrence.isUnbounded() || occurrence.isMaxUnlimited()) ? Long.MAX_VALUE : occurrence.maxOccurs());

        return particle;
    }
}
