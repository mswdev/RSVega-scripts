package sphiinx.script.public_script.spx_tutorial_island.mission;

import org.rspeer.runetek.adapter.Interactable;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.GameAccount;
import org.rspeer.ui.Log;
import sphiinx.api.SPXScript;
import sphiinx.api.framework.goal.GoalList;
import sphiinx.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.data.Stages;
import sphiinx.script.public_script.spx_tutorial_island.data.TutorialStage;
import sphiinx.script.public_script.spx_tutorial_island.data.Vars;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TIMission extends Mission {

    public final Predicate<String> DEFAULT_ACTION = a -> true;
    public final SPXScript SPX_SCRIPT;
    public final Iterator<Map.Entry<String, String>> ITERATOR;
    public final LinkedHashMap<String, String> ACCOUNT_DETAILS;

    private final Stages STAGES;

    private TutorialStage STATE;

    public TIMission(SPXScript spx_script, LinkedHashMap<String, String> account_details) {
        this.SPX_SCRIPT = spx_script;
        this.ACCOUNT_DETAILS = account_details;
        this.STAGES = new Stages(this);
        this.ITERATOR = ACCOUNT_DETAILS.entrySet().iterator();
    }


    @Override
    public String getMissionName() {
        return "SPX Tutorial Island";
    }

    @Override
    public String getWorkerName() {
        return null;
    }

    @Override
    public String getWorkerString() {
        return STATE.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return false;
    }

    @Override
    public boolean canEnd() {
        //todo Clean this up
        if (Vars.get().load_accounts_from_list && !ITERATOR.hasNext() && !Game.isLoggedIn() && STAGES.STAGES[STAGES.STAGES.length - 1].isValid())
            return true;


        return !Vars.get().load_accounts_from_list && !Game.isLoggedIn() && STAGES.STAGES[STAGES.STAGES.length - 1].isValid();
    }

    @Override
    public GoalList getGoals() {
        return null;
    }

    @Override
    public int execute() {
        if (Interfaces.isDialogProcessing())
            return 150;

        if (Interfaces.canContinue()) {
            Game.getClient().fireScriptEvent(299, 1, 1); //TODO Remove once Interfaces.processContinue() is fixed.
            if (Interfaces.processContinue())
                Time.sleepUntil(Interfaces::isDialogProcessing, 1500);
        } else {
            STATE = STAGES.getValid();
            if (STATE == null)
                return 150;

            //Todo Replace once I write a paint framework.
            //System.out.println(STATE.toString());
            STATE.execute();
        }

        return 150;
    }

    @Override
    public void onMissionStart() {
        setNextAccount();
    }

    @Override
    public void onMissionEnd() {

    }

    @Override
    public String[] getMissionPaint() {
        return new String[0];
    }

    @Override
    public void resetPaint() {

    }

    /**
     * Gets the tutorial island hint npc.
     *
     * @return The tutorial island hint npc; null otherwise.
     */
    public Npc getHintNPC() {
        return Npcs.getAt(Game.getClient().getHintArrowNpcIndex());
    }

    /**
     * Gets an interactable scene object at the hint arrow position.
     *
     * @return An interactable scene object at the hint arrow; null otherwise.
     */
    public SceneObject getHintSceneObject() {
        final Position HINT_POSITION = new Position(Game.getClient().getHintArrowX(), Game.getClient().getHintArrowY(), Players.getLocal().getFloorLevel());
        return Stream.of(SceneObjects.getAt(HINT_POSITION))
                .filter(a -> a.containsAction(DEFAULT_ACTION))
                .findFirst()
                .orElse(null);
    }

    /**
     * Interacts with the tutorial island hint.
     *
     * @return True if the interaction was successful; false otherwise.
     */
    public boolean interactWithHint(Interactable interactable) {
        if (interactable == null)
            return false;

        return interactable.interact(DEFAULT_ACTION);
    }

    /**
     * Sets the next account to use.
     */
    public void setNextAccount() {
        if (!Vars.get().load_accounts_from_list)
            return;

        final Map.Entry<String, String> ENTRY = ITERATOR.next();
        Log.fine("[ACCOUNT]: [Username: " + ENTRY.getKey() + " | Password: " + ENTRY.getValue() + "]");
        Vars.get().TEMP.add(ENTRY.getKey() + ":" + ENTRY.getValue()); //TODO Remove this soon, only using it to show accs that completed tutorial island.
        SPX_SCRIPT.setAccount(new GameAccount(ENTRY.getKey(), ENTRY.getValue()));
    }
}

