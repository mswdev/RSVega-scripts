package sphiinx.script.private_script.zerker.revenants.mission.worker;

import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.local.Health;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.WorkerHandler;
import sphiinx.script.public_script.spx_tutorial_island.api.game_util.Wilderness;
import sphiinx.script.private_script.zerker.revenants.mission.RevenantMission;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.FightRevenant;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.LootItem;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.food.EatFood;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.food.WithdrawFood;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.travel_to_edgeville.TeleportToEdgeville;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.travel_to_edgeville.WithdrawAmuletOfGlory;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.travel_to_revenant.EnterRevenantCave;
import sphiinx.script.private_script.zerker.revenants.mission.worker.impl.travel_to_revenant.TeleportToLavaMaze;

import java.util.Arrays;

public class RevenantWorkerHandler extends WorkerHandler<RevenantMission> {

    private final TeleportToLavaMaze teleport_to_lava_maze;
    private final EnterRevenantCave enter_revenant_cave;
    private final FightRevenant fight_revenant;
    private final LootItem loot_item;
    private final WithdrawFood withdraw_food;
    private final EatFood eat_food;
    private final TeleportToEdgeville teleport_to_edgeville;
    private final WithdrawAmuletOfGlory withdraw_amulet_of_glory;

    public RevenantWorkerHandler(RevenantMission mission) {
        super(mission);
        teleport_to_lava_maze = new TeleportToLavaMaze();
        enter_revenant_cave = new EnterRevenantCave();
        fight_revenant = new FightRevenant();
        loot_item = new LootItem();
        withdraw_food = new WithdrawFood();
        eat_food = new EatFood();
        teleport_to_edgeville = new TeleportToEdgeville();
        withdraw_amulet_of_glory = new WithdrawAmuletOfGlory();
    }

    @Override
    public Worker<RevenantMission> decide() {
        if (!Equipment.contains(WithdrawAmuletOfGlory.AMULET_OF_GLORY_NAME))
            return withdraw_amulet_of_glory;

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

        return fight_revenant;
    }
}

