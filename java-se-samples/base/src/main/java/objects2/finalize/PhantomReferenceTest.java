package objects2.finalize;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceTest {
    public static void main(String[] args) throws Exception {
        // ����һ���ַ�������
        String str = new String("Java����");
        // ����һ�����ö���
        ReferenceQueue rq = new ReferenceQueue();
        // ����һ�������ã��ô����������õ�"Java����"�ַ���
        PhantomReference pr = new PhantomReference(str, rq);
        System.out.println(pr.get());

        // �ж�str���ú�"Java����"�ַ���֮�������
        str = null;
        // ȡ�������������õĶ��󣬲�����ͨ�������û�ȡ�����õĶ������Դ˴����null
        System.out.println(pr.get());  //��
        // ǿ����������
        System.gc();
        System.runFinalization();
        // ��������֮�������ý����������ö�����
        // ȡ�����ö��������Ƚ�������е�������pr���бȽ�
        System.out.println(rq.poll() == pr);   //��
    }
}
