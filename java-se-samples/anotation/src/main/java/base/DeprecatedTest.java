package base;


class Apple1 {
    // ����info�����ѹ�ʱ
    @Deprecated
    public void info() {
        System.out.println("Apple��info����");
    }
}

public class DeprecatedTest {
    public static void main(String[] args) {
        // ����ʹ��info����ʱ���ᱻ����������
        new Apple1().info();
    }
}

