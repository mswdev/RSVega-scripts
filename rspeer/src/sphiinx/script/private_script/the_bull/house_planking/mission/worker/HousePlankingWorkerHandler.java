package sphiinx.script.private_script.the_bull.house_planking.mission.worker;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.House;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.framework.worker.WorkerHandler;
import sphiinx.script.private_script.the_bull.house_planking.Main;
import sphiinx.script.private_script.the_bull.house_planking.mission.HousePlankingMission;
import sphiinx.script.private_script.the_bull.house_planking.mission.worker.impl.*;

public class HousePlankingWorkerHandler extends WorkerHandler {

    private final EquipStaffOfAir equip_staff_of_air;
    private final WithdrawLogs withdraw_logs;
    private final WithdrawCoins withdraw_coins;
    private final WithdrawEarthRune withdraw_earth_rune;
    private final WithdrawLawRune withdraw_law_rune;
    private final UnnoteLogs unnote_logs;
    private final TeleportToHouse teleport_to_house;
    private final ButlerDialogue butler_dialogue;
    private final TeleportToLumbridge teleport_to_lumbridge;

    public HousePlankingWorkerHandler(HousePlankingMission mission) {
        equip_staff_of_air = new EquipStaffOfAir(mission);
        withdraw_logs = new WithdrawLogs(mission);
        withdraw_coins = new WithdrawCoins(mission);
        withdraw_earth_rune = new WithdrawEarthRune(mission);
        withdraw_law_rune = new WithdrawLawRune(mission);
        unnote_logs = new UnnoteLogs();
        teleport_to_house = new TeleportToHouse();
        butler_dialogue = new ButlerDialogue();
        teleport_to_lumbridge = new TeleportToLumbridge();
    }

    @Override
    public Worker decide() {
        if (House.isInside()) {
            if (Inventory.contains(Main.ARGS.LOG_TYPE1.getItemID()))
                return butler_dialogue;

            return teleport_to_lumbridge;
        }

        if (!Equipment.contains(EquipStaffOfAir.STAFF_OF_AIR))
            return equip_staff_of_air;

        if (Inventory.getCount(true, WithdrawCoins.COINS) < 6000)
            return withdraw_coins;

        if (Inventory.getCount(WithdrawEarthRune.EARTH_RUNE) <= 0)
            return withdraw_earth_rune;

        if (Inventory.getCount(WithdrawLawRune.LAW_RUNE) <= 0)
            return withdraw_law_rune;

        if (!Inventory.contains(Main.ARGS.LOG_TYPE1.getItemID())) {
            if (BankLocation.LUMBRIDGE_PVP.getPosition().distance() > 10)
                return teleport_to_lumbridge;

            if (Inventory.getCount(Main.ARGS.LOG_TYPE1.getItemID() + 1) <= 0)
                return withdraw_logs;

            return unnote_logs;
        }

        return teleport_to_house;
    }
}

