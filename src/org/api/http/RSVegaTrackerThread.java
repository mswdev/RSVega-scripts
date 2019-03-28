package org.api.http;

import org.api.http.bot.BotData;
import org.api.http.bot.stats.StatsOSRS;
import org.rspeer.ui.Log;

import java.util.logging.Level;

public class RSVegaTrackerThread implements Runnable {


    @Override
    public void run() {
        Log.log(Level.WARNING, "Info", "Executing RSVegaTracker data collection.");
        if (!BotData.updateBot(BotData.getBotID(), BotData.getBotDataRequestBody()))
            Log.severe("Bot data update HTTP request failed.");

        if (!StatsOSRS.updateStatsOSRS(BotData.getBotID(), StatsOSRS.getStatsOSRSDataRequestBody()))
            Log.severe("Stats OSRS data update HTTP request failed.");
    }
}
