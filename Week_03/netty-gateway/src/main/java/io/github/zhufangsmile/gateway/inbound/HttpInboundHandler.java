package io.github.zhufangsmile.gateway.inbound;

import io.github.zhufangsmile.gateway.filter.HttpRequestFilter;
import io.github.zhufangsmile.gateway.outbound.netty.NettyHttpClient;
import io.github.zhufangsmile.gateway.outbound.okhttp.HttpOutboundHandler;
import io.github.zhufangsmile.gateway.router.HttpEndpointRouter;
import io.github.zhufangsmile.gateway.router.RandomHttpEndpointRouter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

import java.net.URL;
import java.util.List;

/**
 * @author zhufang
 * @date 2020/11/2 11:19 上午
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private List<String> proxyServer;

    private HttpEndpointRouter router;

    private HttpOutboundHandler httpOutboundHandler;

    public HttpInboundHandler(List<String> proxyServer) {
        this.proxyServer = proxyServer;
        this.router = new RandomHttpEndpointRouter();
        this.httpOutboundHandler = new HttpOutboundHandler();
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest request = (FullHttpRequest)msg;

            //过滤器
            HttpRequestFilter filter = (request1, ctx1) -> {
                request1.headers().add("nio", "zhufangsmile");
            };
            filter.filter(request, ctx);

            //路由
            String route = router.route(proxyServer);
            httpOutboundHandler.handle(route, request, ctx);
//            URL url = new URL(route);
//
//
////            httpOutboundHandler.handle(route, request, ctx);
//            NettyHttpClient nettyHttpClient = new NettyHttpClient(request,ctx);
//            nettyHttpClient.connect(url.getHost(), url.getPort());


        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ReferenceCountUtil.release(msg);
        }


    }
}
