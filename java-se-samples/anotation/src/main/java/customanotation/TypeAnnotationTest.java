package customanotation;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.List;

// 定义一个简单的Type Annotation，不带任何成员变量
@Target(ElementType.TYPE_USE)
@interface NotNull {
}

// 定义类时使用Type Annotation
@NotNull
public class TypeAnnotationTest
        implements @NotNull /* implements时使用Type Annotation */ Serializable {
    // 方法形参中使用Type Annotation
    // throws时使用Type Annotation
    public static void main(@NotNull String[] args) throws FileNotFoundException {
        Object obj = "fkjava.org";
        // 强制类型转换时使用Type Annotation
        String str = (@NotNull String) obj;
        // 创建对象时使用Type Annotation
        Object win = new @NotNull JFrame("软件");
    }

    // 泛型中使用Type Annotation
    public void foo(List<@NotNull String> info) {
    }
}