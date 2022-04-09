package lesson15_ReflectionApiAndAnnotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NotSimpleAnnotation {
    // аннотация содержит значение и имя.
//    int value();
    int value() default 10;
    String name() default "default";




}
