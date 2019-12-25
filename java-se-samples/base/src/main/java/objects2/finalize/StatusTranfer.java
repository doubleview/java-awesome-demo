package objects2.finalize;

public class StatusTranfer {
    public static void test() {
        String a = new String("轻量级Java EE企业应用实战"); //①
        a = new String("Java开发");   //②
    }

    public static void main(String[] args) {
        test();     //③
    }
}
