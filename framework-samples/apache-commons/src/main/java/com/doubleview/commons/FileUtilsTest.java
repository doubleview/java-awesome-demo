package com.doubleview.commons;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;

public class FileUtilsTest {

    public static void main(String[] args) throws Exception {
        Collection<File> files = FileUtils.listFiles(new File("/Users/huchengchao/Downloads"), new String[]{"mp3", "pdf", "m4a", "mp4"}, true);

        files.forEach(file -> {
            String filename = file.getAbsolutePath();
            System.out.println(filename);
            FileUtils.deleteQuietly(file);
        });
    }
}