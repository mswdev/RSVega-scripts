package sphiinx.script.public_script.spx_air_orbs;

import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.listeners.SkillListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.runetek.event.types.SkillEvent;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.script.public_script.spx_tutorial_island.api.SPXStyle;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.SPXScript;
import sphiinx.script.public_script.spx_tutorial_island.api.game_util.Wilderness;
import sphiinx.script.public_script.spx_tutorial_island.api.game_util.pricechecking.PriceCheck;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.AirOrbRestockMission;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.MONEY_MAKING, name = "[SPX] Air Orbs", desc = "")
public class Main extends SPXScript implements RenderListener, SkillListener {

    private StopWatch stop_watch;
    private int orbs_charged;
    private int profit;
    private int uncharged_orb_price;
    private int charged_orb_price;
    private int cosmic_rune_price;

    @Override
    public void onStart() {
        super.onStart();
        stop_watch = StopWatch.start();
        uncharged_orb_price = PriceCheck.getRSPrice(567);
        charged_orb_price = PriceCheck.getRSPrice(573);
        cosmic_rune_price = PriceCheck.getRSPrice(564);
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new AirOrbLevelMission());
        missions.add(new AirOrbChargeMission(this));
        missions.add(new AirOrbRestockMission(this));
        return missions;
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        final Graphics G = renderEvent.getSource();
        G.drawString("[RUNTIME]: " + stop_watch.toElapsedString(), 10, 305);
        G.drawString("[ORBS]: " + orbs_charged + " (" + SPXStyle.formatNumber((3600000 / stop_watch.getElapsed().toMillis()) * orbs_charged) + "/hr)", 10, 320);
        G.drawString("[PROFIT]: " + profit + " (" + SPXStyle.formatNumber((3600000 / stop_watch.getElapsed().toMillis()) * profit) + "/hr)", 10, 335);
    }

    @Override
    public void notify(SkillEvent skillEvent) {
        if (skillEvent.getSource() != Skill.MAGIC)
            return;

        if (Wilderness.getWildernessLevel() != 7)
            return;

        orbs_charged++;
        profit = ((charged_orb_price - uncharged_orb_price) - (cosmic_rune_price * 3)) * orbs_charged;
    }
}

