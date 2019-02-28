package org.api.script.impl.mission.nmz_mission.worker;


import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.api.script.impl.mission.nmz_mission.worker.impl.BuyDream;
import org.api.script.impl.mission.nmz_mission.worker.impl.EnterDream;
import org.api.script.impl.mission.nmz_mission.worker.impl.potions.BuyPotion;
import org.api.script.impl.mission.nmz_mission.worker.impl.potions.PotionType;
import org.api.script.impl.mission.nmz_mission.worker.impl.potions.WithdrawPotion;
import org.api.script.impl.worker.interactables.ItemWorker;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.local.Health;


public class NMZWorkerHandler extends WorkerHandler {

    private int drink_value;

    @Override
    public Worker decide() {
        if (Varps.getBitValue(EnterDream.ACTIVE_DREAM_VARPBIT) == 0) {
            if (Varps.getBitValue(PotionType.ABSORPTION.getAmountOwnedVarpbit()) < 80)
                return new BuyPotion(PotionType.ABSORPTION, 255 - PotionType.ABSORPTION.getAmountOwnedVarpbit());

            if (Varps.getBitValue(PotionType.OVERLOAD.getAmountOwnedVarpbit()) < 32)
                return new BuyPotion(PotionType.OVERLOAD, 255 - PotionType.OVERLOAD.getAmountOwnedVarpbit());

            if (PotionType.getCount(PotionType.ABSORPTION) < 80)
                return new WithdrawPotion(PotionType.ABSORPTION, 80 - PotionType.getCount(PotionType.ABSORPTION));

            if (PotionType.getCount(PotionType.OVERLOAD) < 32)
                return new WithdrawPotion(PotionType.OVERLOAD, 32 - PotionType.getCount(PotionType.OVERLOAD));

            if (Varps.getBitValue(BuyDream.dream_potion_varbit) == 0)
                return new BuyDream();

            if (Varps.getBitValue(BuyDream.dream_potion_varbit) > 0) {
                drink_value = Random.nextInt(500, 750);
                return new EnterDream();
            }
        } else {
            if (Inventory.contains(a -> a.getName().contains(PotionType.ABSORPTION.getName())) && Varps.getBitValue(PotionType.ABSORPTION.getActiveVarpbit()) <= drink_value)
                return new DrinkPotion();

            if (Inventory.contains(a -> a.getName().contains(PotionType.OVERLOAD.getName())) && Varps.getBitValue(PotionType.OVERLOAD.getActiveVarpbit()) <= 1 && Health.getCurrent() > 50)
                return new ItemWorker(a -> a.getName().contains(PotionType.OVERLOAD.getName()));
        }

        return null;
    }
}

