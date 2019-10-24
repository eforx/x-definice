package org.xdef.impl.util.conv.xd2schemas.xsd.builder.facet.array;

import org.xdef.XDContainer;
import org.xdef.XDNamedValue;
import org.xdef.XDValue;

public class EnumerationRegexBuilder {

    public static String regex(final XDNamedValue[] params) {

        String pattern = "";

        for (XDNamedValue param : params) {
            if ("enumeration".equals(param.getName())) {
                if (param.getValue().getItemId() == XDValue.XD_CONTAINER) {
                    for (XDValue value : ((XDContainer) param.getValue()).getXDItems()) {
                        // Remove all new lines and leading whitespaces on new line
                        String strValue = value.stringValue().replaceAll("\\n *", " ");
                        if (!pattern.isEmpty()) {
                            pattern += "|" + strValue;
                        } else {
                            pattern = strValue;
                        }
                    }
                }
            }
        }

        return pattern;
    }
}