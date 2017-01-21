package cn.edu.ncut.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author NikoBelic
 * @create 12/12/2016 19:20
 */
public class TestReflect
{
    public void sayHello(String name )
    {
        System.out.println("Hello " + name);
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException
    {
        Object service = Class.forName(TestReflect.class.getName()).newInstance();
        System.out.println(service.getClass());
        Method sayHello = service.getClass().getMethod("sayHello", String.class);
        sayHello.invoke(service,"NikoBelic");
    }
}
