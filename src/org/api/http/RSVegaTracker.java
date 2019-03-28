package org.api.http;

import org.api.http.bot.BotData;
import org.rspeer.ui.Log;

import java.util.logging.Level;

public class RSVegaTracker {

    public static final String API_URL = "https://api.sphiinx.me/rsvega";

    public static void onStart() {
        Log.log(Level.WARNING, "Info", "Executing RSVegaTracker data collection.");
        if (!AccountData.insertAccountData())
            Log.severe("Account data insert HTTP request failed.");

        if (!BotData.insertBotData())
            Log.severe("Bot data insert HTTP request failed.");
    }
}
