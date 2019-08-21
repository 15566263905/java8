package cn.base;

public class ClazzDemo {
    public static void main(String[] args){
        //没有泛型
        Class intClass = int.class;
        
        //带泛型的Class对象
        Class<Integer> integerClass = int.class;
        
        integerClass = Integer.class;
        
        //没有泛型的约束,可以随意赋值
        intClass= double.class;
        
        //编译期错误,无法编译通过
        //integerClass = double.class
    }
}
