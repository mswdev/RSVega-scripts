package org.api.script.impl.mission.tutorial_island_mission.worker.impl.at_start;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.tutorial_island_mission.TutorialIslandMission;
import org.api.script.impl.mission.tutorial_island_mission.data.DisplayNameType;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.EnterInput;
import org.rspeer.runetek.api.component.Interfaces;

public class CharacterDisplayNameWorker extends Worker {

    static final int DISPLAY_NAME_VARPBIT = 5605;
    private static final int CHOOSE_DISPLAY_NAME_INTER = 558;
    private final TutorialIslandMission mission;

    CharacterDisplayNameWorker(TutorialIslandMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return Varps.getBitValue(DISPLAY_NAME_VARPBIT) != 0;
    }

    @Override
    public void work() {
        if (Varps.getBitValue(DISPLAY_NAME_VARPBIT) == DisplayNameType.SEARCHING.getVarpbitValue())
            return;

        if (Varps.getBitValue(DISPLAY_NAME_VARPBIT) == DisplayNameType.NOT_AVAILABLE.getVarpbitValue()) {
            final InterfaceComponent display_name_suggestion = Interfaces.getFirst(CHOOSE_DISPLAY_NAME_INTER, a -> a.getIndex() == Random.nextInt(14, 16));
            if (display_name_suggestion != null && display_name_suggestion.isVisible()) {
                display_name_suggestion.click();
                return;
            }

            final InterfaceComponent lookup_display_name = Interfaces.getFirst(CHOOSE_DISPLAY_NAME_INTER, a -> a.containsAction("Look up name"));
            if (lookup_display_name == null)
                return;

            if (!EnterInput.isOpen()) {
                lookup_display_name.click();
                Time.sleepUntil(EnterInput::isOpen, 1500);
            }

            String display_name = mission.getUsername().split("@")[0];
            EnterInput.initiate(display_name);
        }

        final InterfaceComponent set_display_name = Interfaces.getFirst(CHOOSE_DISPLAY_NAME_INTER, a -> a.containsAction("Set name") && a.isVisible() && a.getText().isEmpty());
        if (set_display_name == null)
            return;

        set_display_name.click();
        Time.sleepUntil(() -> Varps.getBitValue(DISPLAY_NAME_VARPBIT) == DisplayNameType.SET.getVarpbitValue(), 1500);
    }

    @Override
    public String toString() {
        return "Executing character display name worker.";
    }
}

