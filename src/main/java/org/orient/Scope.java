package org.orient;

import java.util.LinkedHashMap;
import java.util.Map;

public class Scope {
    private String name;
    private Scope enclosingScope; // null if global (outermost) scope
    private Map<String, Symbol> symbols = new LinkedHashMap<String, Symbol>();

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

    public String toString() {
        return "Scope " + name + " :" + symbols.keySet();
    }
}
