package map;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map map = new HashMap();
        // 成对放入多个key-value对
        map.put("Java开发", 109);
        map.put("iOS开发", 10);
        map.put("Ajax开发", 79);
        // 多次放入的key-value对中value可以重复
        map.put("轻量级Java EE企业应用实战", 99);
        // 放入重复的key时，新的value会覆盖原有的value
        // 如果新的value覆盖了原有的value，该方法返回被覆盖的value
        System.out.println(map.put("iOS开发", 99)); // 输出10
        System.out.println(map); // 输出的Map集合包含4个key-value对
        // 判断是否包含指定key
        System.out.println("是否包含值为 iOS开发 key："
                + map.containsKey("iOS开发")); // 输出true
        // 判断是否包含指定value
        System.out.println("是否包含值为 99 value："
                + map.containsValue(99)); // 输出true
        // 获取Map集合的所有key组成的集合，通过遍历key来实现遍历所有key-value对
        for (Object key : map.keySet()) {
            // map.get(key)方法获取指定key对应的value
            System.out.println(key + "-->" + map.get(key));
        }
        map.remove("Ajax开发"); // 根据key来删除key-value对。
        System.out.println(map); // 输出结果不再包含 Ajax开发=79 的key-value对
    }
}
