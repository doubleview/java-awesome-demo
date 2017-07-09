package objects2.finalize;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTest {
    public static void main(String[] args)
            throws Exception {
        // ����һ���ַ�������
        String str = new String("Java����");
        String str2 = new String("java����");
        // ����һ�������ã��ô����������õ�"Java����"�ַ���
        WeakReference wr = new WeakReference(str);  //��
        SoftReference sr = new SoftReference(str2);

        // �ж�str���ú�"Java����"�ַ���֮�������
        str = null;   //��
        str2 = null;
        // ȡ�������������õĶ���
        System.out.println(wr.get());  //��
        System.out.println(sr.get());
        // ǿ����������
        System.gc();
        System.runFinalization();
        // �ٴ�ȡ�������������õĶ���
        System.out.println(wr.get());  //��
        System.out.println(sr.get());
    }
}
