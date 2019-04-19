package org.script.paid_script.spx_tutorial_island.api.http;

import java.util.Date;

public class RSVegaTrackerThread implements Runnable {


    @Override
    public void run() {
        RSVegaTracker.updateBot();
        RSVegaTracker.updateStatsOSRS();
        RSVegaTracker.updateSession(new Date());
    }
}
