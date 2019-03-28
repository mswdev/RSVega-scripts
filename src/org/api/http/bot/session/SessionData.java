package org.api.http.bot.session;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.api.http.RSVegaTracker;
import org.api.http.bot.BotData;
import org.api.http.wrappers.Request;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SessionData {

    private static int SESSION_ID;

    public static boolean insertSession(RequestBody request_body) {
        try (final Response response = Request.post(RSVegaTracker.API_URL + "/bot/session/add", request_body)) {
            return response.isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updateSession(int session_id, RequestBody request_body) {
        try (final Response response = Request.put(RSVegaTracker.API_URL + "/bot/id/" + session_id + "/session/update", request_body)) {
            return response.isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static RequestBody getSessionDataRequestBody(String script_name, Date time_started, Date time_ended) {
        final FormBody.Builder form_builder = new FormBody.Builder();
        form_builder.add("bot_id", String.valueOf(BotData.getBotID()));

        if (script_name != null)
            form_builder.add("script", script_name);

        if (time_started != null)
            form_builder.add("time_started", new SimpleDateFormat("yyyy-MM-dd H:mm:ss").format(time_started));

        if (time_ended != null)
            form_builder.add("time_ended", new SimpleDateFormat("yyyy-MM-dd H:mm:ss").format(time_ended));
        return form_builder.build();
    }

    /**
     * Gets a json array of the newest bot session.
     *
     * @param bot_id The bot id;
     * @return A json array of the newest bot session.; null otherwise.
     */
    private static JsonArray getNewestSessionByBotID(int bot_id) {
        try (final Response response = Request.get(RSVegaTracker.API_URL + "/bot/session/bot-id/" + bot_id + "/newest")) {
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
     * Gets the session id associated with the bot id.
     *
     * @return The session id associated with the bot id.
     */
    public static int getSessionID() {
        if (SESSION_ID == 0) {
            JsonArray json_array = SessionData.getNewestSessionByBotID(BotData.getBotID());
            if (json_array == null)
                return 0;

            if (json_array.size() == 0)
                return 0;

            SESSION_ID = json_array.get(0).getAsJsonObject().get("id").getAsInt();
        }

        return SESSION_ID;
    }
}
