package org.api.script.framework.goal.impl;

import org.api.script.framework.goal.Goal;

public class ResourceGoal implements Goal {

    private int[] ids;
    private int current_amount;
    private int goal_amount;
    private String item_string;

    public ResourceGoal(int goal_amount, int... ids) {
        this.ids = ids;
        this.goal_amount = goal_amount;
        item_string = getItemString();
    }

    @Override
    public boolean hasReached() {
        return current_amount >= goal_amount;
    }

    public void update(int count) {
        current_amount += count;
    }

    public int[] getIds() {
        return ids;
    }

    @Override
    public String getCompletionMessage() {
        return "[Resource Goal]: Collected " + goal_amount + " of " + item_string + ".";
    }

    @Override
    public String getName() {
        return "[Resource Goal]: Set: Collecting " + goal_amount + " of " + item_string + ".";
    }

    public String toString() {
        return "[Resource Goal]: Left: Collected " + current_amount + " of " + goal_amount + ".";
    }

    private String getItemString() {
        StringBuilder str = new StringBuilder(ids.length == 1 ? "Item[" : "Items[");

        for (int i = 0; i < ids.length; i++)
            str.append(i == ids.length - 1 ? ids[i] + "]" : ids[i] + ",");

        return str.toString();
    }

    public boolean containsId(int id) {
        for (int i : ids)
            if (i == id)
                return true;

        return false;
    }
}

