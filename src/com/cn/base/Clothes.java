package cn.base;

public class Clothes {
    
    public Clothes (String name, EnumMapDemo.Color color) {
      this.color = color;
      this.name = name;
    }
    
    private EnumMapDemo.Color color;
    private String name;
    
    public EnumMapDemo.Color getColor() {
        return color;
    }
    
    public void setColor(EnumMapDemo.Color color) {
        this.color = color;
    }
}
