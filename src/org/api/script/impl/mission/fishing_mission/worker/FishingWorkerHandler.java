package org.api.script.impl.mission.fishing_mission.worker;

import org.rspeer.runetek.api.component.tab.Inventory;
import org.api.game.skills.fishing.FishType;
import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.api.script.impl.worker.MovementWorker;
import org.api.script.impl.worker.banking.DepositWorker;
import org.api.script.impl.worker.banking.WithdrawWorker;
import org.api.script.impl.worker.interactables.NpcWorker;

public class FishingWorkerHandler extends WorkerHandler {

    @Override
    public Worker decide() {
        if (Inventory.isFull())
            return new DepositWorker();

        final FishType fish_type = FishType.SHRIMP;
        if (!Inventory.contains(fish_type.getRequiredEquipmentIDs())) {
            for (int id : fish_type.getRequiredEquipmentIDs()) {
                if (Inventory.contains(id))
                    continue;

                return new WithdrawWorker(a -> a.getId() == id);
            }
        }

        return new NpcWorker(a -> a.getName().equals("Fishing spot"), a -> a.equals(fish_type.getAction()), new MovementWorker(fish_type.getFishLocation()[0].getPosition()));
    }
}
