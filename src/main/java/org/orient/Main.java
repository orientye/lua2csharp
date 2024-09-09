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

        InputStream inStream = Main.class.getClassLoader().getResourceAsStream("HelloWorld.lua");
        assert (inStream != null);
        String result = Transform.transform(inStream);
        System.out.println(result);

        System.out.println("\n=================================================\n");
    }
}