package cn.base.bit;

/**
 * @Author Gosin
 * @Date 2019/8/21 15:28
 */
public class BitDemo3 {
    public static void main(String[] args)
    {
        int a = 2;
        System.out.println("a：" + a);
        System.out.println("a[二进制]：" + Integer.toBinaryString(a));
        int b = ~a;
        System.out.println("~a ：" + b);
        System.out.println("~a[二进制]：" + Integer.toBinaryString(b));
    }
}
