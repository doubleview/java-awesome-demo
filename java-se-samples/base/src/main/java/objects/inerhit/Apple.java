package objects.inerhit;


public class Apple extends Fruit {
    public static void main(String[] args) {
        // ����Apple����
        Apple a = new Apple();
        // Apple������û��weight��Ա����
        // ��ΪApple�ĸ�����weight��Ա������Ҳ���Է���Apple�����weight��Ա����
        a.weight = 56;
        // ����Apple�����info()����
        a.info();
    }
}
