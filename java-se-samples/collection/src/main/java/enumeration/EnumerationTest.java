package enumeration;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class EnumerationTest {
    public static void main(String[] args) {
        Vector v = new Vector();
        v.add("Java����");
        v.add("������Java EE��ҵӦ��ʵս");
        Hashtable scores = new Hashtable();
        scores.put("����", 78);
        scores.put("��ѧ", 88);
        Enumeration em = v.elements();
        while (em.hasMoreElements()) {
            System.out.println(em.nextElement());
        }
        Enumeration keyEm = scores.keys();
        while (keyEm.hasMoreElements()) {
            Object key = keyEm.nextElement();
            System.out.println(key + "--->"
                    + scores.get(key));
        }
    }
}
