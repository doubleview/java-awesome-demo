package objects.enclosure;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static java.lang.System.out;

public class StaticImportTest {
    public static void main(String[] args) {
        // out是java.lang.System类的静态成员变量，代表标准输出
        // PI是java.lang.Math类的静态成员变量，表示π常量
        out.println(PI);
        // 直接调用Math类的sqrt静态方法
        out.println(sqrt(256));
    }
}
