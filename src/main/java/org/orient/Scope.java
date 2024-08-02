package org.orient;

import java.util.LinkedHashMap;
import java.util.Map;

public class Scope {
    String name;
    Scope enclosingScope; // null if global (outermost) scope
    Map<String, Symbol> symbols = new LinkedHashMap<String, Symbol>();

    public Scope(String name, Scope enclosingScope) {
        this.name = name;
        this.enclosingScope = enclosingScope;
    }

    public void add(Symbol sym) {
        symbols.put(sym.getName(), sym);
        sym.setScope(this);
    }

    public Symbol resolve(String name) {
        Symbol s = symbols.get(name);
        if (s != null) return s;
        if (enclosingScope != null) return enclosingScope.resolve(name);
        return null;
    }

    public Scope getEnclosingScope() {
        return enclosingScope;
    }

    public String toString() {
        return "Scope " + name + " :" + symbols.keySet();
    }
}
