package set;

import java.util.HashSet;
import java.util.Iterator;

class R1 {
    int count;

    public R1(int count) {
        this.count = count;
    }

    public String toString() {
        return "R[count:" + count + "]";
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj != null && obj.getClass() == R1.class) {
            R1 r = (R1) obj;
            return this.count == r.count;
        }
        return false;
    }

    public int hashCode() {
        return this.count;
    }
}

public class HashSetTest2 {
    public static void main(String[] args) {
        HashSet hs = new HashSet();
        hs.add(new R1(5));
        hs.add(new R1(-3));
        hs.add(new R1(9));
        hs.add(new R1(-2));
        // 打印HashSet集合，集合元素没有重复
        System.out.println(hs);
        // 取出第一个元素
        Iterator it = hs.iterator();
        R1 first = (R1) it.next();
        // 为第一个元素的count实例变量赋值
        first.count = -3;     // ①
        // 再次输出HashSet集合，集合元素有重复元素
        System.out.println(hs);
        // 删除count为-3的R对象
        hs.remove(new R1(-3));    // ②
        // 可以看到被删除了一个R元素
        System.out.println(hs);
        System.out.println("hs是否包含count为-3的R对象？"
                + hs.contains(new R1(-3))); // 输出false
        System.out.println("hs是否包含count为-2的R对象？"
                + hs.contains(new R1(-2))); // 输出false
    }
}