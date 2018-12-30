package sphiinx.script.private_script.zerker.revenants.mission.worker.impl.refill_weapon;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;
import sphiinx.script.private_script.zerker.revenants.Main;
import sphiinx.script.private_script.zerker.revenants.mission.RevenantMission;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.ge.BuyItemWorker;

import java.util.function.Predicate;

public class WithdrawZulrahScales extends Worker {

    private final BuyItemWorker BUY_ZULRAH_SCALES;
    public static Predicate<Item> ZULRAH_SCALES = a -> a.getName().equals("Zulrah's scales");
    private static final WithdrawWorker WITHDRAW_ZULRAH_SCALES = new WithdrawWorker(ZULRAH_SCALES, 0);
    private final RevenantMission mission;

    public WithdrawZulrahScales(RevenantMission mission) {
        this.mission = mission;
        BUY_ZULRAH_SCALES = new BuyItemWorker(mission, 12934, 1000);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (!BUY_ZULRAH_SCALES.isWaitingForItem())
            WITHDRAW_ZULRAH_SCALES.work();

        if (!Main.ARGS.GE_AUTO_SELL) {
            mission.can_end = WITHDRAW_ZULRAH_SCALES.itemNotFound();
        } else {
            BUY_ZULRAH_SCALES.work();
        }
    }

    @Override
    public String toString() {
        return "Withdrawing Zulrah's scales";
    }
}

