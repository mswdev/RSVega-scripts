package sphiinx.script.private_script.zerker.revenants.mission.worker.impl.refill_weapon;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;
import sphiinx.script.private_script.zerker.revenants.Main;
import sphiinx.script.private_script.zerker.revenants.mission.RevenantMission;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.ge.BuyItemWorker;

import java.util.function.Predicate;

public class WithdrawRevenantEther extends Worker {

    private final BuyItemWorker BUY_REVENANT_ETHER;
    public static Predicate<Item> REVENANT_ETHER = a -> a.getName().equals("Revenant ether");
    private static final WithdrawWorker WITHDRAW_REVENANT_ETHER = new WithdrawWorker(REVENANT_ETHER, 0);
    private final RevenantMission mission;

    public WithdrawRevenantEther(RevenantMission mission) {
        this.mission = mission;
        BUY_REVENANT_ETHER = new BuyItemWorker(mission, 21820, 1000);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (!BUY_REVENANT_ETHER.isWaitingForItem())
            WITHDRAW_REVENANT_ETHER.work();

        if (!Main.ARGS.GE_AUTO_SELL) {
            mission.can_end = WITHDRAW_REVENANT_ETHER.itemNotFound();
        } else {
            BUY_REVENANT_ETHER.work();
        }
    }

    @Override
    public String toString() {
        return "Withdrawing Revenant ether";
    }
}

