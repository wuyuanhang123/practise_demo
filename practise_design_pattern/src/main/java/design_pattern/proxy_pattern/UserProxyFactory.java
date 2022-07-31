package design_pattern.proxy_pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserProxyFactory {
    //被代理对象
    private Object target;

    public UserProxyFactory(Object target){
        this.target = target;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
        new InvocationHandler(){
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("开启事务");
                Object returnValue = method.invoke(target,objects);
                System.out.println("结束事务");
                return null;
            }
        });
    }
}
