package objects2.finals;


public class StringJoinTest {
    public static void main(String[] args) {
        String s1 = "Java";
        // s2�������õ��ַ������Ա���ʱ��ȷ��������
        // ���s2ֱ�����ó����������е�"Java"�ַ���
        String s2 = "" + "Java";
        System.out.println(s1 == s2);
        // ����2���ַ���ֱ����
        String str1 = "";     //��
        String str2 = "Java";     //��
        // ��str1��str2������������
        String s3 = str1 + str2;
        System.out.println(s1 == s3);
    }
}
