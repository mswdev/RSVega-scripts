package sphiinx.script.public_script.spx_account_checker.mission.worker.impl;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_account_checker.data.Vars;
import sphiinx.script.public_script.spx_account_checker.mission.AccountCheckerMission;
import sphiinx.script.public_script.spx_account_checker.mission.worker.AccountCheckerWorker;

import java.util.function.Predicate;

public class CheckAge extends AccountCheckerWorker {

    private final int HANS_CHAT_INTERFACE_ID = 231;
    private final int HANS_CHAT_INTERFACE_CHILD_ID = 4;
    private final Predicate<Npc> HANS_NPC = a -> a.getName().equals("Hans");
    private final Predicate<String> AGE_HANS = a -> a.equals("Age");
    private final Position HANS_POSITION = new Position(3218, 3228, 0);

    public CheckAge(AccountCheckerMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving())
            return;

        final Npc HANS = Npcs.getNearest(HANS_NPC);
        if (HANS != null) {
            if (Dialog.isViewingChat()) {
                final InterfaceComponent HANS_CHAT = Interfaces.getComponent(HANS_CHAT_INTERFACE_ID, HANS_CHAT_INTERFACE_CHILD_ID);
                if (HANS_CHAT == null)
                    return;

                final String[] UNFORMATTED_TEXT = HANS_CHAT.getText().split("arrived ");
                final String[] FORMATTED_TEXT = UNFORMATTED_TEXT[1].split(" days");
                Vars.get().ACCOUNT_DATA.putIfAbsent("account_age", String.valueOf(Integer.parseInt(FORMATTED_TEXT[0])));
                Vars.get().check_age = false;
                Vars.get().can_logout = true;
            } else {
                if (HANS.interact(AGE_HANS))
                    Time.sleepUntil(() -> Players.getLocal().isMoving() || Dialog.isViewingChat(), 1500);
            }
        } else {
            if (Movement.walkTo(HANS_POSITION))
                Time.sleepUntil(() -> Npcs.getNearest(HANS_NPC) != null, 1500);
        }
    }

    @Override
    public String toString() {
        return "Checking age";
    }
}

