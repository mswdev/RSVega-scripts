package org.script.free_script.spx_aio_walking.api.http;

import org.rspeer.ui.Log;
import org.script.free_script.spx_aio_walking.api.http.bot.BotData;
import org.script.free_script.spx_aio_walking.api.http.bot.session.SessionData;
import org.script.free_script.spx_aio_walking.api.http.bot.stats.StatsOSRS;

import java.util.Date;

public class RSVegaTracker {

    public static final String API_URL = "https://api.sphiinx.me/rsvega";

    public static void insertAccount() {
        if (!AccountData.insertAccount(AccountData.getAccountDataRequestBody()))
            Log.severe("Account data insert HTTP request failed.");
    }

    public static void insertBot() {
        if (!BotData.insertBot(BotData.getBotDataRequestBody()))
            Log.severe("Bot data insert HTTP request failed.");
    }

    public static void updateBot() {
        if (!BotData.updateBot(BotData.getBotID(), BotData.getBotDataRequestBody()))
            Log.severe("Bot data update HTTP request failed.");
    }

    public static void updateStatsOSRS() {
        if (!StatsOSRS.updateStatsOSRS(BotData.getBotID(), StatsOSRS.getStatsOSRSDataRequestBody()))
            Log.severe("Stats OSRS data update HTTP request failed.");
    }

    public static void insertSession(String script_name, Date time_started) {
        if (!SessionData.insertSession(SessionData.getSessionDataRequestBody(script_name, time_started, null)))
            Log.severe("Session data insert HTTP request failed.");
    }

    public static void updateSession(Date time_ended) {
        if (!SessionData.updateSession(SessionData.getSessionID(), SessionData.getSessionDataRequestBody(null, null, time_ended)))
            Log.severe("Session data update HTTP request failed.");
    }
}
