package customanotation;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.List;

// ����һ���򵥵�Type Annotation�������κγ�Ա����
@Target(ElementType.TYPE_USE)
@interface NotNull {
}

// ������ʱʹ��Type Annotation
@NotNull
public class TypeAnnotationTest
        implements @NotNull /* implementsʱʹ��Type Annotation */ Serializable {
    // �����β���ʹ��Type Annotation
    // throwsʱʹ��Type Annotation
    public static void main(@NotNull String[] args) throws FileNotFoundException {
        Object obj = "fkjava.org";
        // ǿ������ת��ʱʹ��Type Annotation
        String str = (@NotNull String) obj;
        // ��������ʱʹ��Type Annotation
        Object win = new @NotNull JFrame("���");
    }

    // ������ʹ��Type Annotation
    public void foo(List<@NotNull String> info) {
    }
}