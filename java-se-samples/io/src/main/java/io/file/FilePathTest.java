package io.file;

import java.io.File;
import java.io.IOException;


public class FilePathTest {

    public static void main(String[] args) throws IOException {

        File f = new File("../a");
        System.out.println(f.getPath());
        System.out.println(f.getName());
        System.out.println(f.getCanonicalPath());
        System.out.println(f.getCanonicalFile().getPath());
        System.out.println(f.getParentFile().getCanonicalPath());
        System.out.println(f.getAbsolutePath());
        System.out.println(f.getAbsoluteFile().getPath());
        System.out.println("--------------------------------");
        System.out.println("size ：" + f.length());
        System.out.println("----------------------------------");
        for (File file : f.getParentFile().listFiles()) {
            System.out.println(file.getName());
        }
    }
}
