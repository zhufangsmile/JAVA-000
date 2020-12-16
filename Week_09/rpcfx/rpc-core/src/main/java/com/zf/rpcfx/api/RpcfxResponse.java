package com.zf.rpcfx.api;

/**
 * @author zhufang
 * @date 2020/12/13 4:35 下午
 */
public class RpcfxResponse {
    private Object result;
    private Boolean status;
    private Exception exception;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
