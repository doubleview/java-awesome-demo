package objects.polymorphic;


public class InstanceofTest {
    public static void main(String[] args) {
        // ����helloʱʹ��Object�࣬��hello�ı���������Object��
        // Object��������ĸ���, ��hello������ʵ��������String
        Object hello = "Hello";
        // String��Object����ڼ̳й�ϵ�����Խ���instanceof���㡣����true��
        System.out.println("�ַ����Ƿ���Object���ʵ����"
                + (hello instanceof Object));
        System.out.println("�ַ����Ƿ���String���ʵ����"
                + (hello instanceof String)); // ����true��
        // Math��Object����ڼ̳й�ϵ�����Խ���instanceof���㡣����false��
        System.out.println("�ַ����Ƿ���Math���ʵ����"
                + (hello instanceof Math));
        // Stringʵ����Comparable�ӿڣ����Է���true��
        System.out.println("�ַ����Ƿ���Comparable�ӿڵ�ʵ����"
                + (hello instanceof Comparable));
        String a = "Hello";
//		// String����Math��û�м̳й�ϵ�����������������޷�ͨ��
//		System.out.println("�ַ����Ƿ���Math���ʵ����"
//			+ (a instanceof Math));
    }
}
