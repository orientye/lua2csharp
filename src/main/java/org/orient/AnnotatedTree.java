package org.orient;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;
import java.util.Map;

public class AnnotatedTree {

    Map<ParseTree, Scope> scopes = new HashMap<>();
    Map<ParseTree, Symbol> symbols = new HashMap<>();
    private ParseTree ast = null;

    public AnnotatedTree(ParseTree parseTree) {
        this.ast = parseTree;
    }

    public void dump() {
        System.out.println(ast.toStringTree());
    }
}
