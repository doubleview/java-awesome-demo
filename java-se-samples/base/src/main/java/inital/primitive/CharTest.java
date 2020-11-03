package inital.primitive;


public class CharTest {
    public static void main(String[] args) {
        // 直接指定单个字符作为字符值
        char aChar = 'a';
        // 使用转义字符来作为字符值
        char enterChar = '\r';
        // 使用Unicode编码值来指定字符值
        char ch = '\u9999';
        // 将输出一个'香'字符
        System.out.println(ch);
        // 直接将一个char变量当成int类型变量使用
        int zhongValue = 'a';
        System.out.println(zhongValue);
        // 直接把一个0～65535范围内的int整数赋给一个char变量
        char c = 97;
        System.out.println(c);
    }
}

