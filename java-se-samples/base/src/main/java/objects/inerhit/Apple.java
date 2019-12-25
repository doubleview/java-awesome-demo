package objects.inerhit;


public class Apple extends Fruit {
    public static void main(String[] args) {
        // 鍒涘缓Apple瀵硅薄
        Apple a = new Apple();
        // Apple瀵硅薄鏈韩娌℃湁weight鎴愬憳鍙橀噺
        // 鍥犱负Apple鐨勭埗绫绘湁weight鎴愬憳鍙橀噺锛屼篃鍙互璁块棶Apple瀵硅薄鐨剋eight鎴愬憳鍙橀噺
        a.weight = 56;
        // 璋冪敤Apple瀵硅薄鐨刬nfo()鏂规硶
        a.info();
    }
}
