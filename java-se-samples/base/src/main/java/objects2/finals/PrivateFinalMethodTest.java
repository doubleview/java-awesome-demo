package objects2.finals;

public class PrivateFinalMethodTest {
    private final void test() {
    }
}

class Sub extends PrivateFinalMethodTest {
    // 下面方法定义将不会出现问题
    public void test() {
    }
}
