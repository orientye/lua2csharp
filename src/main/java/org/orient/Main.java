package org.orient;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("\n=================================================\n");

        InputStream inStream = Main.class.getClassLoader().getResourceAsStream("table.lua");
        assert (inStream != null);
        CharStream charStream = CharStreams.fromStream(inStream);

        LuaLexer lexer = new LuaLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LuaParser parser = new LuaParser(tokens);
        ParseTree tree = parser.start_();
        System.out.println(tree.toStringTree(parser));

        AnnotatedTree annotatedTree = new AnnotatedTree(tree);
        annotatedTree.dump();
        System.out.println("\n=================================================");

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

        System.out.println("\n=================================================\n");
    }
}