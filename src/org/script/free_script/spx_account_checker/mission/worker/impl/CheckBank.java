package org.script.free_script.spx_account_checker.mission.worker.impl;

import org.api.game.pricechecking.PriceCheck;
import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.ui.Log;
import org.script.free_script.spx_account_checker.data.Vars;
import org.script.free_script.spx_account_checker.mission.AccountCheckerMission;

import java.io.IOException;

public class CheckBank extends Worker {

    private final int coinsId = 995;
    private final AccountCheckerMission mission;

    public CheckBank(AccountCheckerMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving())
            return;

        if (Bank.isOpen()) {
            Vars.get().osrsData.putIfAbsent("bank_worth", getBankWorth());
            Vars.get().checkBank = false;
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
            if (item.getId() == coinsId) {
                total += item.getStackSize();
                continue;
            }

            try {
                total += PriceCheck.getOSBuddyPrice(item.getId()) * item.getStackSize();
            } catch (IOException e) {
                e.printStackTrace();
                Log.severe("[ACCOUNT CHECKER]: Unable to get OSBuddy prices.");
                mission.getScript().setStopping(true);
            }
        }

        return total > 0 ? String.valueOf(total) : "0";
    }

    @Override
    public String toString() {
        return "Checking bank";
    }
}

