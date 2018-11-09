package sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.OpenBankWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.AirOrbRestockMission;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.AirOrbRestockWorker;

public class DepositInventory extends AirOrbRestockWorker {

    private final OpenBankWorker open_bank;

    public DepositInventory(AirOrbRestockMission mission) {
        super(mission);
        this.open_bank = new OpenBankWorker(false);
    }

    @Override
    public void work() {
        if (Bank.isOpen()) {
            if (Bank.depositInventory())
                Time.sleepUntil(() -> Inventory.getFreeSlots() == 28, 1500);
        } else {
            open_bank.work();
        }
    }

    @Override
    public String toString() {
        return "Depositing inventory";
    }
}

