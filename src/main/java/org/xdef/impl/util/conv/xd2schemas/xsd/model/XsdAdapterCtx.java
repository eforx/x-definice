package org.xdef.impl.util.conv.xd2schemas.xsd.model;

import org.apache.ws.commons.schema.XmlSchemaCollection;
import org.apache.ws.commons.schema.utils.NamespaceMap;
import org.xdef.impl.util.conv.xd2schemas.xsd.util.XsdLogger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.xdef.impl.util.conv.xd2schemas.xsd.util.XsdLoggerDefs.*;

/**
 * Basic XSD context for transformation x-definition to XSD schema
 */
public class XsdAdapterCtx {

    private final int logLevel;

    /**
     * Names of created xsd schemas
     */
    private Set<String> schemaNames = null;

    /**
     * Namespace context per x-definition
     * Key:     x-definition name
     * Value:   namespace context
     */
    private Map<String, NamespaceMap> namespaceCtx = null;

    /**
     * Schemas location based on x-definition
     * Key:     namespace URI
     * Value:   location
     */
    private Map<String, XmlSchemaImportLocation> schemaLocationsCtx = null;

    /**
     * Schemas locations which are created in post-processing
     * Key:     schema namespace URI
     * Value:   schema location
     */
    private Map<String, XmlSchemaImportLocation> extraSchemaLocationsCtx = null;

    /**
     * Collection of created XSD schemas
     */
    private XmlSchemaCollection xmlSchemaCollection = null;

    public XsdAdapterCtx(int logLevel) {
        this.logLevel = logLevel;
    }

    public void init() {
        schemaNames = new HashSet<String>();
        namespaceCtx = new HashMap<String, NamespaceMap>();
        schemaLocationsCtx = new HashMap<String, XmlSchemaImportLocation>();
        extraSchemaLocationsCtx = new HashMap<String, XmlSchemaImportLocation>();
        xmlSchemaCollection = new XmlSchemaCollection();
    }

    /**
     * Add schema name to name set
     * @param name  x-definition name
     */
    public void addSchemaName(final String name) throws RuntimeException {
        if (!schemaNames.add(name)) {
            if (XsdLogger.isError(logLevel)) {
                XsdLogger.printC(ERROR, XSD_ADAPTER_CTX, "Schema with this name has been already processed! Name=" + name);
            }

            throw new RuntimeException("X-definition name duplication");
        }
    }

    /**
     * Add namespace to namespace context
     * @param nsPrefix            x-definition name
     * @param namespaceCtx      x-definition namespace context
     */
    public void addNamespaceCtx(final String nsPrefix, final NamespaceMap namespaceCtx) {
        if (namespaceCtx.containsKey(nsPrefix)) {
            if (XsdLogger.isWarn(logLevel)) {
                XsdLogger.printC(WARN, XSD_ADAPTER_CTX, "Namespace context already exists! NamespacePrefix=" + nsPrefix);
            }
            return;
        }

        if (XsdLogger.isInfo(logLevel)) {
            XsdLogger.printC(INFO, XSD_ADAPTER_CTX, "Add namespace context. NamespacePrefix=" + nsPrefix);
        }

        namespaceCtx.put(nsPrefix, namespaceCtx);
    }

    /**
     *
     * @param nsUri
     * @param importLocation
     */
    public void addSchemaLocation(final String nsUri, final XmlSchemaImportLocation importLocation) {
        if (schemaLocationsCtx.containsKey(nsUri)) {
            if (XsdLogger.isWarn(logLevel)) {
                XsdLogger.printP(INFO, PREPROCESSING, "Schema import already exists for namespace URI. NamespaceURI=" + nsUri);
            }
            return;
        }

        if (XsdLogger.isInfo(logLevel)) {
            XsdLogger.printP(INFO, PREPROCESSING, "Add schema import. NamespaceURI=" + nsUri + ", FileName=" + importLocation.getFileName());
        }

        schemaLocationsCtx.put(nsUri, importLocation);
    }

    public final Set<String> getSchemaNames() {
        return schemaNames;
    }

    public final Map<String, NamespaceMap> getNamespaceCtx() {
        return namespaceCtx;
    }

    public final Map<String, XmlSchemaImportLocation> getSchemaLocationsCtx() {
        return schemaLocationsCtx;
    }

    public final Map<String, XmlSchemaImportLocation> getExtraSchemaLocationsCtx() {
        return extraSchemaLocationsCtx;
    }

    public final XmlSchemaCollection getXmlSchemaCollection() {
        return xmlSchemaCollection;
    }
}