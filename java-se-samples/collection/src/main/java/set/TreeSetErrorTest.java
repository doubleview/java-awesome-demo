package set;

import java.util.TreeSet;

class Err {
}

public class TreeSetErrorTest {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet();
        // 向TreeSet集合中添加两个Err对象
        ts.add(new Err());
        ts.add(new Err());  //①
    }
}
