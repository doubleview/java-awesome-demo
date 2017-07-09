package apibase.args;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KeyboardInTest {
    public static void main(String[] args) throws Exception {
        // 以System.in节点流为基础，创建一个BufferedReader对象
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        String line = null;
        // 逐行读取键盘输入
        while ((line = br.readLine()) != null) {
            System.out.println("用户键盘输入是:" + line);
        }
    }
}
