package org.api.script.impl.mission.nmz_mission.worker.impl.potions;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.worker.interactables.SceneObjectWorker;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.component.EnterInput;
import org.rspeer.runetek.api.component.Interfaces;

import java.util.function.Predicate;

public class BuyPotion extends Worker {

    private final Predicate<SceneObject> rewards_chest_predicate = a -> a.getName().equals("Rewards chest");
    private final SceneObjectWorker scene_object_worker = new SceneObjectWorker(rewards_chest_predicate);
    private final PotionType potion_type;
    private final int amount;
    private final int rewards_chest_component_predicate = 206;
    private final int rewards_chest_potions_component_predicate = 6;

    public BuyPotion(PotionType potion_type, int amount) {
        this.potion_type = potion_type;
        this.amount = amount;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        final InterfaceComponent rewards_chest_component = Interfaces.getComponent(rewards_chest_component_predicate, rewards_chest_potions_component_predicate, potion_type.getShopInterfaceID());
        if (rewards_chest_component == null) {
            scene_object_worker.work();
            return;
        }

        if (!EnterInput.isOpen()) {
            rewards_chest_component.interact("Buy-X");
            return;
        }

        EnterInput.initiate(amount);
    }

    @Override
    public String toString() {
        return "Buying potion";
    }
}

