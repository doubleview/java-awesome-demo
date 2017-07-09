package base;

public class ATest1 {
    public static void main(String[] args) {
        // 创建A类的实例
        A a = new A();
        // 让a实例的类变量a的值自加
        A.a++;
        System.out.println(A.a);
    }
}
