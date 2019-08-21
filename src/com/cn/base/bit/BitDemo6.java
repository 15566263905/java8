package cn.base.bit;

/**
 * @Author Gosin
 * @Date 2019/8/21 15:41
 */
public class BitDemo6 {
    public static void main(String[] args)
    {
        System.out.println(Integer.toBinaryString(6297));
        System.out.println(Integer.toBinaryString(-6297));
        System.out.println(Integer.toBinaryString(6297>>5));
        System.out.println(Integer.toBinaryString(-6297>>5));
        System.out.println(Integer.toBinaryString(6297>>>5));
        System.out.println(Integer.toBinaryString(-6297>>>5));
        System.out.println(Integer.toBinaryString(6297<<5));
        System.out.println(Integer.toBinaryString(-6297<<5));
    }
}
