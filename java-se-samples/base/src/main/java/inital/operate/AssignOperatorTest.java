package inital.operate;


public class AssignOperatorTest {
    public static void main(String[] args) {
        String str = "Java"; // Ϊ����str��ֵΪJava
        double pi = 3.14; // Ϊ����pi��ֵΪ3.14
        boolean visited = true; // Ϊ����visited��ֵΪtrue
        String str2 = str; // ������str��ֵ����str2
        int a;
        int b;
        int c;
        // ͨ��Ϊa, b , c��ֵ������������ֵ����7
        a = b = c = 7;
        // �������������ֵ��
        System.out.println(a + "\n" + b + "\n" + c);

        double d1 = 12.34;
        double d2 = d1 + 5; // �����ʽ��ֵ����d2
        System.out.println(d2); // ���d2��ֵ�������17.34

    }
}
