package objects2.innerclass;


interface Product {
    double getPrice();

    String getName();
}

public class AnonymousTest {
    public void test(Product p) {
        System.out.println("������һ��" + p.getName()
                + "��������" + p.getPrice());
    }

    public static void main(String[] args) {
        AnonymousTest ta = new AnonymousTest();
        // ����test()����ʱ����Ҫ����һ��Product������
        // �˴�����������ʵ�����ʵ��
        ta.test(new Product() {
            public double getPrice() {
                return 567.8;
            }

            public String getName() {
                return "AGP�Կ�";
            }
        });
    }
}
