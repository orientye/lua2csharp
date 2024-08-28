package org.orient;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;
import java.util.Map;

public class AnnotatedTree {

    private ParseTree ast = null;

    Map<ParseTree, Scope> scopesOfNodes = new HashMap<>();

    Map<ParseTree, Symbol> symbolsOfNodes = new HashMap<>();

    public AnnotatedTree(ParseTree parseTree) {
        this.ast = parseTree;
    }

    public void dump() {
        System.out.println(ast.toStringTree());
        System.out.println(ast.getText());
    }
}
