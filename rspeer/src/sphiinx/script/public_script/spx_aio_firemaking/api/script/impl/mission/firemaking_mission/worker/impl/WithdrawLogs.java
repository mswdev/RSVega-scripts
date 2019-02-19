package sphiinx.script.public_script.spx_aio_firemaking.api.script.impl.mission.firemaking_mission.worker.impl;


import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.ui.Log;
import sphiinx.script.public_script.spx_aio_firemaking.api.script.framework.worker.Worker;
import sphiinx.script.public_script.spx_aio_firemaking.api.script.impl.worker.banking.OpenBankWorker;
import sphiinx.script.public_script.spx_aio_firemaking.api.game.skills.firemaking.FiremakingUtil;
import sphiinx.script.public_script.spx_aio_firemaking.Main;
import sphiinx.script.public_script.spx_aio_firemaking.api.script.impl.mission.firemaking_mission.FireMakingMission;

public class WithdrawLogs extends Worker {

    private final OpenBankWorker open_bank_worker = new OpenBankWorker(false);
    private final FireMakingMission mission;

    public WithdrawLogs(FireMakingMission mission) {
       this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        if (Bank.isOpen()) {
            if (Main.ARGS.set_progressive)
                Main.ARGS.log_type = FiremakingUtil.getAppropriateOwnedLogs();

            if (Main.ARGS.log_type != null && Bank.contains(Main.ARGS.log_type.getName())) {
                if (Bank.withdrawAll(Main.ARGS.log_type.getName()))
                    Time.sleepUntil(() -> Inventory.contains(Main.ARGS.log_type.getName()), 1500);
            } else {
                Log.severe("[FIREMAKING]: You do not have any usable logs in your bank.");
                mission.setShouldStop(true);
            }
        } else {
            open_bank_worker.work();
        }
    }

    @Override
    public String toString() {
        return "Withdrawing logs.";
    }
}

