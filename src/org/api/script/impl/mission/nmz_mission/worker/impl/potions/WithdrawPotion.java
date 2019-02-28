package org.api.script.impl.mission.nmz_mission.worker.impl.potions;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.worker.interactables.SceneObjectWorker;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.EnterInput;
import org.rspeer.runetek.api.component.tab.Inventory;

import java.util.function.Predicate;

public class WithdrawPotion extends Worker {

    private final Predicate<SceneObject> potion_barrel;
    private final SceneObjectWorker scene_object_worker;
    private final int amount;

    public WithdrawPotion(PotionType potion_type, int amount) {
        this.amount = amount;
        potion_barrel = a -> a.getName().equals(potion_type.getBarrelName());
        scene_object_worker = new SceneObjectWorker(potion_barrel, a -> a.equals("Take"));
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (!EnterInput.isOpen()) {
            scene_object_worker.work();
            return;
        }

        final int inventory_cache = Inventory.getCount();
        EnterInput.initiate(amount);
        Time.sleepUntil(() -> inventory_cache != Inventory.getCount(), 1500);
    }

    @Override
    public String toString() {
        return "Withdrawing potion";
    }
}

