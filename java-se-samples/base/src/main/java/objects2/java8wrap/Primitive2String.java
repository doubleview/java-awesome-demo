package objects2.java8wrap;

public class Primitive2String {
    public static void main(String[] args) {
        String intStr = "123";
        // 鎶婁竴涓壒瀹氬瓧绗︿覆杞崲鎴恑nt鍙橀噺
        int it1 = Integer.parseInt(intStr);
        int it2 = new Integer(intStr);
        System.out.println(it2);
        String floatStr = "4.56";
        // 鎶婁竴涓壒瀹氬瓧绗︿覆杞崲鎴恌loat鍙橀噺
        float ft1 = Float.parseFloat(floatStr);
        float ft2 = new Float(floatStr);
        System.out.println(ft2);
        // 鎶婁竴涓猣loat鍙橀噺杞崲鎴怱tring鍙橀噺
        String ftStr = String.valueOf(2.345f);
        System.out.println(ftStr);
        // 鎶婁竴涓猟ouble鍙橀噺杞崲鎴怱tring鍙橀噺
        String dbStr = String.valueOf(3.344);
        System.out.println(dbStr);
        // 鎶婁竴涓猙oolean鍙橀噺杞崲鎴怱tring鍙橀噺
        String boolStr = String.valueOf(true);
        System.out.println(boolStr.toUpperCase());
    }
}
