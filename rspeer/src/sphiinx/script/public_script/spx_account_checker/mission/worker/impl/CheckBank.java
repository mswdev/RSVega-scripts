package sphiinx.script.public_script.spx_account_checker.mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.api.game.pricechecking.PriceChecking;
import sphiinx.script.public_script.spx_account_checker.data.Vars;
import sphiinx.script.public_script.spx_account_checker.mission.AccountCheckerMission;
import sphiinx.script.public_script.spx_account_checker.mission.worker.AccountCheckerWorker;

public class CheckBank extends AccountCheckerWorker {

    private final int COINS_ID = 995;

    public CheckBank(AccountCheckerMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving())
            return;

        if (Bank.isOpen()) {
            Vars.get().ACCOUNT_DATA.putIfAbsent("bank_worth", getBankWorth());
            Vars.get().check_bank = false;
        } else {
            if (BankLocation.getNearestDepositBox().getPosition().distance() <= 15) {
                if (Bank.open(BankLocation.getNearestWithdrawable()))
                    Time.sleepUntil(Bank::isOpen, 1500);
            } else {
                if (Movement.walkTo(BankLocation.getNearestWithdrawable().getPosition().randomize(3)))
                    Time.sleepUntil(() -> BankLocation.getNearest().getPosition().distance() <= 15, 1500);
            }
        }
    }

    private String getBankWorth() {
        int total = 0;
        for (Item item : Bank.getItems()) {
            if (item.getId() == COINS_ID) {
                total += item.getStackSize();
                continue;
            }

            total += PriceChecking.getRSPrice(item.getId()) * item.getStackSize();
        }

        return total > 0 ? String.valueOf(total) : null;
    }

    @Override
    public String toString() {
        return "Checking bank";
    }
}

