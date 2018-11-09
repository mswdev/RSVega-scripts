package sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.impl;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.EnterAmount;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.input.Keyboard;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.WithdrawItemWorker;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.BlastFurnaceMission;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.BlastFurnaceWorker;

import java.util.function.Predicate;

public class PayCoffer extends BlastFurnaceWorker {

    public static final int COFFER_VARP = 795;
    public static final int COFFER_MIN = 10000;
    private static final int DEPOSIT_AMOUNT = 100000;
    private static final Predicate<SceneObject> COFFER = a -> a.getName().equals("Coffer");

    private final WithdrawItemWorker withdraw_coins;

    public PayCoffer(BlastFurnaceMission mission) {
        super(mission);
        withdraw_coins = new WithdrawItemWorker<>(BlastFurnaceMission.COINS, Bank.WithdrawMode.ITEM, DEPOSIT_AMOUNT);
    }

    @Override
    public void work() {
        if (Inventory.getCount(true, BlastFurnaceMission.COINS) < DEPOSIT_AMOUNT) {
            withdraw_coins.work();
            mission.can_end = withdraw_coins.itemNotFound();
        } else {
            final SceneObject object = SceneObjects.getNearest(COFFER);
            if (object == null)
                return;

            if (!Dialog.isOpen() && !EnterAmount.isOpen()) {
                if (object.click())
                    Time.sleepUntil(Dialog::isOpen, 1500);
            } else {
                if (Dialog.isViewingChatOptions()) {
                    if (Dialog.process(0))
                        Time.sleepUntil(EnterAmount::isOpen, 1500);
                } else {
                    Keyboard.sendText(Integer.toString(DEPOSIT_AMOUNT));
                    Keyboard.pressEnter();
                    Time.sleepUntil(() -> !EnterAmount.isOpen(), 1500);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Paying coffer";
    }
}

