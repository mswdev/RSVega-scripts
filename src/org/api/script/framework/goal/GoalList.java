package org.api.script.framework.goal;

import org.api.script.framework.goal.impl.ResourceGoal;
import org.api.script.framework.goal.impl.TimeGoal;
import org.rspeer.ui.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class GoalList extends ArrayList<Goal> {

    public GoalList(Goal... goals) {
        this.addAll(Arrays.asList(goals));
    }

    /**
     * Updates the resource goal count.
     *
     * @param id    The ITEM id that has been added to the inventory.
     * @param count The count of the ITEM that has been added to the inventory.
     */
    public void updateResourceGoals(int id, int count) {
        for (Goal g : this)
            if (g instanceof ResourceGoal && ((ResourceGoal) g).containsId(id))
                ((ResourceGoal) g).update(count);
    }

    /**
     * Determines whether we have reached all the goals in the list.
     *
     * @return True if we've reached all the goals in the list; false otherwise.
     */
    public boolean hasReachedGoals() {
        for (int i = 0; i < size(); i++) {
            if (get(i).hasReached()) {
                Log.info(get(i).getCompletionMessage());
                remove(i);
            }
        }
        return isEmpty();
    }

    /**
     * Resets the time goals in this list.
     */
    public void resetTimeGoals() {
        for (Goal g : this)
            if (g instanceof TimeGoal)
                ((TimeGoal) g).reset();
    }

}

