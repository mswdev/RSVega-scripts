package sphiinx.script.public_script.spx_aio_firemaking.mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.WithdrawItemWorker;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_aio_firemaking.mission.FireMakingMission;

import java.util.function.Predicate;

public class WithdrawTinderBox extends Worker<FireMakingMission> {

    public static final Predicate<Item> TINDERBOX = a -> a.getName().equals("Tinderbox");
    private final WithdrawItemWorker withdraw_tinder_box;

    public WithdrawTinderBox(FireMakingMission mission) {
        super(mission);
        withdraw_tinder_box = new WithdrawItemWorker<>(TINDERBOX);
    }

    @Override
    public void work() {
        withdraw_tinder_box.work();
    }

    @Override
    public String toString() {
        return "Withdrawing Tinderbox";
    }
}

