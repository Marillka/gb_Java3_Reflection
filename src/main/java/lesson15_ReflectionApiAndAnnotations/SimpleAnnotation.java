package lesson15_ReflectionApiAndAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
Для того чтобы сделать аннотацию, нужно перед словом interface добавить собаку @.
Также нужно добавить аннотация @Retention - это сообщение о том, где аннотация будет использоваться.

Зачем помечать код аннотациями @Test, для того чтобы в момент запуска мы могли понимать что у нас размечено тестами
 */
//@Retention(RetentionPolicy.SOURCE)// source - означает что данная аннотация нужна нам только на этапе компиляции.
//@Retention(RetentionPolicy.CLASS)// Эту информацию, о том что метод был записан в файл.class, но во время выполнения программы для рефлексии она будет недоступна.
@Retention(RetentionPolicy.RUNTIME)// Для того чтобы рефлексия была доступна и все работало как положено. Означает что для рефлекии это аннотация будет видна и мы сможем к ней обратиться, когда это потребуется.
@Target(ElementType.METHOD)// Еще одно, чем помечаем. Target это то, к чему аннотация применима. Классу, методу, пакету, полю и т.д.)
public @interface SimpleAnnotation {

}


