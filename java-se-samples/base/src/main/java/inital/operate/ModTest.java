package inital.operate;


public class ModTest {
    public static void main(String[] args) {
        double a = 5.2;
        double b = 3.1;
        double mod = a % b;

        System.out.println(mod); // mod��ֵΪ2.1
        System.out.println("5��0.0����Ľ����:" + 5 % 0.0); // �������:NaN
        System.out.println("-5.0��0����Ľ����:" + -5.0 % 0); // �������:NaN
        System.out.println("0��5.0����Ľ����:" + 0 % 5.0); // ���0.0
        System.out.println("0��0.0����Ľ����:" + 0 % 0.0); // �������:NaN
        // ������뽫�����쳣��java.lang.ArithmeticException: / by zero
        System.out.println("-5��0����Ľ����:" + -5 % 0);
    }
}
