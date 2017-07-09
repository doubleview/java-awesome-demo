package objects.variable;

public class ScopeTest1 {
    // 定义一个类成员变量作为循环变量
    static int i;

    public static void main(String[] args) {
        for (i = 0; i < 10; i++) {
            System.out.println("Hello");
        }
    }
}
