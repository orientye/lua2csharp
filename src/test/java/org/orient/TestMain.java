package org.orient;

import java.io.File;
import java.nio.file.Files;

public class TestMain {
    public static void main(String[] args) throws Exception {
        File file = new File("./src/test/examples/Return.lua");
        doFile(file);
    }

    public static void doFile(File file) throws Exception {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isFile()) {
                        doOneFile(f);
                    } else if (f.isDirectory()) {
                        doFile(f);
                    }
                }
            }
        } else {
            doOneFile(file);
        }
    }

    private static void doOneFile(File f) throws Exception {
        String fileName = f.getName();
        if (fileName.endsWith("lua")) {
            System.out.println("\n=================================================\n\n");
            System.out.println("lua: " + f.getPath());

            //lua2csharp
            String result = Transform.transformFromFileName(f.getPath());
            String csResult = result.replaceAll("\\s", "");
            System.out.println(csResult);

            //target
            String path = getCsFileName(f.getPath());
            File csFile = new File(path);
            byte[] bytes = Files.readAllBytes(csFile.toPath());
            String content = new String(bytes);
            String csFileContent = content.replaceAll("\\s", "");
            System.out.println(csFileContent);

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