package sphiinx.script.public_script.spx_air_orbs;

import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.listeners.SkillListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.runetek.event.types.SkillEvent;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.api.SPXScript;
import sphiinx.api.framework.mission.Mission;
import sphiinx.api.framework.ui.javafx.FXGUI;
import sphiinx.api.game.pricechecking.PriceChecking;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.MONEY_MAKING, name = "[SPX] Air Orbs", desc = "")
public class Main extends SPXScript implements RenderListener, SkillListener {

    private StopWatch STOP_WATCH;
    private int orbs_charged;
    private int profit;

    private int uncharged_orb_price;
    private int charged_orb_price;
    private int cosmic_rune_price;

    @Override
    public void onStart() {
        super.onStart();
        STOP_WATCH = StopWatch.start();
        uncharged_orb_price = PriceChecking.getRSPrice(567);
        charged_orb_price = PriceChecking.getRSPrice(573);
        cosmic_rune_price = PriceChecking.getRSPrice(564);
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        LinkedList<Mission> MISSIONS = new LinkedList<>();
        MISSIONS.add(new AirOrbLevelMission());
        MISSIONS.add(new AirOrbChargeMission());
        return MISSIONS;
    }

    @Override
    public FXGUI getFXGUI() {
        return null;
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        final Graphics G = renderEvent.getSource();

        rainbowColor(G);
        G.drawString("[RUNTIME]: " + STOP_WATCH.toElapsedString(), 10, 305);
        G.drawString("[ORBS]: " + orbs_charged + " (" + Long.toString((int) (3600000.0 / STOP_WATCH.getElapsed().toMillis()) * orbs_charged) + "/hr)", 10, 320);
        G.drawString("[PROFIT]: " + profit + " (" + formatNumber((3600000.0 / STOP_WATCH.getElapsed().toMillis()) * profit) + "/hr)", 10, 335);
    }

    public void rainbowColor(Graphics G) {
        int ran = Random.nextInt(1, 7);
        switch (ran) {
            case 1:
                G.setColor(Color.RED);
                break;
            case 2:
                G.setColor(Color.ORANGE);
                break;
            case 3:
                G.setColor(Color.YELLOW);
                break;
            case 4:
                G.setColor(Color.GREEN);
                break;
            case 5:
                G.setColor(Color.CYAN);
                break;
            case 6:
                G.setColor(Color.BLUE);
                break;
            case 7:
                G.setColor(Color.MAGENTA);
                break;
        }
    }

    @Override
    public void notify(SkillEvent skillEvent) {
        if (skillEvent.getType() == SkillEvent.TYPE_EXPERIENCE) {
            orbs_charged++;
            profit = ((charged_orb_price - uncharged_orb_price) - (cosmic_rune_price * 3)) * orbs_charged;
        }
    }

    /**
     * Formats a number by replacing zeros with 'k' or 'm' dependent on the value of the number.
     *
     * @param num The number to format.
     * @return A String of the formatted number.
     */
    public static String formatNumber(double num) {
        if (num < 1000.0)
            return Integer.toString((int) num);
        else if (Math.round(num) / 10000.0 < 100.0)
            return String.format("%.1fk", Math.round(num) / 1000.0);
        else
            return String.format("%.1fm", Math.round(num) / 1000000.0);
    }
}

