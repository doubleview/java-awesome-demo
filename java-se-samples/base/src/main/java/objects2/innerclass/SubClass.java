package objects2.innerclass;


public class SubClass extends Out.In {
    //��ʾ����SubClass�Ĺ�����
    public SubClass(Out out) {
        //ͨ�������Out������ʽ����In�Ĺ�����
        out.super("hello");
    }
}
