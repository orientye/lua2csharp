package org.orient;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.InputStream;

public class Transform {
    public static String transform(InputStream inputStream) throws Exception {
        assert (inputStream != null);
        CharStream charStream = CharStreams.fromStream(inputStream);

        LuaLexer lexer = new LuaLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LuaParser parser = new LuaParser(tokens);
        ParseTree tree = parser.start_();

        AnnotatedTree annotatedTree = new AnnotatedTree(tree);

        ParseTreeWalker walker = new ParseTreeWalker();

        //Pass
        PassScopeAndType PassScopeAndType = new PassScopeAndType(annotatedTree);
        walker.walk(PassScopeAndType, tree);
        PassScopeAndType.Reset();
        walker.walk(PassScopeAndType, tree);

        //Pass
        PassTransformation passTransformation = new PassTransformation(annotatedTree, tokens);
        walker.walk(passTransformation, tree);

        String result = passTransformation.getResult();
        return result;
    }
}