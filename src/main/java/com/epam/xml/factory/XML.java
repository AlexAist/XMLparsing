package com.epam.xml.factory;

import java.util.HashMap;
import java.util.Map;

public enum XML {

    BANKS("banks"),
    STATE_BANK("state-bank"),
    TYPE("type"),
    DEPOSITOR("depositor"),
    DEPOSITOR_NAME("depositor-name"),
    DEPOSITOR_SURNAME("depositor-surname"),
    ACCOUNT_ID("account-id"),
    AMOUNT_ON_DEPOSIT("amount-on-deposit"),
    PROFITABILITY("profitability"),
    TIME_CONSTRAINS("time-constrains"),
    STATUS("status"),
    OWNER("owner"),
    CREATE_DATE("createDate"),
    ID_ATTRIBUTE("id"),
    NAME_ATTRIBUTE("name"),
    COUNTRY_ATTRIBUTE("country");

    private String value;
    private static final Map<String, XML> MAP;

    static {
        MAP = new HashMap<>();
        for (XML enumValue : XML.values()) {
            MAP.put(enumValue.value, enumValue);
        }
    }

    XML(final String xmlStringValue) {
        value = xmlStringValue;
    }

    public String getValue() {
        return value;
    }

    public static XML getEnumValue(final String xmlStringValue) {
        return MAP.get(xmlStringValue);
    }
}
