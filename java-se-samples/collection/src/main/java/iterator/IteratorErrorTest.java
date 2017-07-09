package iterator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class IteratorErrorTest {
    public static void main(String[] args) {
        // 创建集合、添加元素的代码与前一个程序相同
        Collection books = new HashSet();
        books.add("轻量级Java EE企业应用实战");
        books.add("Java开发");
        books.add("Android开发");
        // 获取books集合对应的迭代器
        Iterator it = books.iterator();
        while (it.hasNext()) {
            String book = (String) it.next();
            System.out.println(book);
            if (book.equals("Android开发")) {
                // 使用Iterator迭代过程中，不可修改集合元素,下面代码引发异常
                books.remove(book);
            }
        }
    }
}

