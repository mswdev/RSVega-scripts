package org.script.free_script.spx_aio_firemaking.api.http.bot;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import okhttp3.FormBody;
import okhttp3.Response;
import org.script.free_script.spx_aio_firemaking.api.http.RSVegaTracker;
import org.script.free_script.spx_aio_firemaking.api.http.wrappers.Request;

import java.io.IOException;

public class CreateBot {

    /**
     * Sends a post request to the RSVega-restapi to create an account.
     *
     * @param captcha_api_key The 2captcha api key; this is required.
     * @param email           The email to use when creating the account; If no email is provided it will be generated.
     * @param password        The password to use when creating the account; If no password is provided it will be
     *                        generated.
     * @param socks_ip        The socks5 proxy ip address to use when creating the account; If the proxy ip and port are
     *                        not specified no proxy will be used.
     * @param socks_username  The socks5 proxy username; this is not needed if the proxy does not have a username or
     *                        password.
     * @param socks_password  The socks5 proxy password; this is not needed if the proxy does not have a username or
     *                        password.
     * @return A json array of the account data; null otherwise. The json array includes the success, email, password
     * and proxy used when creating the account. If the success returned false then there was an error creating the
     * account; this can be due to a bad captcha or the email is taken.
     */
    public static JsonArray post(String captcha_api_key, String email, String password, String socks_ip, String socks_port, String socks_username, String socks_password) {
        final FormBody.Builder form_builder = new FormBody.Builder();
        form_builder.add("captcha_api_key", captcha_api_key);

        if (email != null)
            form_builder.add("email", email);

        if (password != null)
            form_builder.add("password", password);

        if (socks_ip != null && socks_port != null) {
            form_builder.add("socks_ip", socks_ip);
            form_builder.add("socks_port", socks_port);
        }

        if (socks_username != null && socks_password != null) {
            form_builder.add("socks_username", socks_username);
            form_builder.add("socks_password", socks_password);
        }


        try (final Response response = Request.post(RSVegaTracker.API_URL + "/bot/create", form_builder.build())) {
            if (!response.isSuccessful())
                return null;

            if (response.body() == null)
                return null;

            final Gson gson = new Gson().newBuilder().create();
            return gson.fromJson(response.body().string(), JsonArray.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

