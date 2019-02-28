package org.script.testing.data_testing.data_test_mission_2.mission.worker;

import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.ui.Log;

public class DataTestWorkerHandler extends WorkerHandler {

    private StopWatch stop_watch;

    //3 - 4.5 mins
    private int random_num = Random.nextInt(180, 270);

    @Override
    public Worker decide() {
        if (stop_watch == null)
            stop_watch = StopWatch.start();

        Log.fine("[Seconds]: " + stop_watch.getElapsed().getSeconds());
        if (stop_watch.getElapsed().getSeconds() >= random_num) {
            int rand = Random.nextInt(1, 10);
            Log.fine("CASE: " + rand);
            switch (rand) {
                case 1:
                    Tabs.open(Tab.COMBAT);
                    break;
                case 2:
                    if (!Movement.isRunEnabled())
                        Movement.toggleRun(true);
                    else
                        Movement.toggleRun(false);
                    break;
                case 3:
                    Tabs.open(Tab.SKILLS);
                    break;
                case 4:
                    Tabs.open(Tab.QUEST_LIST);
                    break;
                case 5:
                    Tabs.open(Tab.INVENTORY);
                    break;
                case 6:
                    Tabs.open(Tab.EQUIPMENT);
                    break;
                case 7:
                    Tabs.open(Tab.OPTIONS);
                    break;
                case 8:
                    Tabs.open(Tab.FRIENDS_LIST);
                    break;
                case 9:
                    Tabs.open(Tab.EMOTES);
                    break;
                case 10:
                    Tabs.open(Tab.MUSIC_PLAYER);
                    break;
            }

            stop_watch.reset();
            random_num = Random.nextInt(180, 270);
        }

        return null;
    }
}
