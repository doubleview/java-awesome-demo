package classloader;


import java.util.Properties;

public class Hello {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println("运行Hello的参数：" + arg);
        }

        Properties properties = System.getProperties();
        for (Object key : properties.keySet()) {
            System.out.println(key + "=" + properties.get(key));
        }
    }
}
