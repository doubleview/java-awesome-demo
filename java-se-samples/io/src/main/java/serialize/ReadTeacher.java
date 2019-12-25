package serialize;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ReadTeacher {
    public static void main(String[] args) {
        try (
                // 创建一个ObjectInputStream输出流
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream("teacher.txt"))) {
            // 依次读取ObjectInputStream输入流中的四个对象
            Teacher t1 = (Teacher) ois.readObject();
            Teacher t2 = (Teacher) ois.readObject();
            Person p = (Person) ois.readObject();
            Teacher t3 = (Teacher) ois.readObject();
            // 输出true
            System.out.println("t1的student引用和p是否相同：" + (t1.getStudent() == p));
            // 输出true
            System.out.println("t2的student引用和p是否相同：" + (t2.getStudent() == p));
            // 输出true
            System.out.println("t2和t3是否是同一个对象：" + (t2 == t3));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
