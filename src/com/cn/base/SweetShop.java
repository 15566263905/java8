package cn.base;

/**
 * @Author Gosin
 * @Date 2019/8/20 20:06
 */
public class SweetShop {
    public static void print(Object obj) {
        System.out.println(obj);
    }
    public static void main(String[] args) {
        print("inside main");
        new Candy();
        print("After creating Candy");
        try {
            Class.forName("cn.base.Gum");
        } catch(ClassNotFoundException e) {
            print("Couldn't find Gum");
        }
        print("After Class.forName(\"cn.base.Gum\")");
        new Cookie();
        print("After creating Cookie");
    
    
        try{
            //通过Class.forName获取Gum类的Class对象
            Class clazz=Class.forName("cn.base.Gum");
            System.out.println("forName=clazz:"+clazz.getName());
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    
        //通过实例对象获取Gum的Class对象
        Gum gum = new Gum();
        Class clazz2=gum.getClass();
        System.out.println("new=clazz2:"+clazz2.getName());
    }
}
