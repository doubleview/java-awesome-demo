package objects2.innerclass;


public class TeachableProgrammerTest {
    public static void main(String[] args) {
        TeachableProgrammer tp = new TeachableProgrammer("���");
        //ֱ�ӵ���TeachableProgrammer���Programmer��̳е���work����
        tp.work();
        //�����ϵ��õ���Closure��work������
        //ʵ�����ǻص�TeachableProgrammer��teach����
        tp.getCallbackReference().work();
    }
}
