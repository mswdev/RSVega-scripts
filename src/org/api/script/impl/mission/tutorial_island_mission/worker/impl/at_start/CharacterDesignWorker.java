package org.api.script.impl.mission.tutorial_island_mission.worker.impl.at_start;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.tutorial_island_mission.data.DesignOption;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class CharacterDesignWorker extends Worker {

    private static final int INTER_MASTER = 269;
    private static final int INTER_ACCEPT_COMPONENT = 99;

    private boolean shouldExecute() {
        final InterfaceComponent CHARACTER_DESIGN = Interfaces.getFirst(INTER_MASTER, a -> a.getIndex() == INTER_ACCEPT_COMPONENT);
        return CHARACTER_DESIGN != null && CHARACTER_DESIGN.isVisible();
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        final List<DesignOption> design_options = new ArrayList<>(Arrays.asList(DesignOption.values()));
        Collections.shuffle(design_options);
        design_options.remove(DesignOption.GENDER);
        design_options.add(0, DesignOption.GENDER);

        for (DesignOption option : design_options) {
            final InterfaceComponent left_option = Interfaces.getFirst(INTER_MASTER, a -> a.getIndex() == option.getComponentIndexLeft());
            final InterfaceComponent right_option = Interfaces.getFirst(INTER_MASTER, a -> a.getIndex() == option.getComponentIndexRight());
            if (left_option == null || right_option == null)
                continue;

            int options = Random.nextInt(0, option.getTotalOptions());
            for (int i = 0; i < options; i++) {
                final boolean right_click = Random.nextInt(0, 1) == 0;
                if (right_click)
                    right_option.interact(a -> true);
                else
                    left_option.interact(a -> true);
            }
        }

        final InterfaceComponent ACCEPT_BUTTON = Interfaces.getFirst(INTER_MASTER, a -> a.getIndex() == INTER_ACCEPT_COMPONENT);
        if (ACCEPT_BUTTON == null)
            return;

        if (ACCEPT_BUTTON.interact(a -> true))
            Time.sleepUntil(() -> !shouldExecute(), 1500);
    }

    @Override
    public String toString() {
        return "Executing character design worker.";
    }
}

