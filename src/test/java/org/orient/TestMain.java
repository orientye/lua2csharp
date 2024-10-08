package org.orient;

import java.io.File;
import java.nio.file.Files;
import java.util.HashSet;

public class TestMain {
    public static void main(String[] args) throws Exception {
        HashSet<String> excludes = new HashSet<>();
        //excludes.add("Class.lua");
        excludes.add("Table2AnonymousTypes.lua");
        excludes.add("Table2Array.lua");
        excludes.add("Table2Class.lua");
        excludes.add("Table2Dictionary.lua");
        excludes.add("Table2ExpandObject.lua");
        excludes.add("Table2JObject.lua");
        excludes.add("Table2List.lua");
        excludes.add("Table2Struct.lua");
        excludes.add("VarNil.lua");

        File file = new File("./src/test/examples/Class.lua");
        if (file.isFile()) {
            String fileName = file.getName();
            excludes.remove(fileName);
        }
        doFile(file, excludes);
    }

    private static void doFile(File file, HashSet<String> excludes) throws Exception {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isFile()) {
                        doOneFile(f, excludes);
                    } else if (f.isDirectory()) {
                        doFile(f, excludes);
                    }
                }
            }
        } else {
            doOneFile(file, excludes);
        }
    }

    private static void doOneFile(File f, HashSet<String> excludes) throws Exception {
        String fileName = f.getName();
        if (fileName.endsWith("lua")) {
            if (excludes.contains(fileName)) {
                return;
            }
            System.out.println("\n=================================================\n\n");
            System.out.println("lua: " + f.getPath());

            //lua2csharp
            String result = Transform.transformFromFileName(f.getPath());
            String csResult = result.replaceAll("\\s", "");
            System.out.println("result: " + csResult);

            //target
            String path = getCsFileName(f.getPath());
            File csFile = new File(path);
            byte[] bytes = Files.readAllBytes(csFile.toPath());
            String content = new String(bytes);
            String csFileContent = content.replaceAll("\\s", "");
            System.out.println("target: " + csFileContent);

            //compare
            if (csResult.equals(csFileContent)) {
                System.out.println(fileName + " transform success");
            } else {
                System.err.println(fileName + " transform failed!!!");
                System.out.println("\n" + result + "\n");
                assert (false);
            }
        }
    }

    private static String getCsFileName(String luaFileName) {
        assert (luaFileName != null);
        int lastIndexOfDot = luaFileName.lastIndexOf('.');
        assert (lastIndexOfDot != -1);
        return luaFileName.substring(0, lastIndexOfDot) + ".cs";
    }
}