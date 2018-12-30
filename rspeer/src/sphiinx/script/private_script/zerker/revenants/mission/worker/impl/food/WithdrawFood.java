package sphiinx.script.private_script.zerker.revenants.mission.worker.impl.food;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;
import sphiinx.script.private_script.zerker.revenants.Main;
import sphiinx.script.private_script.zerker.revenants.mission.RevenantMission;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.ge.BuyItemWorker;

import java.util.function.Predicate;

public class WithdrawFood extends Worker {

    private final BuyItemWorker BUY_FOOD;
    public static Predicate<Item> FOOD = a -> a.getName().equals("Shark");
    private static final WithdrawWorker WITHDRAW_FOOD = new WithdrawWorker(FOOD, 24);
    private final RevenantMission mission;

    public WithdrawFood(RevenantMission mission) {
        this.mission = mission;
        BUY_FOOD = new BuyItemWorker(mission, 385, 100);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (!BUY_FOOD.isWaitingForItem())
            WITHDRAW_FOOD.work();

        if (!Main.ARGS.GE_AUTO_SELL) {
            mission.can_end = WITHDRAW_FOOD.itemNotFound();
        } else {
            BUY_FOOD.work();
        }
    }

    @Override
    public String toString() {
        return "Withdrawing food.";
    }
}

