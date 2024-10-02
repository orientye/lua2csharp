package org.orient;

import java.util.LinkedHashMap;
import java.util.Map;

public class Class {
    private final String name;
    final Map<String, Symbol> fields = new LinkedHashMap<>();

    public Class(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
