package sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.impl;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Npcs;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.WithdrawItemWorker;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.BlastFurnaceMission;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.BlastFurnaceWorker;

import java.util.function.Predicate;

public class PayForeman extends BlastFurnaceWorker {

    private static final int DEPOSIT_AMOUNT = 2500;
    private static final Predicate<InterfaceComponent> PAY_FOREMAN = a -> a.getText().contains("You must ask the foreman");
    private static final Predicate<Npc> FOREMAN = a -> a.getName().equals("Blast Furnace Foreman");
    private static final Predicate<String> PAY = a -> a.equals("Pay");
    private final WithdrawItemWorker withdraw_coins;
    public boolean paid_foreman;

    public PayForeman(BlastFurnaceMission mission) {
        super(mission);
        withdraw_coins = new WithdrawItemWorker(BlastFurnaceMission.COINS, Bank.WithdrawMode.ITEM, 2500);
    }

    public static boolean shouldPayForeman() {
        final InterfaceComponent component = Interfaces.getFirst(PAY_FOREMAN);
        if (component == null)
            return false;

        return component.isVisible();
    }

    @Override
    public void work() {
        paid_foreman = false;
        if (Inventory.getCount(true, BlastFurnaceMission.COINS) < DEPOSIT_AMOUNT) {
            withdraw_coins.work();
            mission.can_end = withdraw_coins.itemNotFound();
        } else {
            final Npc npc = Npcs.getFirst(FOREMAN);
            if (npc == null)
                return;

            if (!Dialog.isOpen()) {
                if (npc.interact(PAY))
                    Time.sleepUntil(Dialog::isOpen, 1500);
            } else {
                if (Dialog.process(0))
                    if (Time.sleepUntil(Dialog::isProcessing, 1500))
                        paid_foreman = true;
            }
        }
    }

    @Override
    public String toString() {
        return "Paying foreman";
    }
}

