package org.orient;

import java.io.File;
import java.nio.file.Files;

public class TestMain {
    public static void main(String[] args) throws Exception {

        File folder = new File("./src/test/examples");
        doFolder(folder);
    }

    public static void doFolder(File folder) throws Exception {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isFile()) {
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
                                assert(false);
                            }
                        }
                    } else if (f.isDirectory()) {
                        doFolder(f);
                    }
                }
            }
        } else {
            System.err.println(folder.getName() + " is not a directory");
        }
    }

    private static String getCsFileName(String luaFileName) {
        assert (luaFileName != null);
        int lastIndexOfDot = luaFileName.lastIndexOf('.');
        assert (lastIndexOfDot != -1);
        return luaFileName.substring(0, lastIndexOfDot) + ".cs";
    }
}