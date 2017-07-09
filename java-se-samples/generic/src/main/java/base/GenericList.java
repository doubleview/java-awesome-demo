package base;

import java.util.ArrayList;
import java.util.List;

public class GenericList {
    public static void main(String[] args) {
        // 创建一个只想保存字符串的List集合
        List<String> strList = new ArrayList<>();  // ①
        strList.add("Java开发");
        strList.add("Android开发");
        // 下面代码将引起编译错误
        //strList.add(5);    // ②
        strList.forEach(str -> System.out.println(str.length())); // ③
    }
}

