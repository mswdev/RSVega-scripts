package org.api.http;

import org.api.http.bot.BotData;
import org.api.http.bot.stats.StatsOSRS;
import org.rspeer.ui.Log;

import java.util.logging.Level;

public class RSVegaTrackerThread implements Runnable {


    @Override
    public void run() {
        Log.log(Level.WARNING, "Info", "Executing RSVegaTracker data collection.");
        if (!BotData.updateBotData())
            Log.severe("Bot data update HTTP request failed.");

        if (!StatsOSRS.updateStatsOSRSData())
            Log.severe("Stats OSRS data update HTTP request failed.");
    }
}
