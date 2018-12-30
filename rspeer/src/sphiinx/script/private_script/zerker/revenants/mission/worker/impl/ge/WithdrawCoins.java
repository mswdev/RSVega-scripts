package sphiinx.script.private_script.zerker.revenants.mission.worker.impl.ge;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;
import sphiinx.script.private_script.zerker.revenants.Main;
import sphiinx.script.private_script.zerker.revenants.mission.RevenantMission;

import java.util.function.Predicate;

public class WithdrawCoins extends Worker {

    public static final Predicate<Item> COINS = a -> a.getName().equals("Coins");
    private static final SellItemWorker SELL_ITEMS = new SellItemWorker("Coal");
    private final WithdrawWorker WITHDRAW_COINS = new WithdrawWorker(COINS, Bank.WithdrawMode.ITEM, 0);
    private final RevenantMission mission;

    public WithdrawCoins(RevenantMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (!SELL_ITEMS.isWaitingForItem())
            WITHDRAW_COINS.work();

        if (!Main.ARGS.GE_AUTO_SELL) {
            mission.can_end = WITHDRAW_COINS.itemNotFound();
        } else {
            SELL_ITEMS.work();
        }
    }

    @Override
    public String toString() {
        return "Withdrawing coins.";
    }
}

