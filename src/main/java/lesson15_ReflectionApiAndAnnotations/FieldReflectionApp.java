package lesson15_ReflectionApiAndAnnotations;

import java.lang.reflect.Field;

public class FieldReflectionApp {

    public static void main(String[] args) {
        Class bikeClass = Bike.class;

        // возвращает массив типа field - то есть поле. Класс, который описивает каждое поле нашего класса
        Field[] fields = bikeClass.getFields();


        // getType возвращает класса и мы у класса можем вызвать метод getName();
        for (Field f : fields) {
            System.out.println("name = " + f.getName() + " = type " + f.getType().getName());
        }
        /*
name = model = type java.lang.String
name = serialNo = type java.lang.String
name = year = type int


Сделали поле year приватным
name = model = type java.lang.String
name = serialNo = type java.lang.String
         */

        System.out.println();// пробел
        // Для того чтобы получить доступ ко всем полям класса нужен метод getDeclaredFields.
        Field[] fields2 = bikeClass.getDeclaredFields();

        for (Field f : fields2) {
            System.out.println("name = " + f.getName() + " = type " + f.getType().getName());
        }

        /*
name = model = type java.lang.String
name = serialNo = type java.lang.String
name = year = type int
         */


        System.out.println();// пробел


            Bike bike = new Bike();
            System.out.println(bike);

            try {
                // запрашиваем поле по имени
                Field field = bikeClass.getField("model");// бросает NoSuchFieldException - проверка на существование такового поля
                field.set(bike, "Canyon");// бросает IllegalAccessException - проверка на приватное поле
                System.out.println(bike);
                /*
Bike{model='null', serialNo='null', year=0}
Bike{model='Canyon', serialNo='null', year=0}
         */

                Field yearField = bikeClass.getDeclaredField("year");
//                yearField.set(bike, 2021);
//                System.out.println(bike);
                /*
java.lang.IllegalAccessException: class
пытаемся модифицировать приватное поле
                 */

                // вот такая фича позволяет писать в приватное поле и получать доступ к нему. Деается открытым только для yearField, если где то в паралельном потоке вызовем bike.getClass то setAccessible на нем стоять не будет.
                // можно ли менять значение поля если оно final - нет.
                yearField.setAccessible(true);
                yearField.set(bike, 2021);
                System.out.println(bike);
                /*
Bike{model='null', serialNo='null', year=0}
Bike{model='Canyon', serialNo='null', year=0}
Bike{model='Canyon', serialNo='null', year=2021}
                 */

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }





    }
}
