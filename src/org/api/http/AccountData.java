package org.api.http;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.api.http.wrappers.Request;
import org.rspeer.script.Script;

import java.io.IOException;

public class AccountData {

    private static int ACCOUNT_ID;

    public static boolean insertAccount(RequestBody request_body) {
        try (final Response response = Request.post(RSVegaTracker.API_URL + "/account/add", request_body)) {
            return response.isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static RequestBody getAccountDataRequestBody() {
        final FormBody.Builder form_builder = new FormBody.Builder();
        form_builder.add("username", Script.getRSPeerUser().getUsername());
        return form_builder.build();
    }

    /**
     * Gets a json array of the rspeer users account data.
     *
     * @param username The username of the rspeer user.
     * @return A json array of the rspeer users account data; null otherwise.
     */
    private static JsonArray getAccountByUsername(String username) {
        try (final Response response = Request.get(RSVegaTracker.API_URL + "/account/user/" + username)) {
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

    /**
     * Gets the account id associated with the rspeer user.
     *
     * @return The account id associated with the rspeer user.
     */
    public static int getAccountID() {
        if (ACCOUNT_ID == 0) {
            JsonArray json_array = AccountData.getAccountByUsername(Script.getRSPeerUser().getUsername());
            if (json_array == null)
                return 0;

            if (json_array.size() == 0)
                return 0;

            ACCOUNT_ID = json_array.get(0).getAsJsonObject().get("id").getAsInt();
        }

        return ACCOUNT_ID;
    }
}
