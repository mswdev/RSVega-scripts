package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.WorldHopper;
import sphiinx.api.script.framework.worker.Worker;

public class HopWorld extends Worker {

    public boolean should_hop_world;

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        final int cache = Game.getClient().getCurrentWorld();
        if (WorldHopper.isOpen()) {
            if (WorldHopper.randomHopInP2p())
                if (Time.sleepUntil(() -> cache != Game.getClient().getCurrentWorld(), 10000))
                    should_hop_world = false;
        } else {
            if (WorldHopper.open())
                Time.sleepUntil(WorldHopper::isOpen, 1500);
        }
    }

    @Override
    public String toString() {
        return "Hopping worlds.";
    }
}

