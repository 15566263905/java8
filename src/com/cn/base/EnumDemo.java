package cn.base;

public class EnumDemo {
    public static void main(String[] args){
        //直接引用
        Day day =Day.MONDAY;
    }
}

//定义枚举类型
enum Day {
    MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
