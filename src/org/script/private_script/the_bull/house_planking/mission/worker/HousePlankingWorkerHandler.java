package org.script.private_script.the_bull.house_planking.mission.worker;

import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.api.script.impl.worker.banking.DepositWorker;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.House;
import org.script.private_script.the_bull.house_planking.mission.HousePlankingMission;
import org.script.private_script.the_bull.house_planking.mission.worker.impl.*;

public class HousePlankingWorkerHandler extends WorkerHandler {

    private final HousePlankingMission mission;
    private final EquipStaffOfAir equipStaffOfAir;
    private final WithdrawLogs withdrawLogs;
    private final WithdrawCoins withdrawCoins;
    private final WithdrawEarthRune withdrawEarthRune;
    private final WithdrawLawRune withdrawLawRune;
    private final UnnoteLogs unnoteLogs;
    private final TeleportToHouse teleportToHouse;
    private final ButlerDialogue butlerDialogue;
    private final TeleportToLumbridge teleportToLumbridge;

    public HousePlankingWorkerHandler(HousePlankingMission mission) {
        this.mission = mission;
        equipStaffOfAir = new EquipStaffOfAir(mission);
        withdrawLogs = new WithdrawLogs(mission);
        withdrawCoins = new WithdrawCoins(mission);
        withdrawEarthRune = new WithdrawEarthRune(mission);
        withdrawLawRune = new WithdrawLawRune(mission);
        unnoteLogs = new UnnoteLogs(mission);
        teleportToHouse = new TeleportToHouse();
        butlerDialogue = new ButlerDialogue(mission);
        teleportToLumbridge = new TeleportToLumbridge(mission);
    }

    @Override
    public Worker decide() {
        if (Inventory.contains(HousePlankingMission.RING_OF_WEALTH_IDS[5] + 1))
            return new DepositWorker(a -> a.getId() == HousePlankingMission.RING_OF_WEALTH_IDS[5] + 1);

        if (House.isInside()) {
            if (Inventory.contains(mission.getLogType().getItemId()))
                return butlerDialogue;

            return teleportToLumbridge;
        }

        if (!Equipment.contains(EquipStaffOfAir.STAFF_OF_AIR))
            return equipStaffOfAir;

        if (Inventory.getCount(true, WithdrawCoins.COINS) < 6000)
            return withdrawCoins;

        if (Inventory.getCount(WithdrawEarthRune.EARTH_RUNE) <= 0)
            return withdrawEarthRune;

        if (Inventory.getCount(WithdrawLawRune.LAW_RUNE) <= 0)
            return withdrawLawRune;

        if (!Inventory.contains(mission.getLogType().getItemId())) {
            if (BankLocation.LUMBRIDGE_PVP.getPosition().distance() > 10)
                return teleportToLumbridge;

            if (Inventory.getCount(mission.getLogType().getNotedItemId()) <= 0)
                return withdrawLogs;

            return unnoteLogs;
        }

        return teleportToHouse;
    }
}

