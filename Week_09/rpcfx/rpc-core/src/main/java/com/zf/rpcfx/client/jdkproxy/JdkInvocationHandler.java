package com.zf.rpcfx.client.jdkproxy;
import com.zf.rpcfx.client.Invoker;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhufang
 * @date 2020/12/16 10:44 上午
 */
public class JdkInvocationHandler implements InvocationHandler {

    private Invoker invoker;
    public JdkInvocationHandler(Invoker invoker) {
        this.invoker = invoker;
    }

    // 可以尝试，自己去写对象序列化，二进制还是文本的，，，rpcfx是xml自定义序列化、反序列化，json: code.google.com/p/rpcfx
    // int byte char float double long bool
    // [], data class

    @Override
    public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
        return invoker.invoke(method, params);
    }
}
