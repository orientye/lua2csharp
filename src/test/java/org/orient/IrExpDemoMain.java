package org.orient;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.File;
import java.nio.file.Files;

/**
 * Small demo entry to verify the IR-based pipeline on Exp.lua.
 *
 * It loads src/test/examples/Exp.lua, runs Transform.transformWithIr,
 * and compares the result against src/test/examples/Exp.cs after
 * stripping all whitespace characters.
 */
public class IrExpDemoMain {

    public static void main(String[] args) throws Exception {
        String luaPath = "./src/test/examples/Exp.lua";
        String csPath = "./src/test/examples/Exp.cs";

        // run IR-based transformation
        CharStream charStream = CharStreams.fromFileName(luaPath);
        String result = Transform.transformWithIr(charStream);
        String csResult = normalize(result);
        System.out.println("IR result:\n" + result);

        // load target C# file
        File csFile = new File(csPath);
        byte[] bytes = Files.readAllBytes(csFile.toPath());
        String targetContent = new String(bytes);
        String csFileContent = normalize(targetContent);
        System.out.println("Target:\n" + targetContent);

        // compare
        if (csResult.equals(csFileContent)) {
            System.out.println("Exp.lua IR transform success");
        } else {
            System.err.println("Exp.lua IR transform failed!!!");
        }
    }

    private static String normalize(String s) {
        // remove single-line comments starting with //
        String withoutComments = s.replaceAll("(?m)//.*$", "");
        // remove all whitespace characters
        return withoutComments.replaceAll("\\s", "");
    }
}

