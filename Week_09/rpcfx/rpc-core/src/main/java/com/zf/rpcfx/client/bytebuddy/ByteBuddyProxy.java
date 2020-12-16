package com.zf.rpcfx.client.bytebuddy;

import com.zf.rpcfx.client.Invoker;
import com.zf.rpcfx.client.Proxy;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhufang
 * @date 2020/12/16 9:59 上午
 */
public class ByteBuddyProxy<T> implements Proxy<T> {

    private static final Map<Class, Class> PROXY_CLASS_MAP = new ConcurrentHashMap();

    @Override
    public T create(Class<T> serviceClass, Invoker<T> invoker) {
        try {
            Class<?> proxyClass = PROXY_CLASS_MAP.get(serviceClass);
            if (proxyClass == null) {
                proxyClass = new ByteBuddy()
                        .subclass(Object.class)
                        .implement(serviceClass)
                        .method(ElementMatchers.isAbstract().and(ElementMatchers.isPublic()))
                        .intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                return invoker.invoke(method, args);
                            }
                        }))
                        .make()
                        .load(getClass().getClassLoader())
                        .getLoaded();
            }
            T instance = (T)proxyClass.newInstance();
            return instance;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
