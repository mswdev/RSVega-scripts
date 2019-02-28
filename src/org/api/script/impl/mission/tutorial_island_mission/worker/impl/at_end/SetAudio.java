package org.api.script.impl.mission.tutorial_island_mission.worker.impl.at_end;

import org.api.game.ClientSettings;
import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.tutorial_island_mission.TutorialIslandMission;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.InterfaceOptions;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;

public class SetAudio extends Worker {

    private final TutorialIslandMission mission;

    public SetAudio(TutorialIslandMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (!Tabs.isOpen(Tab.OPTIONS))
            if (Tabs.open(Tab.OPTIONS))
                Time.sleepUntil(() -> Tabs.isOpen(Tab.OPTIONS), 1500);

        if (ClientSettings.setMusicAudioLevel(mission.getArgs().set_audio))
            Time.sleepUntil(() -> 5 - InterfaceOptions.Audio.getMusicVolume() == mission.getArgs().set_audio, 1500);

        if (ClientSettings.setEffectAudioLevel(mission.getArgs().set_audio))
            Time.sleepUntil(() -> 5 - InterfaceOptions.Audio.getSoundEffectVolume() == mission.getArgs().set_audio, 1500);

        if (ClientSettings.setAreaAudioLevel(mission.getArgs().set_audio))
            Time.sleepUntil(() -> 5 - InterfaceOptions.Audio.getAreaSoundVolume() == mission.getArgs().set_audio, 1500);
    }

    @Override
    public String toString() {
        return "Setting audio.";
    }
}

