package objects.constructor;


public class ConstructorOverload {
    public String name;
    public int count;

    // �ṩ�޲����Ĺ�����
    public ConstructorOverload() {
    }

    // �ṩ�����������Ĺ�������
    // �Ըù��������صĶ���ִ�г�ʼ��
    public ConstructorOverload(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public static void main(String[] args) {
        // ͨ���޲�������������ConstructorOverload����
        ConstructorOverload oc1 = new ConstructorOverload();
        // ͨ���в�������������ConstructorOverload����
        ConstructorOverload oc2 = new ConstructorOverload("������Java EE��ҵӦ��ʵս", 300000);
        System.out.println(oc1.name + " " + oc1.count);
        System.out.println(oc2.name + " " + oc2.count);
    }
}

