package com.zf.rpcfx.server;

import com.thoughtworks.xstream.XStream;
import com.zf.rpcfx.api.RpcfxRequest;
import com.zf.rpcfx.api.RpcfxResolver;
import com.zf.rpcfx.api.RpcfxResponse;
import com.zf.rpcfx.exception.RpcfxException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author zhufang
 * @date 2020/12/13 4:34 下午
 */
public class RpcfxInvoker {

    private RpcfxResolver rpcfxResolver;
    private XStream xStream = new XStream();
    
    public RpcfxInvoker(RpcfxResolver rpcfxResolver) {
        this.rpcfxResolver = rpcfxResolver;
    }

    public RpcfxResponse invoke(RpcfxRequest request) {
        RpcfxResponse response = new RpcfxResponse();
        String serviceClass = request.getServiceClass();
        Object service = rpcfxResolver.resolve(serviceClass);

        try {
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            Object result = method.invoke(service, request.getParams());
            response.setStatus(true);
            response.setResult(result);
            return response;
        } catch (InvocationTargetException | IllegalAccessException e) {
            RpcfxException exception = new RpcfxException("invoke interface error", e);
            response.setException(exception);
            response.setStatus(false);
            return response;
        }


    }

    public String invokeXml(String requestXml) {
        RpcfxRequest request = (RpcfxRequest) xStream.fromXML(requestXml);
        RpcfxResponse response = new RpcfxResponse();
        String serviceClass = request.getServiceClass();
        Object service = rpcfxResolver.resolve(serviceClass);

        try {
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            Object result = method.invoke(service, request.getParams());
            response.setStatus(true);
            response.setResult(result);
            return xStream.toXML(response);
        } catch (InvocationTargetException | IllegalAccessException e) {
            RpcfxException exception = new RpcfxException("invoke interface error", e);
            response.setException(exception);
            response.setStatus(false);
            return xStream.toXML(response);
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }

}
