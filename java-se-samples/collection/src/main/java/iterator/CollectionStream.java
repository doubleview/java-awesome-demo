package iterator;

import java.util.Collection;
import java.util.HashSet;

public class CollectionStream {
    public static void main(String[] args) {
        // 创建books集合、为books集合添加元素的代码与8.2.5小节的程序相同。
        Collection books = new HashSet();
        books.add(new String("轻量级Java EE企业应用实战"));
        books.add(new String("Java开发"));
        books.add(new String("iOS开发"));
        books.add(new String("Ajax开发"));
        books.add(new String("Android开发"));
        // 统计书名包含“”子串的图书数量
        System.out.println(books.stream()
                .filter(ele -> ((String) ele).contains(""))
                .count()); // 输出4
        // 统计书名包含“Java”子串的图书数量
        System.out.println(books.stream()
                .filter(ele -> ((String) ele).contains("Java"))
                .count()); // 输出2
        // 统计书名字符串长度大于10的图书数量
        System.out.println(books.stream()
                .filter(ele -> ((String) ele).length() > 10)
                .count()); // 输出2
        // 先调用Collection对象的stream()方法将集合转换为Stream,
        // 再调用Stream的mapToInt()方法获取原有的Stream对应的IntStream
        books.stream().mapToInt(ele -> ((String) ele).length())
                // 调用forEach()方法遍历IntStream中每个元素
                .forEach(System.out::println);// 输出8  11  16  7  8
    }
}
