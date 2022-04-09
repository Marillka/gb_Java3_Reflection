package lesson15_ReflectionApiAndAnnotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodReflectionApp {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class bikeClass = Bike.class;

        // Что есть у метода, чего нет у поля - возвращаемый тип и параметры
        Method[] declaredMethods = bikeClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {

            // Дай мне у этого метода такую аннотация, и если она есть скажи мне о том что этот метод помечен аннотацией
            if (declaredMethod.getAnnotation(SimpleAnnotation.class) != null) {
                System.out.println("method with annotation");
            }

            declaredMethod.getDeclaredAnnotations();// вернет все аннотации, которые висят на методе. На методе может быть больше одной аннотации.

            System.out.println("name = " + declaredMethod.getName() + " returnType = " + declaredMethod.getReturnType().getName() + " parameters " + Arrays.toString(declaredMethod.getParameterTypes()));
        }
        /*
name = toString returnType = java.lang.String parameters []
name = getYear returnType = int parameters []
name = setYear returnType = void parameters [int]
name = getSerialNo returnType = java.lang.String parameters []
name = setSerialNo returnType = void parameters [class java.lang.String]
name = getModel returnType = java.lang.String parameters []
name = setModel returnType = void parameters [class java.lang.String]


name = toString returnType = java.lang.String parameters []
name = getYear returnType = int parameters []
name = setYear returnType = void parameters [int]
name = setSerialNo returnType = void parameters [class java.lang.String]
name = setYearAndModel returnType = void parameters [class java.lang.String, int]
name = getModel returnType = java.lang.String parameters []
name = getSerialNo returnType = java.lang.String parameters []
name = setModel returnType = void parameters [class java.lang.String]
         */


        System.out.println();// пробел
        // Дай мне метод который называется "вот так", у которого параметры вот такие
        Method method = bikeClass.getDeclaredMethod("setYearAndModel", String.class, int.class);

        NotSimpleAnnotation annotation = method.getDeclaredAnnotation(NotSimpleAnnotation.class);// получили аннотацию
        System.out.println(annotation.name() + " " + annotation.value());
        //myName 11

        System.out.println();// пробел

        Bike bike = new Bike();
        System.out.println(bike);

        // разрешаем доступ к этому приватному методу
        method.setAccessible(true);
        // Этот метод мы можем вызвать. Должны передать все что надо этому методу
        method.invoke(bike, "Pinarello", 2021);// ошибки доступа к методу - исключения
        System.out.println(bike);
        /*
Bike{model='null', serialNo='null', year=0}
Bike{model='Pinarello', serialNo='null', year=2021}
         */

    }
}
