package set;

import java.util.Date;
import java.util.TreeSet;

public class TreeSetErrorTest2 {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet();
        // ��TreeSet�����������������
        ts.add(new String("Java����"));
        ts.add(new Date());   // ��
    }
}
