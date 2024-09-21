package org.orient;

import java.util.LinkedHashMap;
import java.util.Map;

public class Scope {
    private final String name;
    private final Scope enclosingScope; // null if global (outermost) scope
    private final Map<String, Symbol> symbols = new LinkedHashMap<>();

    public Scope(String name, Scope enclosingScope) {
        this.name = name;
        this.enclosingScope = enclosingScope;
    }

    public void add(Symbol sym) {
        symbols.put(sym.getName(), sym);
    }

    public Symbol resolve(String name) {
        Symbol s = symbols.get(name);
        if (s != null) return s;
        if (enclosingScope != null) return enclosingScope.resolve(name);
        return null;
    }

    public String getName() { return name; }

    public String toString() {
        return "Scope " + name + " :" + symbols.keySet();
    }
}
