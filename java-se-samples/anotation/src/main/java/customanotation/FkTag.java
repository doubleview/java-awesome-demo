package customanotation;

import java.lang.annotation.*;

// 指定该注解信息会保留到运行时
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(FkTags.class)
public @interface FkTag {
    // 为该注解定义2个成员变量
    String name() default "软件";

    int age();
}
