package org.api.http;

import org.rspeer.ui.Log;

import java.util.Date;
import java.util.logging.Level;

public class RSVegaTrackerThread implements Runnable {


    @Override
    public void run() {
        Log.log(Level.WARNING, "Info", "Executing RSVegaTracker data collection.");
        RSVegaTracker.updateBot();
        RSVegaTracker.updateStatsOSRS();
        RSVegaTracker.updateSession(new Date());
    }
}
