package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.ui.Log;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.AirOrbChargeWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank.GetCosmicRunes;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank.GetStamina;

import java.util.function.Predicate;

public class GetItemFromBank extends AirOrbChargeWorker {

    protected Predicate<Item> item;
    protected int amount;

    public GetItemFromBank(AirOrbChargeMission mission) {
        super(mission);
    }

    @Override
    public void work() {
            if (Bank.isOpen()) {
                if (Bank.contains(item) && Bank.getCount(GetCosmicRunes.COSMIC_RUNE) >= 81) {
                    final int CACHE = Inventory.getItems().length;
                    if (amount == 0) {
                        if (Bank.withdrawAll(item))
                            Time.sleepUntil(() -> Inventory.getItems().length != CACHE, 1500);
                    } else {
                        if (Bank.withdraw(item, amount))
                            Time.sleepUntil(() -> Inventory.getItems().length != CACHE, 1500);
                    }
                } else {
                    if (item == GetStamina.STAMINA) {
                        GetStamina.has_stamina = false;
                    } else {
                        //todo NEEDS MULE
                        Log.severe("We don't have anymore of the item, we need to mule.");
                        mission.can_end = true;
                    }
                }
            } else {
                if (BankLocation.getNearestWithdrawable().getPosition().distance() <= 15) {
                    if (Bank.open(BankLocation.getNearestWithdrawable()))
                        Time.sleepUntil(Bank::isOpen, 1500);
                } else {
                    if (Movement.walkTo(BankLocation.getNearestWithdrawable().getPosition().randomize(3)))
                        Time.sleepUntil(() -> BankLocation.getNearestWithdrawable().getPosition().distance() <= 15, 1500);
                }
            }
    }

    @Override
    public String toString() {
        return "Getting items from the bank";
    }
}

