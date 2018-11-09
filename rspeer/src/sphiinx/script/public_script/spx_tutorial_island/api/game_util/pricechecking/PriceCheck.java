package sphiinx.script.public_script.spx_tutorial_island.api.game_util.pricechecking;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceCheck {

    //todo Rewrite this class.
    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();
    private static JsonObject OSBUDDY_SUMMARY_JSON;

    /**
     * Gets the price of the item id from the RuneScape website.
     *
     * @param id The id of the item.
     * @return The price of the item; 0 otherwise.
     */
    public static int getRSPrice(int id) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://services.runescape.com/m=itemdb_oldschool/api/catalogue/detail.json?item=" + id).openStream()))) {
            final Matcher matcher = Pattern.compile(".*\"price\":\"?(\\d+,?\\.?\\d*)([k|m]?)\"?},\"today\".*").matcher(reader.readLine());
            reader.close();

            if (matcher.matches()) {
                final double price = Double.parseDouble(matcher.group(1).replace(",", ""));
                final String suffix = matcher.group(2);
                return (int) (suffix.isEmpty() ? price : price * (suffix.charAt(0) == 'k' ? 1000 : 1000000));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Sets the OSBuddy price summary json.
     */
    private static void setOSBuddySummaryJson() throws IOException {
        final Request request = new Request.Builder()
                .url("https://storage.googleapis.com/osbuddy-exchange/summary.json")
                .get()
                .build();
        final Response response = HTTP_CLIENT.newCall(request).execute();
        if (!response.isSuccessful())
            return;

        if (response.body() == null)
            return;

        final Gson gson = new Gson().newBuilder().create();
        OSBUDDY_SUMMARY_JSON = gson.fromJson(response.body().string(), JsonObject.class);
    }

    /**
     * Gets the price of the item id from the OSBuddy price summary json.
     *
     * @param id The id of the item.
     * @return The price of the item; 0 otherwise.
     */
    public static int getOSBuddyPrice(int id) throws IOException {
        if (OSBUDDY_SUMMARY_JSON != null) {
            final JsonObject json_objects = OSBUDDY_SUMMARY_JSON.getAsJsonObject(Integer.toString(id));
            if (json_objects == null)
                return 0;

            return json_objects.get("sell_average").getAsInt();
        } else {
            setOSBuddySummaryJson();
        }

        return 0;
    }

}
