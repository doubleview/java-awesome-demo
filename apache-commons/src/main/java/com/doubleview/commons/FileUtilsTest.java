package com.doubleview.commons;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

public class FileUtilsTest {

    public static void main(String[] args) throws Exception {
//        Collection<File> files = FileUtils.listFiles(new File("/Users/huchengchao/理财/香帅的北大金融课"), new String[]{"pdf"}, true);
//
//        files.forEach(file -> {
//            String filename = file.getName();
//            String newFilename = StringUtils.removeStart(filename, "xs");
//            System.out.println(newFilename);
//            try {
//                FileUtils.moveFile(file, new File(file.getParent(), newFilename));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });

        String path = "/Users/huchengchao/IdeaProjects/51NB/java-awesome-demo/java-se-samples";
        Collection<File> sourceFileCollection = FileUtils.listFiles(new File(path), new String[]{"java"}, true);

        sourceFileCollection.forEach(file -> {
            try {
                String gbkString = FileUtils.readFileToString(file, "GBK");
                if (!gbkString.contains("�")) {
                    FileUtils.write(file, gbkString, "UTF-8");
                    System.out.println(String.format("convert file [%s] success", file.getAbsolutePath()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}