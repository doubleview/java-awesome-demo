package objects.blockinital;


public class StaticInitTest {
    // 先执行静态初始化块将a静态成员变量赋值为6
    static {
        a = 6;
    }

    // 再将a静态成员变量赋值为9
    static int a = 9;

    public static void main(String[] args) {
        // 下面代码将输出9
        System.out.println(StaticInitTest.a);
    }
}

