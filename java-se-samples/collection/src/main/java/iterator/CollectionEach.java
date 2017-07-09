package iterator;

import java.util.Collection;
import java.util.HashSet;

public class CollectionEach {
    public static void main(String[] args) {
        // 创建一个集合
        Collection books = new HashSet();
        books.add("轻量级Java EE企业应用实战");
        books.add("Java开发");
        books.add("Android开发");
        // 调用forEach()方法遍历集合
        books.forEach(System.out::println);
    }
}
