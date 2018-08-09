package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.at_end;

import sphiinx.script.public_script.spx_tutorial_island.data.Vars;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class GetIronManItems extends TIWorker {


    public GetIronManItems(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        if (!Vars.get().at_end_get_iron_man_items)
            return false;

        return false;
    }

    @Override
    public void work() {

    }

    @Override
    public String toString() {
        return "[END]: Getting iron man items";
    }
}

