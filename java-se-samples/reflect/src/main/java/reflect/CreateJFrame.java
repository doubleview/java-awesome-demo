package reflect;

import java.lang.reflect.Constructor;

/**/
public class CreateJFrame {
    public static void main(String[] args) throws Exception {
        // ��ȡJFrame��Ӧ��Class����
        Class<?> jframeClazz = Class.forName("javax.swing.JFrame");
        // ��ȡJFrame�д�һ���ַ��������Ĺ�����
        Constructor ctor = jframeClazz.getConstructor(String.class);
        // ����Constructor��newInstance������������
        Object obj = ctor.newInstance("���Դ���");
        // ���JFrame����
        System.out.println(obj);
    }
}
