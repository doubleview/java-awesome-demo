package objects.object;

public class PersonTest {
    public static void main(String[] args) {
        // ʹ��Peron�ඨ��һ��Person���͵ı���
        Person p;
        // ͨ��new�ؼ��ֵ���Person��Ĺ�����������һ��Personʵ����
        // ����Personʵ������p������
        p = new Person();

        // ����p��nameʵ��������ֱ��Ϊ�ñ�����ֵ��
        p.name = "���";
        // ����p��say����������say()����ʱ������һ���βΣ�
        // ���ø÷�������Ϊ�β�ָ��һ��ֵ
        p.say("Java���Ժܼ򵥣�ѧϰ�����ף�");
        // ֱ�����p��nameʵ������������� ���
        System.out.println(p.name);

        // ��p������ֵ��ֵ��p2����
        Person p2 = p;
    }
}
