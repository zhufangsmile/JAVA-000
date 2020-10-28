package com.smile.geek.work;

import okhttp3.*;

import java.io.IOException;
import java.util.Optional;

/**
 * @author zhufang
 * @date 2020/10/28 11:15 上午
 */
public class HtttpClient {

    public static Optional<String> get(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(url).build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            ResponseBody body = response.body();
            return Optional.of(body.string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        Optional<String> optional = get("http://localhost:8808/test");
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
    }
}
