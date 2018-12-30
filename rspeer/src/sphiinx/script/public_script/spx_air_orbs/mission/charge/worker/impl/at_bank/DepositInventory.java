package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.banking.OpenBankWorker;

public class DepositInventory extends Worker {

    private final OpenBankWorker open_bank = new OpenBankWorker(false);

    @Override
    public boolean needsRepeat() {
        return false;
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
        return "Depositing inventory.";
    }
}

