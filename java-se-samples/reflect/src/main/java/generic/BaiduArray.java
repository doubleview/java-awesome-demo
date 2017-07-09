package generic;

import java.lang.reflect.Array;

public class BaiduArray {
    // 对Array的newInstance方法进行包装
    @SuppressWarnings("unchecked")
    public static <T> T[] newInstance(Class<T> componentType, int length) {
        return (T[]) Array.newInstance(componentType, length);  //①
    }

    public static void main(String[] args) {
        // 使用baiduArray的newInstance()创建一维数组
        String[] arr = BaiduArray.newInstance(String.class, 10);
        // 使用baiduArray的newInstance()创建二维数组
        // 在这种情况下，只要设置数组元素的类型是int[]即可。
        int[][] intArr = BaiduArray.newInstance(int[].class, 5);
        arr[5] = "thinking in java";
        // intArr是二维数组，初始化该数组的第二个数组元素
        // 二维数组的元素必须是一维数组
        intArr[1] = new int[]{23, 12};
        System.out.println(arr[5]);
        System.out.println(intArr[1][1]);
    }
}
