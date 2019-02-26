package org.api.script.framework.goal.impl;

import org.api.script.framework.goal.Goal;

public class InfiniteGoal implements Goal {

    @Override
    public boolean hasReached() {
        return false;
    }

    @Override
    public String getCompletionMessage() {
        return "[Infinite Goal]: ";
    }

    @Override
    public String getName() {
        return "[Infinite Goal]: ";
    }

    public String toString() {
        return "[Infinite Goal]: ";
    }
}

