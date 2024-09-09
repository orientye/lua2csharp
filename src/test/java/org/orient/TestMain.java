package org.orient;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestMain {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("./src/test/examples");
        if (Files.exists(path) && Files.isDirectory(path)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                for (Path entry : stream) {
                    System.out.println(entry.getFileName());
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("not exist or not directory ");
        }

//        List<String> txtFiles = scanFiles("./src/test/examples", ".lua");
//
//        for (String f : txtFiles) {
//            System.out.println(f);
//        }

//        String fileName = "";
//        String result = Transform.transformFromFileName(fileName);
    }

    private static void testTransform() {

    }

    public static List<String> scanFiles(String directoryPath, String fileExtension) {
        List<String> filePaths = new ArrayList<>();
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            FilenameFilter filter = (dir, name) -> name.endsWith(fileExtension);
            File[] files = directory.listFiles(filter);

            if (files != null) {
                for (File file : files) {
                    filePaths.add(file.getAbsolutePath());
                }
            }
        } else {
            System.out.println("not exist or not directory ");
        }

        return filePaths;
    }

}