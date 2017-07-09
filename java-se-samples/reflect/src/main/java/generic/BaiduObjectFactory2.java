package generic;

import javax.swing.*;
import java.util.Date;

public class BaiduObjectFactory2 {
    public static <T> T getInstance(Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // 获取实例后无须类型转换
        Date d = BaiduObjectFactory2.getInstance(Date.class);
        JFrame f = BaiduObjectFactory2.getInstance(JFrame.class);
    }
}
