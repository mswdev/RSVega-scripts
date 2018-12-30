package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Magic;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.api.script.framework.worker.Worker;

import java.util.function.Predicate;

public class FightSeagulls extends Worker {

    public static final Predicate<Npc> SEAGULL = a -> a.getName().equals("Seagull") && a.getTargetIndex() == -1;
    private final int defensive_inter_master = 593;
    private final int defensive_inter_comp = 20;
    private final int wind_strike_inter_master = 201;
    private final int wind_strike_inter_comp = 1;

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        if (Players.getLocal().getTargetIndex() != -1)
            return;

        if (Equipment.contains("Staff of air") && !Magic.isAutoCasting()) {
            setWindStrikeAutoCast();
        } else {
            fightSeagull();
        }
    }

    private void fightSeagull() {
        final Npc npc = Npcs.getNearest(SEAGULL);
        if (npc == null)
            return;

        if (npc.isPositionInteractable()) {
            if (npc.click())
                Time.sleepUntil(() -> Players.getLocal().getTargetIndex() != -1, 1500);
        } else {
            if (Movement.walkTo(npc))
                Time.sleepUntil(npc::isPositionInteractable, 1500);
        }
    }

    private void setWindStrikeAutoCast() {
        if (isShowingDefensiveAutocast()) {
            if (selectWindStrike())
                Time.sleepUntil(Magic::isAutoCasting, 1500);
        } else {
            if (openDefensiveAutocast())
                Time.sleepUntil(this::isShowingDefensiveAutocast, 1500);
        }
    }

    private boolean isShowingDefensiveAutocast() {
        return Interfaces.getComponent(wind_strike_inter_master, wind_strike_inter_comp) != null;
    }

    private boolean openDefensiveAutocast() {
        final InterfaceComponent defensive_autocast = Interfaces.getComponent(defensive_inter_master, defensive_inter_comp);
        if (defensive_autocast == null)
            return false;
        return defensive_autocast.click();
    }

    private boolean selectWindStrike() {
        final InterfaceComponent wind_strike = Interfaces.getComponent(wind_strike_inter_master, wind_strike_inter_comp, wind_strike_inter_comp);
        if (wind_strike == null)
            return false;

        return wind_strike.click();
    }

    @Override
    public String toString() {
        return "Fighting Seagull.";
    }
}

