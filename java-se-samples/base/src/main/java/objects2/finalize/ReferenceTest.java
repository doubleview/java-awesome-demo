package objects2.finalize;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTest {
    public static void main(String[] args)
            throws Exception {
        // 创建一个字符串对象
        String str = new String("Java开发");
        String str2 = new String("java开发");
        // 创建一个弱引用，让此弱引用引用到"Java开发"字符串
        WeakReference wr = new WeakReference(str);  //①
        SoftReference sr = new SoftReference(str2);

        // 切断str引用和"Java开发"字符串之间的引用
        str = null;   //②
        str2 = null;
        // 取出弱引用所引用的对象
        System.out.println(wr.get());  //③
        System.out.println(sr.get());
        // 强制垃圾回收
        System.gc();
        System.runFinalization();
        // 再次取出弱引用所引用的对象
        System.out.println(wr.get());  //④
        System.out.println(sr.get());
    }
}
