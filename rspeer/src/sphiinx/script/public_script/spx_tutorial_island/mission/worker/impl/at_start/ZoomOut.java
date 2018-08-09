package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.at_start;

import org.rspeer.runetek.api.commons.Time;
import sphiinx.api.game.GameSettings;
import sphiinx.script.public_script.spx_tutorial_island.data.Vars;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class ZoomOut extends TIWorker {


    public ZoomOut(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        if (!Vars.get().at_start_zoom_out)
            return false;

        return GameSettings.getZoomLevel() != 4;
    }

    @Override
    public void work() {
        if (GameSettings.setZoomLevel(4))
            Time.sleepUntil(() -> GameSettings.getZoomLevel() == 4, 1500);
    }

    @Override
    public String toString() {
        return "[START]: Zooming out";
    }
}

