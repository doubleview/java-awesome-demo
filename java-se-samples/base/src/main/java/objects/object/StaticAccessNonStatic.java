package objects.object;


public class StaticAccessNonStatic {
    public void info() {
        System.out.println("�򵥵�info����");
    }

    public static void main(String[] args) {
        // ��Ϊmain()�����Ǿ�̬��������info()�ǷǾ�̬������
        // ����main()�������Ǹ��౾�������Ǹ����ʵ����
        // ���ʡ�Ե�this�޷�ָ����Ч�Ķ���
        // info();
    }
}
