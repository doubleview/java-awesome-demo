package objects2.java8wrap;

public class WrapperClassCompare {
    public static void main(String[] args) {
        Integer a = new Integer(6);
        // ���true
        System.out.println("6�İ�װ��ʵ���Ƿ����5.0" + (a > 5.0));
        System.out.println("�Ƚ�2����װ���ʵ���Ƿ���ȣ�" + (new Integer(2) == new Integer(2))); // ���false
        // ͨ���Զ�װ�䣬����ѻ�������ֵ��ֵ����װ���ʵ��
        Integer ina = 2;
        Integer inb = 2;
        System.out.println("����2�Զ�װ����Ƿ���ȣ�" + (ina == inb)); // ���true
        Integer biga = 128;
        Integer bigb = 128;
        System.out.println("����128�Զ�װ����Ƿ���ȣ�" + (biga == bigb)); // ���false
    }
}
