package customanotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// ָ����ע����Ϣ�ᱣ��������ʱ
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FkTags {
    // ����value��Ա�������ó�Ա�����ɽ��ܶ��@FkTagע��
    FkTag[] value();
}
