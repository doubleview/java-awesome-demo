package metaanotation;


// 使用@Inheritable修饰的Base类
@Inheritable
class Base {
}

// TestInheritable类只是继承了Base类，
// 并未直接使用@Inheritable Annotiation修饰
public class InheritableTest extends Base {
    public static void main(String[] args) {
        // 打印TestInheritable类是否具有@Inheritable修饰
        System.out.println(InheritableTest.class
                .isAnnotationPresent(Inheritable.class));
    }
}

