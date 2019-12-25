package iterator;

import java.util.Collection;
import java.util.HashSet;

public class PredicateTest {
    public static void main(String[] args) {
        // 创建一个集合
        Collection books = new HashSet();
        books.add(new String("轻量级Java EE企业应用实战"));
        books.add(new String("Java开发"));
        books.add(new String("iOS开发"));
        books.add(new String("Ajax开发"));
        books.add(new String("Android开发"));
        // 使用Lambda表达式（目标类型是Predicate）过滤集合
        books.removeIf(ele -> ((String) ele).length() > 10);
        books.removeIf(ele -> ((String) ele).contains("Java"));
        books.forEach(System.out::println);
    }
}
