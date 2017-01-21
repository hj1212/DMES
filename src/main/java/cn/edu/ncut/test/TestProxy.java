package cn.edu.ncut.test;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author NikoBelic
 * @create 12/12/2016 19:53
 */
public class TestProxy
{
    public static void main(String[] args)
    {
        HelloServiceProxy helloHandler = new HelloServiceProxy();
        HelloServic proxy = (HelloServic) helloHandler.bind(new HelloServiceImpl());
        proxy.sayHello("NikoBelic");
    }
}

interface HelloServic
{
    void sayHello(String name);
}

class HelloServiceImpl implements HelloServic
{

    @Override
    public void sayHello(String name)
    {
        System.out.println("Hello," + name);
    }
}

class HelloServiceProxy implements InvocationHandler
{

    // 真实的服务对象
    private Object target;

    // 绑定委托对象并返回一个代理类
    Object bind(Object target)
    {
        this.target = target;
        // 取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        System.out.println("我是JDK动态代理");
        Object result = null;
        // 反射方法前调用
        System.err.println("我准备说Hello");
        // 执行方法,相当于调用 HelloServiceImpl类的sayHello方法
        result = method.invoke(target,args);
        // 反射方法后调用
        System.err.println("我说过Hello了");
        return result;

    }
}

class HelloServiceCgLib implements MethodInterceptor
{
    private Object target;

    public Object getInstance(Object target)
    {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 毁掉方法
        enhancer.setCallback((Callback) this);
        return enhancer.create();

    }


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        return null;
    }
}