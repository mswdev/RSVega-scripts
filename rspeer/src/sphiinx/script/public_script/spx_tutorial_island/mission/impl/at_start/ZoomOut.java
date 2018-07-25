package sphiinx.script.public_script.spx_tutorial_island.mission.impl.at_start;

import org.rspeer.runetek.api.commons.Time;
import sphiinx.api.game.GameSettings;
import sphiinx.script.public_script.spx_tutorial_island.data.Vars;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

public class ZoomOut extends TI_Worker {



    public ZoomOut(TI_Mission mission) {
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

