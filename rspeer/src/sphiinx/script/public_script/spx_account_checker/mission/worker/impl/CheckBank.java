package sphiinx.script.public_script.spx_account_checker.mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.ui.Log;
import sphiinx.script.public_script.spx_tutorial_island.api.game_util.pricechecking.PriceCheck;
import sphiinx.script.public_script.spx_account_checker.data.Vars;
import sphiinx.script.public_script.spx_account_checker.mission.AccountCheckerMission;
import sphiinx.script.public_script.spx_account_checker.mission.worker.AccountCheckerWorker;

import java.io.IOException;

public class CheckBank extends AccountCheckerWorker {

    private final int coins_id = 995;

    public CheckBank(AccountCheckerMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving())
            return;

        if (Bank.isOpen()) {
            Vars.get().osrs_data.putIfAbsent("bank_worth", getBankWorth());
            Vars.get().check_bank = false;
        } else {
            if (BankLocation.getNearestWithdrawable().getPosition().distance() <= 15) {
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
        final Item[] items = Bank.getItems();
        if (items == null)
            return "0";

        for (Item item : items) {
            if (item.getId() == coins_id) {
                total += item.getStackSize();
                continue;
            }

            try {
                total += PriceCheck.getOSBuddyPrice(item.getId()) * item.getStackSize();
            } catch (IOException e) {
                e.printStackTrace();
                Log.severe("[ACCOUNT CHECKER]: Unable to get OSBuddy prices.");
                mission.main.setStopping(true);
            }
        }

        return total > 0 ? String.valueOf(total) : "0";
    }

    @Override
    public String toString() {
        return "Checking bank";
    }
}

