package cn.base;

public enum Day2 {
    MONDAY("星期一"),
    TUESDAY("星期二"),
    WEDNESDAY("星期三"),
    THURSDAY("星期四"),
    FRIDAY("星期五"),
    SATURDAY("星期六"),
    SUNDAY("星期日");
    
    private String desc;
    private Day2(String desc){
        this.desc=desc;
    }
    public String getDesc(){
        return desc;
    }
    
    @Override
    public String toString() {
        return desc;
    }
    
    public static void main(String[] args){
        for (Day2 day:Day2.values()) {
//            System.out.println("name:"+day.name()+
//                    ",desc:"+day.getDesc());
            System.out.println("name:"+day.name()+
                    ",desc:"+day.toString());
        }
    }
}
