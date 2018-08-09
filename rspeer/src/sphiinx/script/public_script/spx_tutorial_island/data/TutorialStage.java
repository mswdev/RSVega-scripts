package sphiinx.script.public_script.spx_tutorial_island.data;

import org.rspeer.runetek.api.Varps;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class TutorialStage {

    private static final int VARP_CONFIG_ID = 281;
    private final int[] VARPS;
    private TIWorker worker;

    TutorialStage(TIWorker worker, int... varps) {
        this.VARPS = varps;
        this.worker = worker;
    }

    public boolean isValid() {
        if (!worker.shouldExecute())
            return false;

        for (int val : VARPS)
            if (Varps.get(VARP_CONFIG_ID) == val || val == -1)
                return true;

        return false;
    }

    public void execute() {
        worker.work();
    }

    public String toString() {
        return worker.toString();
    }
}
