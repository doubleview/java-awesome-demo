package method;

class MyUtil<E> {
    public static <Z> MyUtil<Z> nil() {
        return null;
    }

    public static <Z> MyUtil<Z> cons(Z head, MyUtil<Z> tail) {
        return null;
    }

    E head() {
        return null;
    }
}

public class InferenceTest {
    public static void main(String[] args) {
        // ����ͨ��������ֵ��Ŀ��������ƶ����Ͳ���ΪString
        MyUtil<String> ls = MyUtil.nil();
        // ����ʹ����������ڵ���nil()����ʱָ�����Ͳ���������
        MyUtil<String> mu = MyUtil.nil();
        // �ɵ���cons��������Ĳ����������ƶ����Ͳ���ΪInteger
        MyUtil.cons(42, MyUtil.nil());
        // ����ʹ����������ڵ���nil()����ʱָ�����Ͳ���������
        MyUtil.cons(42, MyUtil.nil());

        // ϣ��ϵͳ���ƶϳ�����nil()�������Ͳ���ΪString���ͣ�
        // ��ʵ����Java 8��Ȼ�ƶϲ�����������������뱨��
//		String s = MyUtil.nil().head();
        String s = MyUtil.<String>nil().head();
    }
}