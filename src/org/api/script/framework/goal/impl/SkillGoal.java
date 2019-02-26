package org.api.script.framework.goal.impl;

import org.api.script.framework.goal.Goal;

public class SkillGoal implements Goal {

    private int current_level;
    private int end_level;

    public SkillGoal(int current_level, int end_level) {
        this.current_level = current_level;
        this.end_level = end_level;
    }

    @Override
    public boolean hasReached() {
        return current_level >= end_level;
    }

    @Override
    public String getCompletionMessage() {
        return "[Skill Goal]: Complete: Achieved level " + end_level + ".";
    }

    @Override
    public String getName() {
        return "[Skill Goal:] Set: [Start level: " + current_level + "| End level: " + end_level + "].";
    }

    public String toString() {
        return "[Skill Goal:] Left: [Current level: " + current_level + "| End level: " + end_level + "].";
    }

    public void update(int current_level) {
        this.current_level = current_level;
    }

    public int getGoal() {
        return end_level;
    }
}

