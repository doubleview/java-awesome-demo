package objects2.innerclass;

interface A {
    void test();
}

public class ATest {
    public static void main(String[] args) {
        final int age = 8;     // ��
        // ������뽫�ᵼ�±������
        // ����age�ֲ������������ڲ�������ˣ����age�൱�ڱ�final������
       // age = 2;
        A a = new A() {
            public void test() {
                // ��Java 8��ǰ������佫��ʾ����age����ʹ��final����
                // ��Java 8��ʼ�������ڲ��ࡢ�ֲ��ڲ���������ʷ�final�ľֲ�����
                //System.out.println(age);
            }
        };
        a.test();
    }
}

