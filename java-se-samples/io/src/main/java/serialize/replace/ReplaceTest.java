package serialize.replace;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReplaceTest {
    public static void main(String[] args) {
        try (
                // 创建一个ObjectOutputStream输出流
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream("replace.txt"));
                // 创建一个ObjectInputStream输入流
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream("replace.txt"))) {
            Person per = new Person("孙悟空", 500);
            // 系统将per对象转换字节序列并输出
            oos.writeObject(per);
            // 反序列化读取得到的是ArrayList
            ArrayList list = (ArrayList) ois.readObject();
            System.out.println(list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

