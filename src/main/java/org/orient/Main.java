package org.orient;

import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStream inStream = Main.class.getClassLoader().getResourceAsStream("simple.lua");

        ANTLRInputStream input = new ANTLRInputStream(inStream);
        LuaLexer lexer = new LuaLexer(input);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer
        LuaParser parser = new LuaParser(tokens);
        ParseTree tree = parser.start_(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree

        AnnotatedTree at = new AnnotatedTree(tree);
        at.dump();
    }
}