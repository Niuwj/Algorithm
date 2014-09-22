package designPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DynamicProxy implements InvocationHandler{
	private Logger logger = Logger.getLogger(this.getClass().getName());
    private Object delegate;

    public  Object bind(Object delegate)
    {
           this.delegate = delegate;
           Class cls = delegate.getClass();
           return Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), this);
    }


    public Object invoke(Object o, Method method, Object[] args)
                  throws Throwable {
           log( "starting...");
           Object obj = method.invoke(delegate, args);
           log("stopping...");
           return obj;
    }

    public void log(String message)
    {
           logger.log(Level.INFO, message);
    }
}
