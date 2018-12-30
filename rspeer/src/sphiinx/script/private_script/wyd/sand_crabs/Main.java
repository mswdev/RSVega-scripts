package sphiinx.script.private_script.wyd.sand_crabs;

import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.scene.Projection;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.listeners.SkillListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.runetek.event.types.SkillEvent;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.events.LoginScreen;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;
import sphiinx.script.private_script.wyd.sand_crabs.data.Vars;
import sphiinx.script.private_script.wyd.sand_crabs.tasks.*;

import java.awt.*;

@ScriptMeta(developer = "", category = ScriptCategory.COMBAT, name = "WYD Sand Crabs", desc = "")
public class Main extends TaskScript implements SkillListener, RenderListener {

    private final int START_ATTACK_XP = Skills.getExperience(Skill.ATTACK);
    private final int START_STRENGTH_XP = Skills.getExperience(Skill.STRENGTH);
    private final int START_DEFENCE_XP = Skills.getExperience(Skill.DEFENCE);
    private final int START_HITPOINTS_XP = Skills.getExperience(Skill.HITPOINTS);
    private StopWatch STOP_WATCH;
    private int ATTACK_XP = Skills.getExperience(Skill.ATTACK);
    private int STRENGTH_XP = Skills.getExperience(Skill.STRENGTH);
    private int DEFENCE_XP = Skills.getExperience(Skill.DEFENCE);
    private int HITPOINTS_XP = Skills.getExperience(Skill.HITPOINTS);

    private int ATTACK_LVL = Skills.getLevel(Skill.ATTACK);
    private int STRENGTH_LVL = Skills.getLevel(Skill.STRENGTH);
    private int DEFENCE_LVL = Skills.getLevel(Skill.DEFENCE);
    private int HITPOINTS_LVL = Skills.getLevel(Skill.HITPOINTS);

    @Override
    public void onStart() {
        Log.fine("WYD Sand Crabs has started");
        removeBlockingEvent(LoginScreen.class);
        addBlockingEvent(new LoginBlock(this));
        STOP_WATCH = StopWatch.start();
        StopWatch.start();
        submit(new FixAutoRetaliate(), new WalkToFightTile(), new ListenForCrash(), new ListenForReset(), new HopWorld(), new Reset());
    }

    @Override
    public int loop() {
        return super.loop();
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        final Graphics G = renderEvent.getSource();
        G.setColor(Color.WHITE);
        G.setFont(new Font("Arial", Font.BOLD, 12));
        G.drawString("[ATTACK]: " + ATTACK_LVL + " | " + formatNumber((3600000.0 / STOP_WATCH.getElapsed().toMillis()) * (ATTACK_XP - START_ATTACK_XP)) + " xp/hr", 10, 274);
        G.drawString("[STRENGTH]: " + STRENGTH_LVL + " | " + formatNumber((3600000.0 / STOP_WATCH.getElapsed().toMillis()) * (STRENGTH_XP - START_STRENGTH_XP)) + " xp/hr", 10, 286);
        G.drawString("[DEFENCE]: " + DEFENCE_LVL + " | " + formatNumber((3600000.0 / STOP_WATCH.getElapsed().toMillis()) * (DEFENCE_XP - START_DEFENCE_XP)) + " xp/hr", 10, 298);
        G.drawString("[HITPOINTS]: " + HITPOINTS_LVL + " | " + formatNumber((3600000.0 / STOP_WATCH.getElapsed().toMillis()) * (HITPOINTS_XP - START_HITPOINTS_XP)) + " xp/hr", 10, 310);
        G.drawString("[RUNTIME]: " + STOP_WATCH.toElapsedString(), 10, 322);
        G.drawString("[STATUS]: " + (getCurrent() != null ? getCurrent().toString() : "N/A"), 10, 335);

        G.setColor(Color.RED);
        if (Vars.get().FIGHT_TILE != null) {
            G.drawPolygon(Projection.getTileShape(Vars.get().FIGHT_TILE));
        }
    }

    private String formatNumber(double num) {
        if (num < 1000.0)
            return Integer.toString((int) num);
        else if (Math.round(num) / 10000.0 < 100.0)
            return String.format("%.1fk", Math.round(num) / 1000.0);
        else
            return String.format("%.1fm", Math.round(num) / 1000000.0);
    }

    @Override
    public void notify(SkillEvent skillEvent) {
        if (skillEvent.getType() == SkillEvent.TYPE_EXPERIENCE) {
            ATTACK_XP = Skills.getExperience(Skill.ATTACK);
            STRENGTH_XP = Skills.getExperience(Skill.STRENGTH);
            DEFENCE_XP = Skills.getExperience(Skill.DEFENCE);
            HITPOINTS_XP = Skills.getExperience(Skill.HITPOINTS);
        }

        if (skillEvent.getType() == SkillEvent.TYPE_LEVEL) {
            ATTACK_LVL = Skills.getLevel(Skill.ATTACK);
            STRENGTH_LVL = Skills.getLevel(Skill.STRENGTH);
            DEFENCE_LVL = Skills.getLevel(Skill.DEFENCE);
            HITPOINTS_LVL = Skills.getLevel(Skill.HITPOINTS);
        }
    }
}

