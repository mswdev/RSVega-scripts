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
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.AirOrbLevelWorker;

import java.util.function.Predicate;

public class FightChicken extends AirOrbLevelWorker {

    private final int DEFENSIVE_INTER_MASTER = 593;
    private final int DEFENSIVE_INTER_COMP = 20;
    private final int WIND_STRIKE_INTER_MASTER = 201;
    private final int WIND_STRIKE_INTER_COMP = 1;
    public static final Predicate<Npc> CHICKEN = a -> a.getName().equals("Chicken") && a.getTargetIndex() == -1;

    public FightChicken(AirOrbLevelMission mission) {
        super(mission);
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
            fightChicken();
        }
    }

    private void fightChicken() {
        final Npc NPC = Npcs.getNearest(CHICKEN);
        if (NPC == null)
            return;

        if (NPC.isPositionInteractable()) {
            if (NPC.click())
                Time.sleepUntil(() -> Players.getLocal().getTargetIndex() != -1, 1500);
        } else {
            if (Movement.walkTo(NPC))
                Time.sleepUntil(NPC::isPositionInteractable, 1500);
        }
    }

    private void setWindStrikeAutoCast() {
        if (isShowingDefensiveAutocast()) {
            selectWindStrike();
        } else {
            openDefensiveAutocast();
        }
    }

    private boolean isShowingDefensiveAutocast() {
        return Interfaces.getComponent(WIND_STRIKE_INTER_MASTER, WIND_STRIKE_INTER_COMP) != null;
    }

    private boolean openDefensiveAutocast() {
        final InterfaceComponent DEFENSIVE_AUTOCAST = Interfaces.getComponent(DEFENSIVE_INTER_MASTER, DEFENSIVE_INTER_COMP);
        if (DEFENSIVE_AUTOCAST == null)
            return false;
        return DEFENSIVE_AUTOCAST.click();
    }

    private boolean selectWindStrike() {
        final InterfaceComponent WIND_STRIKE = Interfaces.getComponent(WIND_STRIKE_INTER_MASTER, WIND_STRIKE_INTER_COMP, WIND_STRIKE_INTER_COMP);
        if (WIND_STRIKE == null)
            return false;

        return WIND_STRIKE.click();
    }

    @Override
    public String toString() {
        return "Fighting chicken";
    }
}

