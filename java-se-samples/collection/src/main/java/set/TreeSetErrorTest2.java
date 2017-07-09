package set;

import java.util.Date;
import java.util.TreeSet;

public class TreeSetErrorTest2 {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet();
        // 向TreeSet集合中添加两个对象
        ts.add(new String("Java开发"));
        ts.add(new Date());   // ①
    }
}
