package io.stream;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {
    public static void main(String[] args) {
        try (
                // �����ַ�������
                FileReader fr = new FileReader("FileReaderTest.java")) {
            // ����һ������Ϊ32�ġ���Ͳ��
            char[] cbuf = new char[32];
            // ���ڱ���ʵ�ʶ�ȡ���ַ���
            int hasRead = 0;
            // ʹ��ѭ�����ظ���ȡˮ������
            while ((hasRead = fr.read(cbuf)) > 0) {
                // ȡ������Ͳ����ˮ�Σ��ַ��������ַ�����ת�����ַ������룡
                System.out.print(new String(cbuf, 0, hasRead));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
