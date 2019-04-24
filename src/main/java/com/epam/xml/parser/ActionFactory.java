package com.epam.xml.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ActionFactory {
    private static Map<String, Action> actionMap = new HashMap<>();

    static {
        actionMap.put("parse", new Parser());
    }

    private ActionFactory() {
    }

    public static Optional<Action> defineCommand(String value) {
        if (value == null) {
            return Optional.empty();
        }
        return Optional.of(actionMap.get(value));
    }
}
