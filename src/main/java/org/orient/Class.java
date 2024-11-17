package org.orient;

import java.util.LinkedHashMap;
import java.util.Map;

public class Class {
    private final String name;
    final Map<String, Symbol> fields = new LinkedHashMap<>();

    final Map<String, Symbol> functions = new LinkedHashMap<>();

    public Class(String name) {
        assert(name != null && !name.isEmpty());
        this.name = UtilString.CapitalizeFirstLetter(name);
    }

    public String getName() {
        return name;
    }
}
