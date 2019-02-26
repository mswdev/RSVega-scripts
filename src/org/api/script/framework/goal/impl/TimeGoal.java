package org.api.script.framework.goal.impl;

import org.api.script.framework.goal.Goal;

public class TimeGoal implements Goal {

    private long start_time;
    private long time_amount;

    public TimeGoal(long time_amount) {
        this.start_time = System.currentTimeMillis();
        this.time_amount = time_amount;
    }

    @Override
    public boolean hasReached() {
        return System.currentTimeMillis() - start_time >= time_amount;
    }

    @Override
    public String getCompletionMessage() {
        return "[Time Goal]: Complete: Ran for" + (time_amount / 1000) / 60 + " minutes.";
    }

    @Override
    public String getName() {
        return "[Time Goal:] Set: " + (time_amount / 1000) / 60 + " minutes.";
    }

    @Override
    public String toString() {
        return "[Time Goal:] Left: " + ((System.currentTimeMillis() - start_time) / 1000) / 60 + "minutes.";
    }

    public void reset() {
        start_time = System.currentTimeMillis();
    }
}

