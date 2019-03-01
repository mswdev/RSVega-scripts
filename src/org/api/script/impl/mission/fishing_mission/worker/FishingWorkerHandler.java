package org.api.script.impl.mission.fishing_mission.worker;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.api.game.skills.fishing.FishType;
import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.api.script.impl.worker.MovementWorker;
import org.api.script.impl.worker.banking.WithdrawWorker;
import org.api.script.impl.worker.interactables.NpcWorker;

public class FishingWorkerHandler extends WorkerHandler {

    @Override
    public Worker decide() {
        final FishType fish_type = FishType.SHRIMP;
        final Item fish = Inventory.getFirst(fish_type.getName());
        if (fish != null)
            fish.interact(a -> a.equals("Drop"));

        if (!Inventory.contains(fish_type.getRequiredEquipmentIDs())) {
            for (int id : fish_type.getRequiredEquipmentIDs()) {
                if (Inventory.contains(id))
                    continue;

                return new WithdrawWorker(a -> a.getId() == id);
            }
        }

        return new NpcWorker(a -> a.getName().equals("Fishing spot"), a -> a.contains(fish_type.getAction()), new MovementWorker(fish_type.getFishLocation()[4].getPosition()));
    }
}
