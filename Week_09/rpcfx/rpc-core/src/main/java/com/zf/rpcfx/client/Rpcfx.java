package com.zf.rpcfx.client;

import com.zf.rpcfx.client.bytebuddy.ByteBuddyProxy;
import com.zf.rpcfx.client.javassist.JavassistProxy;

/**
 * @author zhufang
 * @date 2020/12/16 10:06 上午
 */
public class Rpcfx {
    public static <T> T create(final Class<T> serviceClass, final String url) {
        //使用javassist bytebuddy生成代理类
        Invoker<T> invoker = new Invoker(serviceClass, url);
        Proxy<T> proxy = new ByteBuddyProxy<>();
        return proxy.create(serviceClass, invoker);
    }
}
