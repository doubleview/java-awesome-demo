package customanotation.test1;


public class MyTest {
    // ʹ��@Testableע��ָ���÷����ǿɲ��Ե�
    @Testable
    public static void m1() {
    }

    public static void m2() {
    }

    // ʹ��@Testableע��ָ���÷����ǿɲ��Ե�
    @Testable
    public static void m3() {
        throw new IllegalArgumentException("���������ˣ�");
    }

    public static void m4() {
    }

    // ʹ��@Testableע��ָ���÷����ǿɲ��Ե�
    @Testable
    public static void m5() {
    }

    public static void m6() {
    }

    // ʹ��@Testableע��ָ���÷����ǿɲ��Ե�
    @Testable
    public static void m7() {
        throw new RuntimeException("����ҵ������쳣��");
    }

    public static void m8() {
    }
}
