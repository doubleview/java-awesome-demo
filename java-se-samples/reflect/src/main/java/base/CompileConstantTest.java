package base;


class MyTest {
    static {
        System.out.println("��̬��ʼ����...");
    }

    // ʹ��һ���ַ���ֱ����Ϊstatic final���������ֵ
    static final String compileConstant = "Java����";
}

public class CompileConstantTest {
    public static void main(String[] args) {
        // ���ʡ����MyTest�е�compileConstant�����
        System.out.println(MyTest.compileConstant);   // ��
    }
}
