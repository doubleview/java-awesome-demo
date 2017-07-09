package objects2.java8wrap;


public class UnsignedTest {
    public static void main(String[] args) {
        byte b = -3;
        // ��byte���͵�-3ת��Ϊ�޷���������
        System.out.println("byte���͵�-3��Ӧ���޷���������"
                + Byte.toUnsignedInt(b)); // ���253

        System.out.println(Integer.MAX_VALUE);
        // ָ��ʹ��16���ƽ����޷�������
        int val = Integer.parseUnsignedInt("ffffffff", 16);
        System.out.println(val); // ���171

        System.out.println(Integer.parseUnsignedInt("2147483648"));

        // ��-12ת��Ϊ�޷���int�ͣ�Ȼ��ת��Ϊ16���Ƶ��ַ���
        System.out.println(Integer.toUnsignedString(-12, 16)); // ���fffffff4
        System.out.println(Integer.toUnsignedString(-2147483648));
        // ��������ת��Ϊ�޷������������
        // ��������ת��Ϊ�޷������������
        System.out.println(Integer.divideUnsigned(-2, 3));
        // ��������ת��Ϊ�޷����������������
        System.out.println(Integer.remainderUnsigned(-2, 7));
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.toUnsignedString(-9223372036854775808l));
    }
}
