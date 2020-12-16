package com.zf.rpcfx.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.thoughtworks.xstream.XStream;
import com.zf.rpcfx.api.RpcfxRequest;
import com.zf.rpcfx.api.RpcfxResponse;
import com.zf.rpcfx.exception.RpcfxException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.lang.reflect.Method;

/**
 * @author zhufang
 * @date 2020/12/15 11:56 上午
 */
public class Invoker<T> {

    public static final MediaType JSONTYPE = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType XMLTYPE = MediaType.parse("application/xml; charset=utf-8");

    static {
        ParserConfig.getGlobalInstance().addAccept("com.zf");
    }
    private final Class<T> serviceClass;
    private final String url;

    public Invoker(Class<T> serviceClass, String url) {
        this.serviceClass = serviceClass;
        this.url = url;
    }

    public Object invoke(Method method, Object[] args) {
        RpcfxRequest request = new RpcfxRequest();
        request.setMethod(method.getName());
        request.setParams(args);
        request.setServiceClass(this.serviceClass.getName());
        RpcfxResponse response = post(request, this.url);
        // 这里判断response.status，处理异常
        // 考虑封装一个全局的RpcfxException
        if (!response.getStatus()) {
            throw new RpcfxException("服务端返回异常：", response.getException());
        }


        return response.getResult();
    }

    private RpcfxResponse post(RpcfxRequest req, String url) {
        try {
            XStream xStream = new XStream();
            String reqJson = xStream.toXML(req);
            System.out.println("req xml: "+reqJson);
            // 1.可以复用client
            // 2.尝试使用httpclient或者netty client
            OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(XMLTYPE, reqJson))
                    .build();
            String respJson = client.newCall(request).execute().body().string();
            System.out.println("resp xml: "+respJson);
            RpcfxResponse response = (RpcfxResponse)xStream.fromXML(respJson);
            return response;
        }catch (Exception e) {
            throw new RpcfxException("调用服务端异常：", e);
        }

    }


}
