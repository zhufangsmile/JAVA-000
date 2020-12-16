package com.zf.rpcfx.exception;

/**
 * @author zhufang
 * @date 2020/12/15 6:29 下午
 */
public class RpcfxException extends RuntimeException{
    private String message;

    public RpcfxException(String message) {
        this(message, null);
    }
    public RpcfxException(Throwable cause) {
        this(null, cause);
    }

    public RpcfxException(String message, Throwable cause) {
        super(message, cause);
    }
}
