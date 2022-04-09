package lesson15_ReflectionApiAndAnnotations;

/*
При устройстве на работу спрашивайте как часто катаются релизы. Чем чаще, тем проще и интереснее.

Как работет под капотом Junit и что такое Reflection Api?
Что такое Reflection - переводится как отражение
 */

import java.lang.reflect.Modifier;

public class ReflectionApp {
    public static void main(String[] args) {
        /*
        Что такое ReflectionApi в Java.
        Во время исполнения программы мы можем получать информацию о классе (у каждого класса есть метаинформация) в виде метаинформации.
        Что может быть в метаинформации (она хранится в разделе памяти под названием MetaSpace). Попадает она туда через такую сущность как classLoader. Какую то часть информации classLoader(CL) загружает туда при старте приложения (как только запускаем main). В первую очередь туда поступает информация о классе Thread. Дальше о нашем классе, который мы запускаем и о прочих классах с которыми он взаимодействует (String например). Дальше информация может загружаться лениво (термин означающий по требованияю, lazy инициализация).
        Класс по сути является объектов
         */

        // обычная практика это clazz, class нельзя написать потому что class зарезервированно.
        // getClass - возвращает класс нашего объекта. И в этом классе хранится знание и описание того, что вообще представляет из себя тип нашего объекта.
        Class clazz = "Java".getClass();//class java.lang.String

        // Класс можно запросить напрямую - вернет экземпляр объекта class
        Class strClass = String.class;//class java.lang.String

        // Необязательно нужен объект чтобы узнать про класс
        Class intClass = Integer.class;//class java.lang.Integer

        // Можно класс получить у маленького интеджера. Казалось быт он не является ссылочным типом, однако он же должен обладать какой то метаинформацией чтобы java приложение могло с ним взаимодействовать. И эта метаинформация нужна минимум для того чтобы делать boxing и unboxing
        Class smallIntClass = int.class;//int

        //Все что мы бы хотели использовать в java снабжено meta информацией и классом.
        Class smallIntArrayClass = int[].class;//class [I

        //
        Class smallIntDoubleArrayClass = int[][].class;// [[I


        System.out.println(clazz.getName() + "\n" + clazz.getSimpleName() + "\n");
        //java.lang.String
        //String

        System.out.println(smallIntArrayClass.getName() + "\n" + smallIntArrayClass.getSimpleName() + "\n");
        //[I   -> массив интов
        //int[]

        System.out.println(smallIntDoubleArrayClass.getName() + "\n" + smallIntDoubleArrayClass.getSimpleName());
        //[[I
        //int[][]

        System.out.println(new int[][]{{1, 2}, {3, 4}} + "\n");
        //[[I@4dd8dc3
        /*
        две скобки выводятся потому что дефолтная реализация toString() из класса Object
        public String toString() {
        return getClass.getName() + "@" + Integer.toHexString(hashCode());
        }
         */

        // выдаст модификаторы (default не относится)
        int modifiers = clazz.getModifiers();
        if (Modifier.isFinal(modifiers)) {
            System.out.println(clazz.getName() + " is final");
        }
        if (Modifier.isAbstract(modifiers)) {
            System.out.println(clazz.getName() + " is abstract");
        }
        if (Modifier.isPublic(modifiers)) {
            System.out.println(clazz.getName() + " is public");
        }
        //java.lang.String is final
        //java.lang.String is public

        /*
         Почему modifiers является int. Каждый из модификаторов представляет собой уникальное число. Public это 1, private 2... . Если выбрать публичный 00001 и абрстрактный 00400 то число будет 00401. В этом числе раскурчены все возможные модификаторы.

        Жадные алгоритмы, принцип. У вас есть кэшбек, баллы которые вы можете потратить. Есть 100 бонусных рублей, и у вас есть покупки на 54 рубля, на 68, на 22, на 3, на 5. Задача, использовать как можно больше баллов. Один из способов решения такой задачи - это использование жадных алгоритмов. Как они работают. Сначала он выбирает самое больое число из всех оставшихся. Он говорит, могу я ее испльзовать? могу, и восполняет.
        100 - 68 = остается 32 рубля
        следующее наибольшее число 22
        ок, остается 10
        5
        3
        Так делает на каждой итерации

        В модификаторах работает точно также. Мы берем число 401. Какое максимально число здесь - 400 (абстрактный) и остается 1 (публичный).
         */



        // возвращает массив объектов типа class
        Class[] interfaces = clazz.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println(anInterface.getName());
        }
        /*
java.lang.String is final
java.lang.String is public
java.io.Serializable
java.lang.Comparable
java.lang.CharSequence
java.lang.constant.Constable
java.lang.constant.ConstantDesc
         */

        System.out.println();// пробел

        // вернет родителя
        Class superclass = clazz.getSuperclass();
        System.out.println(superclass);
        //class java.lang.Object
        // суперкласс для Object это null.
    }
}
