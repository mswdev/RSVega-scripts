package sphiinx.script.private_script.zerker.revenants.mission.worker.impl;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.private_script.zerker.revenants.Main;
import sphiinx.script.private_script.zerker.revenants.mission.RevenantMission;

public class FightRevenant extends Worker<RevenantMission> {

    private final LootItem loot_item = new LootItem();

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving() && Movement.getDestinationDistance() > 10)
            return;

        if (Players.getLocal().getTargetIndex() != -1)
            return;

        final Npc revenant = Npcs.getNearest(Main.getParsedArgs().REVENANT_TYPE.getName());
        if (revenant != null) {
            if (revenant.click())
                Time.sleepUntil(() -> Players.getLocal().getTargetIndex() != -1, 1500);
        } else {
            if (Main.getParsedArgs().REVENANT_TYPE.getPosition().distance() > 15) {
                if (Movement.walkTo(Main.getParsedArgs().REVENANT_TYPE.getPosition()))
                    Time.sleepUntil(() -> Players.getLocal().isMoving(), 1500);
            } else {
                loot_item.work();
            }
        }
    }

    @Override
    public String toString() {
        return "Fighting revenant";
    }
}

