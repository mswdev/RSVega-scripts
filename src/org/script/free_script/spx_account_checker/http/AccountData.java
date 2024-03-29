package org.script.free_script.spx_account_checker.http;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class AccountData {

    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    private static Response put(String url, RequestBody body) throws IOException {
        final Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        return HTTP_CLIENT.newCall(request).execute();
    }

    private static Response get(String url) throws IOException {
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        return HTTP_CLIENT.newCall(request).execute();
    }

    public static JsonObject getNext() {
        try {
            final Response response = get("https://api.org.me/rs/accounts/unchecked");
            if (!response.isSuccessful())
                return null;

            if (response.body() == null)
                return null;

            final Gson gson = new Gson().newBuilder().create();
            return gson.fromJson(response.body().string(), JsonObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getTotal() {
        try {
            final Response response = get("https://api.org.me/rs/accounts/unchecked/total");
            if (!response.isSuccessful())
                return null;

            if (response.body() == null)
                return null;

            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean putData(AccountDataType dataType, int accountId, RequestBody requestBody) {
        String url = null;
        switch (dataType) {
            case GENERAL:
                url = "https://api.org.me/rs/accounts/" + accountId + "/update";
                break;
            case OSRS:
                url = "https://api.org.me/rs/accounts/" + accountId + "/osrs/update";
                break;
            case RS3:
                url = "https://api.org.me/rs/accounts/" + accountId + "/rs3/update";
                break;
        }

        try {
            return put(url, requestBody).isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}

