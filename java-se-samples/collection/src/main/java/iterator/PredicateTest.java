package iterator;

import java.util.Collection;
import java.util.HashSet;

public class PredicateTest {
    public static void main(String[] args) {
        // ����һ������
        Collection books = new HashSet();
        books.add(new String("������Java EE��ҵӦ��ʵս"));
        books.add(new String("Java����"));
        books.add(new String("iOS����"));
        books.add(new String("Ajax����"));
        books.add(new String("Android����"));
        // ʹ��Lambda���ʽ��Ŀ��������Predicate�����˼���
        books.removeIf(ele -> ((String) ele).length() > 10);
        books.removeIf(ele -> ((String) ele).contains("Java"));
        books.forEach(System.out::println);
    }
}
