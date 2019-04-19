package org.script.free_script.spx_tutorial_island_lite.api.http;

import java.util.Date;

public class RSVegaTrackerThread implements Runnable {


    @Override
    public void run() {
        RSVegaTracker.updateBot();
        RSVegaTracker.updateStatsOSRS();
        RSVegaTracker.updateSession(new Date());
    }
}
