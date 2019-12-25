package base;

import java.util.ArrayList;
import java.util.List;

public class ListErr {
    public static void main(String[] args) {
        // 创建一个只想保存字符串的List集合
        List strList = new ArrayList();
        strList.add("Java开发");
        strList.add("Android开发");
        // "不小心"把一个Integer对象"丢进"了集合
        strList.add(5);     // ①
        strList.forEach(str -> System.out.println(((String) str).length())); // ②
    }
}
