package org.api.script.impl.mission.blast_furnace_mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.blast_furnace_mission.BlastFurnaceMission;
import org.api.script.impl.worker.banking.WithdrawWorker;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.EnterInput;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.input.Keyboard;
import org.rspeer.runetek.api.scene.SceneObjects;

import java.util.function.Predicate;

public class PayCoffer extends Worker {

    public static final int COFFER_VARP = 795;
    public static final int COFFER_MIN = 10000;
    private static final int DEPOSIT_AMOUNT = 100000;
    private static final Predicate<SceneObject> COFFER = a -> a.getName().equals("Coffer");
    private final BlastFurnaceMission mission;

    private final WithdrawWorker withdraw_worker = new WithdrawWorker(BlastFurnaceMission.COINS, DEPOSIT_AMOUNT, Bank.WithdrawMode.ITEM);

    public PayCoffer(BlastFurnaceMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Inventory.getCount(true, BlastFurnaceMission.COINS) < DEPOSIT_AMOUNT) {
            withdraw_worker.work();
            mission.should_end = withdraw_worker.itemNotFound();
        } else {
            final SceneObject object = SceneObjects.getNearest(COFFER);
            if (object == null)
                return;

            if (!Dialog.isOpen() && !EnterInput.isOpen()) {
                if (object.click())
                    Time.sleepUntil(Dialog::isOpen, 1500);
            } else {
                if (Dialog.isViewingChatOptions()) {
                    if (Dialog.process(0))
                        Time.sleepUntil(EnterInput::isOpen, 1500);
                } else {
                    Keyboard.sendText(Integer.toString(DEPOSIT_AMOUNT));
                    Keyboard.pressEnter();
                    Time.sleepUntil(() -> !EnterInput.isOpen(), 1500);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Paying coffer";
    }
}

