package org.orient;

import java.util.LinkedHashMap;
import java.util.Map;

public class Class {
    private final String name;
    private final Map<String, Symbol> fields = new LinkedHashMap<>();

    public Class(String name) {
        this.name = name;
    }
}
