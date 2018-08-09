package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.at_start;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.InterfaceOptions;
import org.rspeer.runetek.api.component.Interfaces;
import sphiinx.script.public_script.spx_tutorial_island.data.Vars;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class TurnOffAudio extends TIWorker {

    private final int INTER_OPTION_MASTER = 261;
    private final int INTER_MUSIC = 27;
    private final int INTER_EFFECT = 33;
    private final int INTER_AREA = 39;

    public TurnOffAudio(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        if (!Vars.get().at_start_turn_off_audio)
            return false;

        return InterfaceOptions.Audio.getMusicVolume() != 4 || InterfaceOptions.Audio.getSoundEffectVolume() != 4 || InterfaceOptions.Audio.getAreaSoundVolume() != 4;
    }

    @Override
    public void work() {
        final InterfaceComponent MUSIC = Interfaces.getComponent(INTER_OPTION_MASTER, INTER_MUSIC);
        final InterfaceComponent EFFECT = Interfaces.getComponent(INTER_OPTION_MASTER, INTER_EFFECT);
        final InterfaceComponent AREA = Interfaces.getComponent(INTER_OPTION_MASTER, INTER_AREA);
        if (MUSIC == null || EFFECT == null || AREA == null)
            return;

        if (MUSIC.interact(mission.DEFAULT_ACTION))
            Time.sleepUntil(() -> InterfaceOptions.Audio.getMusicVolume() == 4, 1500);

        if (EFFECT.interact(mission.DEFAULT_ACTION))
            Time.sleepUntil(() -> InterfaceOptions.Audio.getSoundEffectVolume() == 4, 1500);

        if (AREA.interact(mission.DEFAULT_ACTION))
            Time.sleepUntil(() -> InterfaceOptions.Audio.getAreaSoundVolume() == 4, 1500);
    }

    @Override
    public String toString() {
        return "[START]: Turning off audio";
    }
}

