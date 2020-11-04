package io.github.zhufangsmile.gateway.router;

import java.util.List;
import java.util.Random;

/**
 * @author zhufang
 * @date 2020/11/3 10:50 上午
 */
public class RandomHttpEndpointRouter implements HttpEndpointRouter {
    private Random random = new Random();

    @Override
    public String route(List<String> endpoints) {
        int i = random.nextInt(endpoints.size());
        System.out.println("i:" + i);
        return endpoints.get(i);
    }
}
