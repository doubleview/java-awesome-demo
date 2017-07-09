package set;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;

public class EnumSetTest2 {
    public static void main(String[] args) {
        Collection c = new HashSet();
        c.clear();
        c.add(Season.FALL);
        c.add(Season.SPRING);
        // ����Collection����������Ԫ��������EnumSet����
        EnumSet enumSet = EnumSet.copyOf(c);   // ��
        System.out.println(enumSet); // ���[SPRING,FALL]
        c.add("Java����");
        c.add("������Java EE��ҵӦ��ʵս");
        // �����������쳣����Ϊc�������Ԫ�ز���ȫ����Ϊö��ֵ
        enumSet = EnumSet.copyOf(c);  // ��
    }
}
