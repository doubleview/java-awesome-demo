package iterator;

import java.util.Collection;
import java.util.HashSet;

public class ForeachTest {
    public static void main(String[] args) {
        // 创建集合、添加元素的代码与前一个程序相同
        Collection books = new HashSet();
        books.add(new String("轻量级Java EE企业应用实战"));
        books.add(new String("Java开发"));
        books.add(new String("Android开发"));
        for (Object obj : books) {
            // 此处的book变量也不是集合元素本身
            String book = (String) obj;
            System.out.println(book);
            if (book.equals("Android开发")) {
                // 下面代码会引发ConcurrentModificationException异常
                books.remove(book);     //①
            }
        }
        System.out.println(books);
    }
}
