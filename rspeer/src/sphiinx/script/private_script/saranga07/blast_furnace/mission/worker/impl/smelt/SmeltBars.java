package sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.impl.smelt;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.BlastFurnaceMission;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.BlastFurnaceWorker;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.impl.WithdrawCoalBag;

import java.util.function.Predicate;

public class SmeltBars extends BlastFurnaceWorker {

    private static final Predicate<SceneObject> CONVEYOR_BELT = a -> a.getName().equals("Conveyor belt") && a.containsAction("Put-ore-on");
    private static final Predicate<String> EMPTY_COAL_BAG = a -> a.equals("Empty");

    public SmeltBars(BlastFurnaceMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        final SceneObject conveyor_belt = SceneObjects.getNearest(CONVEYOR_BELT);
        if (conveyor_belt == null)
            return;

        if (Inventory.isFull()) {
            if (conveyor_belt.click())
                Time.sleepUntil(() -> !Inventory.isFull(), 1500);

            if (mission.is_coal_bag_empty)
                mission.is_smelting = false;
        } else {
            final Item coal_bag = Inventory.getFirst(WithdrawCoalBag.COAL_BAG);
            if (coal_bag == null)
                return;

            if (coal_bag.interact(EMPTY_COAL_BAG))
                if (Time.sleepUntil(Inventory::isFull, 1200))
                    mission.is_coal_bag_empty = true;
        }
    }

    @Override
    public String toString() {
        return "Smelting bars";
    }
}

