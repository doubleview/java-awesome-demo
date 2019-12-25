package list;

import java.util.*;

class A {
    public boolean equals(Object obj) {
        return true;
    }
}

public class ListTest2 {
    public static void main(String[] args) {
        List books = new ArrayList();
        books.add(new String("轻量级Java EE企业应用实战"));
        books.add(new String("疯狂Java讲义"));
        books.add(new String("疯狂Android讲义"));
        System.out.println(books);
        // 删除集合中A对象，将导致第一个元素被删除
        books.remove(new A());     // ①
        System.out.println(books);
        // 删除集合中A对象，再次删除集合中第一个元素
        books.remove(new A());     // ②
        System.out.println(books);
    }
}
