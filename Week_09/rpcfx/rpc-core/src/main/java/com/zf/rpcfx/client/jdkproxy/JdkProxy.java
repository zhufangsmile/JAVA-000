package com.zf.rpcfx.client.jdkproxy;

import com.zf.rpcfx.client.Invoker;
import com.zf.rpcfx.client.Proxy;

/**
 * @author zhufang
 * @date 2020/12/16 10:43 上午
 */
public class JdkProxy<T> implements Proxy<T> {
    @Override
    public T create(Class<T> serviceClass, Invoker<T> invoker) {
        JdkInvocationHandler handler = new JdkInvocationHandler(invoker);
        // 0. 替换动态代理 -> AOP
        return (T) java.lang.reflect.Proxy.newProxyInstance(JdkProxy.class.getClassLoader(), new Class[]{serviceClass}, handler);

    }
}