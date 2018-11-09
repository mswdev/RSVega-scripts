package sphiinx.script.private_script.zerker.revenants.mission.worker.impl.travel_to_edgeville;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.ItemActionWorker;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.private_script.zerker.revenants.mission.RevenantMission;

import java.util.function.Predicate;

public class WithdrawAmuletOfGlory extends Worker<RevenantMission> {

    public static final String AMULET_OF_GLORY_NAME = "glory(";
    private static final Predicate<Item> AMULET_OF_GLORY = a -> a.getName().contains(AMULET_OF_GLORY_NAME);
    private static final ItemActionWorker EQUIP_GLORY = new ItemActionWorker(AMULET_OF_GLORY);

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        EQUIP_GLORY.work();
        mission.can_end = EQUIP_GLORY.itemNotFound();
    }

    @Override
    public String toString() {
        return "Withdrawing Amulet of glory";
    }
}

