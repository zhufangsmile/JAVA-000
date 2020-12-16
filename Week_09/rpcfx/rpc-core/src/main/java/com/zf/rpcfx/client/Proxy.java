package com.zf.rpcfx.client;

/**
 * @author zhufang
 * @date 2020/12/16 10:41 上午
 */
public interface Proxy<T> {

    T create(Class<T> serviceClass, Invoker<T> invoker);
}
