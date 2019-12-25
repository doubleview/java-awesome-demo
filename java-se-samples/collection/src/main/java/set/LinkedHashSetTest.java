package set;

import java.util.LinkedHashSet;

public class LinkedHashSetTest {
    public static void main(String[] args) {
        LinkedHashSet books = new LinkedHashSet();
        books.add("Java开发");
        books.add("轻量级Java EE企业应用实战");
        System.out.println(books);
        // 删除 Java开发
        books.remove("Java开发");
        // 重新添加 Java开发
        books.add("Java开发");
        System.out.println(books);
    }
}
