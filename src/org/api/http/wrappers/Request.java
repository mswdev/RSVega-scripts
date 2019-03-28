package org.api.http.wrappers;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class Request {

    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    public static Response post(String url, RequestBody body) throws IOException {
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(body)
                .build();
        return HTTP_CLIENT.newCall(request).execute();
    }

    public static Response put(String url, RequestBody body) throws IOException {
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .put(body)
                .build();
        return HTTP_CLIENT.newCall(request).execute();
    }

    public static Response get(String url) throws IOException {
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .get()
                .build();
        return HTTP_CLIENT.newCall(request).execute();
    }

}
