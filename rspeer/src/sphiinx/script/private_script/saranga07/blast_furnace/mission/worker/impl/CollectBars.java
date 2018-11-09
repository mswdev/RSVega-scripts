package sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Production;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.OpenBankWorker;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.BlastFurnaceMission;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.BlastFurnaceWorker;

import java.util.function.Predicate;

public class CollectBars extends BlastFurnaceWorker {

    public static final int COLLECT_BARS_VARP = 543;
    public static final int COLLECT_BARS_COOLED_SETTING = 768;
    static final Predicate<SceneObject> COLLECT_BARS = a -> a.getName().equals("Bar dispenser");
    private final OpenBankWorker open_bank;

    public CollectBars(BlastFurnaceMission mission) {
        super(mission);
        open_bank = new OpenBankWorker<>(false);
    }

    @Override
    public void work() {
        final Item bar = Inventory.getFirst("Steel bar");
        if (bar != null) {
            if (Bank.isOpen()) {
                if (Bank.depositAll("Steel bar"))
                    Time.sleepUntil(() -> Inventory.getFirst("Steel bar") == null, 1500);
            } else {
                open_bank.work();
            }
        } else if (Production.isOpen()) {
            if (Production.initiate(0))
                Time.sleepUntil(() -> Inventory.getFirst("Steel bar") != null, 1500);
            mission.is_smelting = false;
        } else {
            final SceneObject bar_dispenser = SceneObjects.getNearest(COLLECT_BARS);
            if (bar_dispenser == null)
                return;

            if (bar_dispenser.click())
                Time.sleepUntil(Production::isOpen, 1500);
        }
    }

    public boolean isDoneSmelting() {
        return Varps.get(COLLECT_BARS_VARP) > 0;
    }

    @Override
    public String toString() {
        return "Collecting smelted bars";
    }
}

