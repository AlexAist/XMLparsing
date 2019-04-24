package com.epam.xml.factory;

import java.security.InvalidParameterException;

public class Builder {

    public static Factory createBuilder(final Type type) {
        switch (type) {
            case DOM:
                return new DOM();
            case SAX:
                return new SAX();
            case STAX:
                return new StAX();
            default:
                throw new InvalidParameterException("Can't create builder for " + type + " parameter.");
        }
    }
}
