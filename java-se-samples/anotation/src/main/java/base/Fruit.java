package base;

public class Fruit {
    public void info() {
        System.out.println("ˮ����info����...");
    }
}

class Apple extends Fruit {
    // ʹ��@Overrideָ�����淽��������д���෽��
    //@Override
    public void inf0() {
        System.out.println("ƻ����дˮ����info����...");
    }
}
