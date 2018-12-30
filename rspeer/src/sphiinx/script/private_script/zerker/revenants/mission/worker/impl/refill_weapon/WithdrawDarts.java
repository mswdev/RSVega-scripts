package sphiinx.script.private_script.zerker.revenants.mission.worker.impl.refill_weapon;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;
import sphiinx.script.private_script.zerker.revenants.Main;
import sphiinx.script.private_script.zerker.revenants.mission.RevenantMission;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.ge.BuyItemWorker;

import java.util.function.Predicate;

public class WithdrawDarts extends Worker {

    private final BuyItemWorker BUY_DARTS;
    public static Predicate<Item> DARTS = a -> a.getName().contains("dart");
    private static final WithdrawWorker WITHDRAW_DARTS = new WithdrawWorker(DARTS, 0);
    private final RevenantMission mission;

    public WithdrawDarts(RevenantMission mission) {
        this.mission = mission;
        BUY_DARTS = new BuyItemWorker(mission, 809, 1000);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (!BUY_DARTS.isWaitingForItem())
            WITHDRAW_DARTS.work();

        if (!Main.ARGS.GE_AUTO_SELL) {
            mission.can_end = WITHDRAW_DARTS.itemNotFound();
        } else {
            BUY_DARTS.work();
        }
    }

    @Override
    public String toString() {
        return "Withdrawing darts";
    }
}

