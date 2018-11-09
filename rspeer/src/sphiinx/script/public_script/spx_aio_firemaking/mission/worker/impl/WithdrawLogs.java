package sphiinx.script.public_script.spx_aio_firemaking.mission.worker.impl;


import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.ui.Log;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.OpenBankWorker;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.game_util.skills.firemaking.FiremakingUtil;
import sphiinx.script.public_script.spx_aio_firemaking.Main;
import sphiinx.script.public_script.spx_aio_firemaking.mission.FireMakingMission;

public class WithdrawLogs extends Worker<FireMakingMission> {

    private final OpenBankWorker open_bank;

    public WithdrawLogs(FireMakingMission mission) {
        super(mission);
        open_bank = new OpenBankWorker<>(false);
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        if (Bank.isOpen()) {
            if (Main.ARGS.set_progressive)
                Main.ARGS.log_type = FiremakingUtil.getBestUsableLog(false, true);

            if (Main.ARGS.log_type != null && Bank.contains(Main.ARGS.log_type.getName())) {
                if (Bank.withdrawAll(Main.ARGS.log_type.getName()))
                    Time.sleepUntil(() -> Inventory.contains(Main.ARGS.log_type.getName()), 1500);
            } else {
                Log.severe("[FIREMAKING]: You do not have any usable logs in your bank.");
                mission.setShouldStop(true);
            }
        } else {
            open_bank.work();
        }
    }

    @Override
    public String toString() {
        return "Withdrawing logs";
    }
}

