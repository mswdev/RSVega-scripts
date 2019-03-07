package org.api.script.impl.mission.tutorial_island_mission.worker;

import org.api.game.ClientSettings;
import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.api.script.impl.mission.tutorial_island_mission.TutorialIslandMission;
import org.api.script.impl.mission.tutorial_island_mission.data.TutorialState;
import org.api.script.impl.mission.tutorial_island_mission.worker.impl.at_end.*;
import org.api.script.impl.mission.tutorial_island_mission.worker.impl.at_start.CharacterSetupWorker;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.InterfaceOptions;
import org.rspeer.runetek.api.component.tab.Inventory;

public class TutorialIslandWorkerHandler extends WorkerHandler {

    private final TutorialIslandMission mission;
    private final CharacterSetupWorker character_setup_worker;
    private final HideRoofs hide_roofs;
    private final SetAudio set_audio;
    private final SetBrightness set_brightness;
    private final SetZoom set_zoom;
    private final DropItems drop_items;
    private final DepositItems bank_items;
    private final StayLoggedIn stay_logged_in;
    private final WalkToPosition walk_to_position;
    private final Logout logout;

    public TutorialIslandWorkerHandler(TutorialIslandMission mission) {
        this.mission = mission;
        character_setup_worker = new CharacterSetupWorker(mission);
        hide_roofs = new HideRoofs();
        set_audio = new SetAudio(mission);
        set_brightness = new SetBrightness(mission);
        set_zoom = new SetZoom(mission);
        drop_items = new DropItems();
        bank_items = new DepositItems();
        stay_logged_in = new StayLoggedIn();
        walk_to_position = new WalkToPosition(mission);
        logout = new Logout(mission);
    }

    @Override
    public Worker decide() {
        if (TutorialState.isInVarp(TutorialState.CHARACTER_DESIGN))
            return character_setup_worker;

        if (Varps.get(TutorialIslandMission.TUTORIAL_ISLAND_VARP) >= 1000) {
            if (Dialog.canContinue())
                Dialog.processContinue();

            if (mission.getArgs().hide_roofs && !InterfaceOptions.Display.isRoofsHidden())
                return hide_roofs;

            if (mission.getArgs().set_audio > 0 && (5 - InterfaceOptions.Audio.getMusicVolume() != mission.getArgs().set_audio || 5 - InterfaceOptions.Audio.getSoundEffectVolume() != mission.getArgs().set_audio || 5 - InterfaceOptions.Audio.getAreaSoundVolume() != mission.getArgs().set_audio))
                return set_audio;

            if (mission.getArgs().set_brightness > 0 && InterfaceOptions.Display.getBrightness() != mission.getArgs().set_brightness)
                return set_brightness;

            if (mission.getArgs().set_zoom > 0 && ClientSettings.getZoomLevel() != mission.getArgs().set_zoom)
                return set_zoom;

            if (mission.getArgs().drop_items && Inventory.getCount() > 0)
                return drop_items;

            if (mission.getArgs().bank_items && Inventory.getCount() > 0)
                return bank_items;

            if (mission.getArgs().walk_position != null && mission.getArgs().walk_position.distance() > 10)
                return walk_to_position;

            if (mission.getArgs().stay_logged_in)
                return stay_logged_in;

            if (mission.getArgs().end_on_completion) {
                mission.setShouldEnd(true);
                return stay_logged_in;
            }

            return logout;
        }

        return TutorialState.getValidState().getWorker();
    }
}

