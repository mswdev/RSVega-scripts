package sphiinx.script.private_script.saranga07.blast_furnace.mission.worker;

import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.BlastFurnaceMission;

public abstract class BlastFurnaceWorker extends Worker<BlastFurnaceMission> {


    public BlastFurnaceWorker(BlastFurnaceMission mission) {
        super(mission);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }
}

