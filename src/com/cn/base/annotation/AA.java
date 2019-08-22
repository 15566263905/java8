package cn.base.annotation;

@FilterPath("/web/list")
class CC { }

@FilterPath("/web/update")
@FilterPath("/web/add")
@FilterPath("/web/delete")
public class AA extends CC {
    public static void main(String[] args) {
        
        Class<?> clazz = AA.class;
        //通过getAnnotationsByType方法获取所有重复注解
        FilterPath[] annotationsByType = clazz.getAnnotationsByType(FilterPath.class);
        FilterPath[] annotationsByType2 = clazz.getDeclaredAnnotationsByType(FilterPath.class);
        if (annotationsByType != null) {
            for (FilterPath filter : annotationsByType) {
                System.out.println("1:"+filter.value());
            }
        }
        
        System.out.println("-----------------");
        
        if (annotationsByType2 != null) {
            for (FilterPath filter : annotationsByType2) {
                System.out.println("2:"+filter.value());
            }
        }
        
        
        System.out.println("使用getAnnotation的结果:"+clazz.getAnnotation(FilterPath.class));
    }
}
