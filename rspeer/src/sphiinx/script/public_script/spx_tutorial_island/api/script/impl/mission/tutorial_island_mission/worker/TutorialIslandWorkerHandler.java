package sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.tutorial_island_mission.worker;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.InterfaceOptions;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.script.public_script.spx_tutorial_island.api.game.ClientSettings;
import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.worker.WorkerHandler;
import sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.tutorial_island_mission.TutorialIslandMission;
import sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.tutorial_island_mission.worker.impl.at_end.*;
import sphiinx.script.public_script.spx_tutorial_island.Main;
import sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.tutorial_island_mission.data.TutorialState;

public class TutorialIslandWorkerHandler extends WorkerHandler {

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
        hide_roofs = new HideRoofs();
        set_audio = new SetAudio();
        set_brightness = new SetBrightness();
        set_zoom = new SetZoom();
        drop_items = new DropItems();
        bank_items = new DepositItems();
        stay_logged_in = new StayLoggedIn();
        walk_to_position = new WalkToPosition();
        logout = new Logout(mission);
    }

    @Override
    public Worker decide() {
        if (Varps.get(TutorialIslandMission.TUTORIAL_ISLAND_VARP) >= 1000) {
            if (Main.ARGS.hide_roofs && !InterfaceOptions.Display.isRoofsHidden())
                return hide_roofs;

            if (Main.ARGS.set_audio > 0 && (5 - InterfaceOptions.Audio.getMusicVolume() != Main.ARGS.set_audio || 5 - InterfaceOptions.Audio.getSoundEffectVolume() != Main.ARGS.set_audio || 5 - InterfaceOptions.Audio.getAreaSoundVolume() != Main.ARGS.set_audio))
                return set_audio;

            if (Main.ARGS.set_brightness > 0 && InterfaceOptions.Display.getBrightness() != Main.ARGS.set_brightness)
                return set_brightness;

            if (Main.ARGS.set_zoom > 0 && ClientSettings.getZoomLevel() != Main.ARGS.set_zoom)
                return set_zoom;

            if (Main.ARGS.drop_items && Inventory.getCount() > 0)
                return drop_items;

            if (Main.ARGS.bank_items && Inventory.getCount() > 0)
                return bank_items;

            if (Main.ARGS.walk_position != null && Main.ARGS.walk_position.distance() > 10)
                return walk_to_position;

            if (Main.ARGS.stay_logged_in)
                return stay_logged_in;

            return logout;
        }

        return TutorialState.getValidState().getWorker();
    }
}

