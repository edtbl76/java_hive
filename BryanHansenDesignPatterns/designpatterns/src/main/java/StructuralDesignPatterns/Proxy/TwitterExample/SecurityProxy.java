package StructuralDesignPatterns.Proxy.TwitterExample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SecurityProxy implements InvocationHandler {

    private Object obj;

    private SecurityProxy(Object obj) {
        this.obj = obj;
    }

    /*
        This is a proxy that has been built around the object we actually want to create.
     */
    public static Object newInstance(Object obj) {
        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), new SecurityProxy(obj));
    }

    /*
         This is the the invocation Handler.
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            result = method.invoke(obj, args);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
        }
        return result;
    }
}
