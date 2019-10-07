package org.xdef.impl.util.conv.xd2schemas.xsd.util;

import org.apache.ws.commons.schema.XmlSchema;
import org.apache.ws.commons.schema.XmlSchemaComplexType;
import org.apache.ws.commons.schema.XmlSchemaElement;
import org.apache.ws.commons.schema.XmlSchemaForm;
import org.apache.ws.commons.schema.constants.Constants;
import org.xdef.XDConstants;

import javax.xml.namespace.QName;

public class XD2XsdUtils {

    public static QName parserNameToQName(final String parserName) {
        if ("CDATA".equals(parserName)) {
            return Constants.XSD_STRING;
        } else if ("string".equals(parserName)) {
            return Constants.XSD_STRING;
        } else if ("int".equals(parserName)) {
            return Constants.XSD_INT;
        } else if ("long".equals(parserName)) {
            return Constants.XSD_LONG;
        } else if ("double".equals(parserName)) {
            return Constants.XSD_DOUBLE;
        } else if ("float".equals(parserName)) {
            return Constants.XSD_FLOAT;
        } else if ("enum".equals(parserName)) {
            return Constants.XSD_STRING;
        } else if ("gMonth".equals(parserName)) {
            return Constants.XSD_MONTH;
        } else if ("gMonthDay".equals(parserName)) {
            return Constants.XSD_MONTHDAY;
        } else if ("gYear".equals(parserName) || "ISOyear".equals(parserName)) {
            return Constants.XSD_YEAR;
        } else if ("gDay".equals(parserName)) {
            return Constants.XSD_DAY;
        } else if ("time".equals(parserName)) {
            return Constants.XSD_TIME;
        } else if ("dateTime".equals(parserName) || "ISOdateTime".equals(parserName)) {
            return Constants.XSD_DATETIME;
        } else if ("ISOyearMonth".equals(parserName)) {
            return Constants.XSD_YEARMONTH;
        } else if ("ISOdate".equals(parserName)) {
            return Constants.XSD_DATE;
        } else {

            System.out.println("Unknown reference type parser: "+ parserName);
        }

        return null;
    }

    // If name contains ":" or reference has different namespace, then element contains external reference
    public static boolean isExternalRef(final String nodeName, final String namespaceUri, final XmlSchema schema) {
        return nodeName.indexOf(':') != -1 && (namespaceUri != null && !namespaceUri.equals(schema.getTargetNamespace()));
    }

    public static String getReferenceSystemId(final String reference) {
        int xdefSystemSeparatorPos = reference.indexOf('#');
        if (xdefSystemSeparatorPos != -1) {
            return reference.substring(0, xdefSystemSeparatorPos);
        }

        return null;
    }

    public static String getReferenceName(final String reference) {
        int xdefNamespaceSeparatorPos = reference.indexOf(':');
        if (xdefNamespaceSeparatorPos != -1) {
            return reference.substring(xdefNamespaceSeparatorPos + 1);
        }

        int xdefSystemSeparatorPos = reference.indexOf('#');
        if (xdefSystemSeparatorPos != -1) {
            return reference.substring(xdefSystemSeparatorPos + 1);
        }

        return reference;
    }

    public static boolean isDefaultNamespacePrefix(final String prefix) {
        return Constants.XML_NS_PREFIX.equals(prefix)
                || Constants.XMLNS_ATTRIBUTE.equals(prefix)
                || XDConstants.XDEF_NS_PREFIX.equals(prefix);
    }

    public static void resolveElementName(final XmlSchema schema, final XmlSchemaElement elem) {
        final String name = elem.getName();
        final String newName = getResolvedName(schema, name);

        if (!name.equals(newName)) {
            elem.setName(newName);
        } else if (XmlSchemaForm.QUALIFIED.equals(schema.getElementFormDefault()) && isUnqualifiedName(schema, name)) {
            elem.setForm(XmlSchemaForm.UNQUALIFIED);
        }
    }

    public static String getResolvedName(final XmlSchema schema, final String name) {
        // Element's name contains target namespace prefix, we can remove this prefix
        if (schema.getSchemaNamespacePrefix() != null && name.startsWith(schema.getSchemaNamespacePrefix()) && name.charAt(schema.getSchemaNamespacePrefix().length()) == ':') {
            return name.substring(schema.getSchemaNamespacePrefix().length() + 1);
        }

        return name;
    }

    public static boolean isUnqualifiedName(final XmlSchema schema, final String name) {
        // Element's name without namespace prefix, while xml is using target namespace
        return name.indexOf(':') == -1 && schema.getSchemaNamespacePrefix() != null;
    }

    public static void addElement(final XmlSchema schema, final XmlSchemaElement element) {
        schema.getItems().add(element);
    }

    public static void addComplexType(final XmlSchema schema, final XmlSchemaComplexType complexType) {
        schema.getItems().add(complexType);
    }
}
