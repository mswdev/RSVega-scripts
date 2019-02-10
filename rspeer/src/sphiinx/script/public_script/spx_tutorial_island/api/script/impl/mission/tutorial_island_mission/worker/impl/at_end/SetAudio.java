package sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.tutorial_island_mission.worker.impl.at_end;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.InterfaceOptions;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.game.ClientSettings;
import sphiinx.script.public_script.spx_tutorial_island.Main;

public class SetAudio extends Worker {


    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (!Tabs.isOpen(Tab.OPTIONS))
            if (Tabs.open(Tab.OPTIONS))
                Time.sleepUntil(() -> Tabs.isOpen(Tab.OPTIONS), 1500);

        if (ClientSettings.setMusicAudioLevel(Main.ARGS.set_audio))
            Time.sleepUntil(() -> 5 - InterfaceOptions.Audio.getMusicVolume() == Main.ARGS.set_audio, 1500);

        if (ClientSettings.setEffectAudioLevel(Main.ARGS.set_audio))
            Time.sleepUntil(() -> 5 - InterfaceOptions.Audio.getSoundEffectVolume() == Main.ARGS.set_audio, 1500);

        if (ClientSettings.setAreaAudioLevel(Main.ARGS.set_audio))
            Time.sleepUntil(() -> 5 - InterfaceOptions.Audio.getAreaSoundVolume() == Main.ARGS.set_audio, 1500);
    }

    @Override
    public String toString() {
        return "Setting audio.";
    }
}

