package org.orient;

import java.io.InputStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("\n===================================\n");

        InputStream inStream = Main.class.getClassLoader().getResourceAsStream("return.lua");
        assert (inStream != null);
        CharStream charStream = CharStreams.fromStream(inStream);

        LuaLexer lexer = new LuaLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LuaParser parser = new LuaParser(tokens);
        ParseTree tree = parser.start_(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree

        AnnotatedTree annotatedTree = new AnnotatedTree(tree);
        annotatedTree.dump();
        System.out.println("\n===================================");

        ParseTreeWalker walker = new ParseTreeWalker();

        //Pass
        PassScopeAndType PassScopeAndType = new PassScopeAndType(annotatedTree);
        walker.walk(PassScopeAndType, tree);
        PassScopeAndType.Reset();
        walker.walk(PassScopeAndType, tree);

        //Pass
        PassTransformation shifter = new PassTransformation(annotatedTree, tokens);
        walker.walk(shifter, tree);
        System.out.println(shifter.rewriter.getText());

        System.out.println("\n===================================\n");
    }
}