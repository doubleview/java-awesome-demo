package objects2.finals;


public class FinalReplaceTest {
    public static void main(String[] args) {
        // 下面定义了4个final“宏变量”
        final int a = 5 + 2;
        final double b = 1.2 / 3;
        final String str = "" + "Java";
        final String book = "Java开发：" + 99.0;
        // 下面的book2变量的值因为调用了方法，所以无法在编译时被确定下来
        final String book2 = "Java开发：" + String.valueOf(99.0);  //①
        System.out.println(book == "Java开发：99.0");
        System.out.println(book2 == "Java开发：99.0");
    }
}
