package iterator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class IteratorEach {
    public static void main(String[] args) {
        // 创建集合、添加元素的代码与前一个程序相同
        Collection books = new HashSet();
        books.add("轻量级Java EE企业应用实战");
        books.add("Java开发");
        books.add("Android开发");
        // 获取books集合对应的迭代器
        Iterator it = books.iterator();
        // 使用Lambda表达式（目标类型是Comsumer）来遍历集合元素
        it.forEachRemaining(obj -> System.out.println("迭代集合元素：" + obj));
    }
}
