package base;

import java.io.*;

public class AutoCloseTest {
    public static void main(String[] args) throws IOException {
        try (
                // ��������ʼ�������ɹرյ���Դ
                // try�����Զ��ر���������Դ��
                BufferedReader br = new BufferedReader(
                        new FileReader("AutoCloseTest.java"));
                PrintStream ps = new PrintStream(new
                        FileOutputStream("a.txt"))) {
            // ʹ��������Դ
            System.out.println(br.readLine());
            ps.println("ׯ�������Ժ���");
        }
    }
}
