package metaanotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
// ����Testable Annotation����javadoc������ȡ
@Documented
public @interface Testable {
}
