package io.github.zhufangsmile.gateway;

import io.github.zhufangsmile.gateway.inbound.HttpInboundServer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhufang
 * @date 2020/11/2 10:58 上午
 */
public class NettyServerApplication {

    private static List<String> endpoints = new ArrayList<>();

    static {
//        endpoints.add("http://localhost:8081");
        endpoints.add("http://dohko.pay.checkup.api.hualala.com:80");
    }

    public static void main(String[] args) {
//        String proxyServer = System.getProperty("proxyServer","http://localhost:8088");
        String proxyPort = System.getProperty("proxyPort","8888");
        int port = Integer.parseInt(proxyPort);
        HttpInboundServer httpInboundServer = new HttpInboundServer(port, endpoints);
        try {
            httpInboundServer.run();
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}
