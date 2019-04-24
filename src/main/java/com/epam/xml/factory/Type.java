package com.epam.xml.factory;

import java.util.HashMap;
import java.util.Map;

public enum Type {

    DOM("dom"),
    SAX("sax"),
    STAX("stax");

    private String apiType;
    private static final Map<String, Type> VALUES_MAP;

    static {
        VALUES_MAP = new HashMap<>();
        for (Type enumValue : Type.values()) {
            VALUES_MAP.put(enumValue.apiType, enumValue);
        }
    }

    Type(final String builderApiType) {
        apiType = builderApiType;
    }

    public String getXmlValue() {
        return apiType;
    }

    public static Type getEnumValue(final String builderApiType) {
        return VALUES_MAP.get(builderApiType);
    }
}
