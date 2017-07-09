package objects2.finals;


public class StringJoinTest {
    public static void main(String[] args) {
        String s1 = "Java";
        // s2变量引用的字符串可以编译时就确定出来，
        // 因此s2直接引用常量池中已有的"Java"字符串
        String s2 = "" + "Java";
        System.out.println(s1 == s2);
        // 定义2个字符串直接量
        String str1 = "";     //①
        String str2 = "Java";     //②
        // 将str1和str2进行连接运算
        String s3 = str1 + str2;
        System.out.println(s1 == s3);
    }
}
