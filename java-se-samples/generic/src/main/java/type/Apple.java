package type;


public class Apple<T extends Number> {
    T col;

    public static void main(String[] args) {
        Apple<Integer> ai = new Apple<>();
        Apple<Double> ad = new Apple<>();
        // ������뽫��������쳣�����������ͼ��String���ʹ���T�β�
        // ��String����Number�������ͣ����������������
        //Apple<String> as = new Apple<>();        // ��
    }
}
