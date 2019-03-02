package org.api.script.impl.mission.fishing_mission.worker;

import org.api.game.skills.fishing.FishType;
import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.api.script.impl.worker.MovementWorker;
import org.api.script.impl.worker.banking.WithdrawWorker;
import org.api.script.impl.worker.interactables.NpcWorker;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.tab.Inventory;

public class FishingWorkerHandler extends WorkerHandler {

    private boolean temp_bool;

    @Override
    public Worker decide() {
        final FishType fish_type = FishType.SHRIMP;
        final Item shrimp_item = Inventory.getFirst(fish_type.getName());
        final Item anchovies_item = Inventory.getFirst("Raw anchovies");
        if (Inventory.containsOnly(fish_type.getRequiredEquipmentIDs()))
            temp_bool = false;

        if ((shrimp_item != null || anchovies_item != null) && (temp_bool || Inventory.isFull())) {
            temp_bool = true;

            if (shrimp_item != null)
                shrimp_item.interact(a -> a.equals("Drop"));

            if (anchovies_item != null)
                anchovies_item.interact(a -> a.equals("Drop"));
            return null;
        }

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
