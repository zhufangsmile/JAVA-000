package com.zf.rpcfx.api;

/**
 * @author zhufang
 * @date 2020/12/13 4:34 下午
 */
public class RpcfxRequest {
    private String serviceClass;
    private String method;
    private Object[] params;

    public String getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
