package iterator;

import java.util.Collection;
import java.util.HashSet;

public class CollectionEach {
    public static void main(String[] args) {
        // ����һ������
        Collection books = new HashSet();
        books.add("������Java EE��ҵӦ��ʵս");
        books.add("Java����");
        books.add("Android����");
        // ����forEach()������������
        books.forEach(System.out::println);
    }
}
