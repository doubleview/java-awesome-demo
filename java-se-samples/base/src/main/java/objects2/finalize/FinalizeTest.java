package objects2.finalize;


public class FinalizeTest {
    private static FinalizeTest ft = null;

    public void info() {
        System.out.println("������Դ�����finalize����");
    }

    public static void main(String[] args) throws Exception {
        // ����FinalizeTest������������ɻָ�״̬
        new FinalizeTest();
        // ֪ͨϵͳ������Դ����
		System.gc();  //��
        // ǿ���������ջ��Ƶ��ÿɻָ������finalize()����
//		Runtime.getRuntime().runFinalization();   //��
        System.runFinalization();   //��
        ft.info();
    }

    public void finalize() {
        System.out.println("ִ��finalize����");
        // ��tf���õ���ͼ���յĿɻָ����󣬼��ɻָ��������±�ɿɴ�
        ft = this;
    }
}

