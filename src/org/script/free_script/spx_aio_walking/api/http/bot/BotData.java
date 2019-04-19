package org.script.free_script.spx_aio_walking.api.http.bot;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.rspeer.RSPeer;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.Worlds;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.Players;
import org.script.free_script.spx_aio_walking.api.http.AccountData;
import org.script.free_script.spx_aio_walking.api.http.RSVegaTracker;
import org.script.free_script.spx_aio_walking.api.http.wrappers.Request;

import java.io.IOException;

public class BotData {

    private static int BOT_ID;

    public static boolean insertBot(RequestBody request_body) {
        try (final Response response = Request.post(RSVegaTracker.API_URL + "/bot/add", request_body)) {
            return response.isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updateBot(int bot_id, RequestBody request_body) {
        try (final Response response = Request.put(RSVegaTracker.API_URL + "/bot/id/" + bot_id + "/update", request_body)) {
            return response.isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Gets a json array of the bot data.
     *
     * @param username The username of the bot.
     * @return A json array of the bot data; null otherwise.
     */
    private static JsonArray getBotByUsername(String username) {
        try (final Response response = Request.get(RSVegaTracker.API_URL + "/bot/user/" + username)) {
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
     * Gets the bot id associated with the bot username.
     *
     * @return The bot id associated with the bot username.
     */
    public static int getBotID() {
        if (BOT_ID == 0) {
            JsonArray json_array = BotData.getBotByUsername(getUsername());
            if (json_array == null)
                return 0;

            if (json_array.size() == 0)
                return 0;

            BOT_ID = json_array.get(0).getAsJsonObject().get("id").getAsInt();
        }

        return BOT_ID;
    }

    public static RequestBody getBotDataRequestBody() {
        final FormBody.Builder form_builder = new FormBody.Builder();
        form_builder.add("account_id", String.valueOf(AccountData.getAccountID()));
        form_builder.add("username", getUsername());
        form_builder.add("display_name", getDisplayName());
        form_builder.add("world", String.valueOf(Worlds.getCurrent()));
        form_builder.add("last_sign_in", String.valueOf(getLastSignIn()));
        form_builder.add("is_members", String.valueOf(isMembers()));
        form_builder.add("is_bank_pin", String.valueOf(hasBankPin()));
        return form_builder.build();
    }

    private static String getUsername() {
        if (RSPeer.getGameAccount() == null)
            return "";

        final String username = RSPeer.getGameAccount().getUsername();
        if (username == null)
            return "";

        return username;
    }

    private static String getDisplayName() {
        if (Players.getLocal() == null)
            return "";

        final String display_name = Players.getLocal().getName();
        if (display_name == null)
            return "";

        return display_name;
    }

    private static int getLastSignIn() {
        final InterfaceComponent last_sign_in = Interfaces.getFirst(a -> a.getText().contains("You last logged in"));
        if (last_sign_in == null)
            return 0;

        if (last_sign_in.getText().contains("earlier today"))
            return 1;

        return Integer.parseInt(last_sign_in.getText().replaceAll("[^0-9]", ""));
    }

    private static int isMembers() {
        final InterfaceComponent not_members = Interfaces.getFirst(a -> a.getText().contains("You are not a member."));
        return not_members != null ? 0 : 1;
    }

    private static int hasBankPin() {
        final InterfaceComponent bank_pin_not_set = Interfaces.getFirst(a -> a.getText().contains("You do not have a Bank PIN."));
        return bank_pin_not_set != null ? 0 : 1;
    }
}
