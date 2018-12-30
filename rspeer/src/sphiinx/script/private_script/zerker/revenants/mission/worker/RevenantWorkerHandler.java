package sphiinx.script.private_script.zerker.revenants.mission.worker;

import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.local.Health;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.framework.worker.WorkerHandler;
import sphiinx.api.game.Wilderness;
import sphiinx.script.private_script.zerker.revenants.Main;
import sphiinx.script.private_script.zerker.revenants.mission.RevenantMission;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.FightRevenant;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.LootItem;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.food.EatFood;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.food.WithdrawFood;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.refill_weapon.*;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.travel_to_edgeville.TeleportToEdgeville;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.travel_to_edgeville.WithdrawAmuletOfGlory;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.travel_to_revenant.EnterRevenantCave;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.travel_to_revenant.TeleportToLavaMaze;

import java.util.Arrays;

public class RevenantWorkerHandler extends WorkerHandler {

    private final TeleportToLavaMaze teleport_to_lava_maze;
    private final EnterRevenantCave enter_revenant_cave;
    private final FightRevenant fight_revenant;
    private final LootItem loot_item;
    private final WithdrawDarts withdraw_darts;
    private final WithdrawZulrahScales withdraw_zulrah_scales;
    private final RefillToxicBlowpipe refill_toxic_blowpipe;
    private final WithdrawRevenantEther withdraw_revenant_ether;
    private final RefillCrawsBow refill_craws_bow;
    private final WithdrawFood withdraw_food;
    private final EatFood eat_food;
    private final TeleportToEdgeville teleport_to_edgeville;
    private final WithdrawAmuletOfGlory withdraw_amulet_of_glory;

    public RevenantWorkerHandler(RevenantMission mission) {
        teleport_to_lava_maze = new TeleportToLavaMaze(mission);
        enter_revenant_cave = new EnterRevenantCave();
        fight_revenant = new FightRevenant();
        loot_item = new LootItem();
        withdraw_darts = new WithdrawDarts(mission);
        withdraw_zulrah_scales = new WithdrawZulrahScales(mission);
        refill_toxic_blowpipe = new RefillToxicBlowpipe();
        withdraw_revenant_ether = new WithdrawRevenantEther(mission);
        refill_craws_bow = new RefillCrawsBow();
        withdraw_food = new WithdrawFood(mission);
        eat_food = new EatFood();
        teleport_to_edgeville = new TeleportToEdgeville();
        withdraw_amulet_of_glory = new WithdrawAmuletOfGlory(mission);
    }

    @Override
    public Worker decide() {
        if (!Equipment.contains(WithdrawAmuletOfGlory.AMULET_OF_GLORY_NAME))
            return withdraw_amulet_of_glory;

        if (Main.ARGS.refill_toxic_blowpipe) {
            if (Wilderness.getWildernessLevel() <= 0) {
                if (Inventory.getCount(WithdrawDarts.DARTS) <= 0)
                    return withdraw_darts;

                if (Inventory.getCount(WithdrawZulrahScales.ZULRAH_SCALES) <= 0)
                    return withdraw_zulrah_scales;

                return refill_toxic_blowpipe;
            }

            return teleport_to_edgeville;
        }

        if (Main.ARGS.refill_craws_bow) {
            if (Wilderness.getWildernessLevel() <= 0) {
                if (Inventory.getCount(WithdrawRevenantEther.REVENANT_ETHER) <= 0)
                    return withdraw_revenant_ether;

                return refill_craws_bow;
            }

            return teleport_to_edgeville;
        }

        if (!Inventory.contains(WithdrawFood.FOOD)) {
            if (Wilderness.getWildernessLevel() <= 0)
                return withdraw_food;

            return teleport_to_edgeville;
        }

        if (Health.getPercent() < 75)
            return eat_food;

        final Player attacking_player = Arrays.stream(Players.getLoaded()).filter(a -> a.getTarget() != null && a.getTarget().getName().equals(Players.getLocal().getName())).findFirst().orElse(null);
        if (attacking_player != null)
            return teleport_to_edgeville;

        if (Wilderness.getWildernessLevel() <= 0)
            return teleport_to_lava_maze;

        if (!EnterRevenantCave.REVENANT_AREA.contains(Players.getLocal()))
            return enter_revenant_cave;

        if (Players.getLocal().getTargetIndex() == 0)
            return loot_item;

        if (loot_item.needsBank()) {
            if (Wilderness.getWildernessLevel() <= 0) {
                if (Bank.isOpen()) {
                    if (Bank.depositInventory())
                        Time.sleepUntil(() -> Inventory.getCount() <= 0, 1500);
                    loot_item.setNeedsBank(false);
                } else {
                    if (Bank.open())
                        Time.sleepUntil(Bank::isOpen, 1500);
                }
            }
            return teleport_to_edgeville;
        }

        return fight_revenant;
    }
}

