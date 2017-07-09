
package base;

public class R<T> {
    // 下面代码错误，不能在静态变量声明中使用类型形参
//	static T info;
    T age;

    public void foo(T msg) {
    }
    // 下面代码错误，不能在静态方法声明中使用类型形参
//	public static void bar(T msg){}

}

