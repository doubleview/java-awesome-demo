package enums.better;

public class GenderTest {
    public static void main(String[] args) {
        Gender g = Gender.valueOf("FEMALE");
        g.setName("Ů");
        System.out.println(g + "����:" + g.getName());
        // ��ʱ����nameֵʱ������ʾ��������
        g.setName("��");
        System.out.println(g + "����:" + g.getName());
    }
}
