package exceptiontype;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ThrowTest2 {
    public static void main(String[] args)
        // Java 6��Ϊ�ٺŴ�������׳�Exception��
        // ���Դ˴������׳�Exception
//		throws Exception
        // Java 7����ٺŴ�������׳��쳣��ʵ�����ͣ�
        // ��˴˴�ֻ�������׳�FileNotFoundException���ɡ�
            throws FileNotFoundException {
        try {
            new FileOutputStream("a.txt");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;        // ��
        }
    }
}