package lesson15_ReflectionApiAndAnnotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ConstructorReflectionApp {
    // работа с констркуктором и с непосредственным созданием объекта
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class bikeClass = Bike.class;

        // вернет все конструкторы класса
        Constructor[] declaredConstructors = bikeClass.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(Arrays.toString(declaredConstructor.getParameterTypes()));
        }
           /*
[class java.lang.String, class java.lang.String, int]
[]
             */

//        bikeClass.newInstance();// вернет объект типа велосипед. Deprecated - запрещенный. с java9+.


        System.out.println();// пробел

        // Хотим получить консруктор, который создаст полностью объект. Если хотим получить пустой конструктор, то надо просто удалить все параметры
        Constructor constructor = bikeClass.getDeclaredConstructor(String.class, String.class, int.class);

        // Объекты создаем через обращение к конструктору
        Object bike = constructor.newInstance("Canyon", "12345", 2018);

        System.out.println(bike);
        /*
        Bike{model='Canyon', serialNo='12345', year=2018}

         */


        System.out.println();// пробел


        // Почему через приведение типов? Потому что на уровне рефлексии мы явно знаем объект какого класса мы ожидаем и здесть никакой автоматизации и typesave мы не ожидаем
        Bike bike1 = (Bike) constructor.newInstance("Cube","777", 2013);
        System.out.println(bike1);
        //Bike{model='Cube', serialNo='777', year=2013}


        /*
        Чем по сути отличаются конструкторы от методов в плане работы?
        Констукторы по сути не отличаются ничем.
        Одна из причин пользоваться конструктором (именно newInstance) заключается:
        Помните мы делали такую штуку, что один конструктор внутри себя может вызывать другой конструктор этого класса. .......................???????????
         */



    }
}
