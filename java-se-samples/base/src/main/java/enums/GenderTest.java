package enums;

public class GenderTest {
    public static void main(String[] args) {
        // ͨ��Enum��valueOf()��������ȡָ��ö�����ö��ֵ
        Gender g = Enum.valueOf(Gender.class, "FEMALE");
        // ֱ��Ϊö��ֵ��nameʵ��������ֵ
        g.name = "Ů";
        // ֱ�ӷ���ö��ֵ��nameʵ������
        System.out.println(g + "����:" + g.name);
    }
}
