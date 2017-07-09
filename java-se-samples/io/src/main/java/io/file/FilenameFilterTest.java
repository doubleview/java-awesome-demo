package io.file;

import java.io.File;

public class FilenameFilterTest {
    public static void main(String[] args) {
        File file = new File(".");
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        // ʹ��Lambda���ʽ��Ŀ������ΪFilenameFilter��ʵ���ļ���������
        // ����ļ�����.java��β�������ļ���Ӧһ��·��������true
        String[] nameList = file.list((dir, name) -> name.endsWith(".java")
                || new File(name).isDirectory());
        for (String name : nameList) {
            System.out.println(name);
        }
    }
}
