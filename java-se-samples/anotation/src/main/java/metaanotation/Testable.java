package metaanotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
// 定义Testable Annotation将被javadoc工具提取
@Documented
public @interface Testable {
}
