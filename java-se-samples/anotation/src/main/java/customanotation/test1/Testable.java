package customanotation.test1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// ʹ��JDK��Ԫ����Annotation��Retention
@Retention(RetentionPolicy.RUNTIME)
// ʹ��JDK��Ԫ����Annotation��Target
@Target(ElementType.METHOD)
// ����һ�����ע�⣬�������κγ�Ա�����������ɴ���Ԫ����
public @interface Testable {
}
