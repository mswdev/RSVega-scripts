package sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.api.script.framework.worker.Worker;

public class OpenGrandExchange extends Worker {


    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        final Npc npc = Npcs.getNearest("Grand Exchange Clerk");
        if (npc != null) {
            if (npc.interact("Exchange"))
                Time.sleepUntil(GrandExchange::isOpen, 1500);
        } else {
            if (Players.getLocal().isMoving() && Movement.getDestinationDistance() > 10)
                return;

            if (Movement.walkTo(BankLocation.GRAND_EXCHANGE.getPosition()))
                Time.sleepUntil(() -> BankLocation.GRAND_EXCHANGE.getPosition().distance() <= 10, 1500);
        }
    }

    @Override
    public String toString() {
        return "Walking to Grand Exchange.";
    }
}

