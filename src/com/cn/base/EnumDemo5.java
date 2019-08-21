package cn.base;

enum Color {GREEN,RED,BLUE}

public class EnumDemo5 {
    public static void printName(Color color){
        switch (color){
            case BLUE:
                System.out.println("蓝色");
                break;
            case RED:
                System.out.println("红色");
                break;
            case GREEN:
                System.out.println("绿色");
                break;
        }
    }
    
    public static void main(String[] args){
        printName(Color.BLUE);
        printName(Color.RED);
        printName(Color.GREEN);
    }
}
