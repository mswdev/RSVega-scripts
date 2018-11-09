package sphiinx.script.public_script.spx_account_checker.mission.worker.impl;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Magic;
import org.rspeer.runetek.api.component.tab.Spell;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_account_checker.data.Vars;
import sphiinx.script.public_script.spx_account_checker.mission.AccountCheckerMission;
import sphiinx.script.public_script.spx_account_checker.mission.worker.AccountCheckerWorker;

import java.util.function.Predicate;

public class CheckAge extends AccountCheckerWorker {

    private final int hans_chat_interface_id = 231;
    private final int hans_chat_interface_child_id = 4;
    private final Predicate<Npc> hans_npc = a -> a.getName().equals("Hans");
    private final Predicate<String> age_hans = a -> a.equals("Age");
    private final Position hans_position = new Position(3218, 3228, 0);

    public CheckAge(AccountCheckerMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving() || Players.getLocal().getAnimation() != -1)
            return;

        final Npc hans = Npcs.getNearest(hans_npc);
        if (hans != null) {
            if (Dialog.isViewingChat()) {
                final InterfaceComponent hans_chat = Interfaces.getComponent(hans_chat_interface_id, hans_chat_interface_child_id);
                if (hans_chat == null)
                    return;

                final String[] unformatted_text = hans_chat.getText().split("arrived");
                final String formatted_text = unformatted_text[1].replaceAll("[^0-9]", "");
                Vars.get().general_data.putIfAbsent("creation_age", String.valueOf(Integer.parseInt(formatted_text)));
                Vars.get().check_age = false;
            } else {
                if (hans.interact(age_hans))
                    Time.sleepUntil(() -> Players.getLocal().isMoving() || Dialog.isViewingChat(), 1500);
            }
        } else {
            if (hans_position.distance() > 35) {
                if (!Tabs.isOpen(Tab.MAGIC)) {
                    if (Tabs.open(Tab.MAGIC))
                        Time.sleepUntil(() -> Tabs.isOpen(Tab.MAGIC), 1500);
                } else if (Magic.cast(Spell.Modern.HOME_TELEPORT))
                    Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 1500);
            } else if (Movement.walkTo(hans_position))
                Time.sleepUntil(() -> Npcs.getNearest(hans_npc) != null, 1500);
        }
    }

    @Override
    public String toString() {
        return "Checking age";
    }
}

