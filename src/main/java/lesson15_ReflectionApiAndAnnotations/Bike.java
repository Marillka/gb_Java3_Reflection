package lesson15_ReflectionApiAndAnnotations;

public class Bike {
    public String model;
    public String serialNo;
    //    public int year;
    private int year;

    public Bike(String model, String serialNo) {
        this.model = model;
        this.serialNo = serialNo;
    }

    public Bike(String model, String serialNo, int year) {
//        this.model = model;
//        this.serialNo = serialNo;
        this(model, serialNo);// Сначала вызовет конструктор
        this.year = year;
    }

    public Bike() {
    }

    @SimpleAnnotation
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @SimpleAnnotation
    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @SimpleAnnotation
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @NotSimpleAnnotation
    private void setYearAndModel(String model, int year) {
        this.model = model;
        this.year = year;
    }


    /*
    Почему так делать нельзя?
    name = setYearAndModel returnType = void parameters [class java.lang.String, int]
    Потому что будет точно такая же сигнатура, название параметров вообще не интересует. Одна из причин по крайней мере. Местами поменять можно.
     */
//    private void setYearAndModel(String model2, int year2) {
//        this.model = model;
//        this.year = year;
//    }

    @Override
    public String toString() {
        return "Bike{" +
                "model='" + model + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", year=" + year +
                '}';
    }
}
