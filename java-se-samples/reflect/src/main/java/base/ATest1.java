package base;

public class ATest1 {
    public static void main(String[] args) {
        // ����A���ʵ��
        A a = new A();
        // ��aʵ���������a��ֵ�Լ�
        A.a++;
        System.out.println(A.a);
    }
}
