package serialize.resolve;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ResolveTest {
    public static void main(String[] args) {
        try (
                // 创建一个ObjectOutputStream输入流
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream("transient.txt"));
                // 创建一个ObjectInputStream输入流
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream("transient.txt"))) {
            oos.writeObject(Orientation.HORIZONTAL);
            Orientation ori = (Orientation) ois.readObject();
            System.out.println(ori == Orientation.HORIZONTAL);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}