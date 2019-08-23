package cn.base;

import java.io.File;
import java.net.*;

public class FileUrlClassLoader extends URLClassLoader {
    public FileUrlClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }
    
    public FileUrlClassLoader(URL[] urls) {
        super(urls);
    }
    
    public FileUrlClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }
    
    
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException {
        String rootDir="D:\\workspace\\Pro025\\trunk\\04 代码库\\java8\\src";
        //创建自定义文件类加载器
        File file = new File(rootDir);
        //File to URI
        URI uri=file.toURI();
        URL[] urls={uri.toURL()};
        
        FileUrlClassLoader loader = new FileUrlClassLoader(urls);
        
        try {
            //加载指定的class文件
            Class<?> object1=loader.loadClass("cn.base.DemoObj");
            System.out.println(object1.newInstance().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
