package org.script.free_script.spx_account_checker.mission.worker.impl;

import org.api.script.framework.worker.Worker;
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
import org.script.free_script.spx_account_checker.data.Vars;

import java.util.function.Predicate;

public class CheckAge extends Worker {

    private final int hansChatInterfaceId = 231;
    private final int hansChatInterfaceChildId = 4;
    private final Predicate<Npc> hansNpc = a -> a.getName().equals("Hans");
    private final Predicate<String> ageHans = a -> a.equals("Age");
    private final Position hansPosition = new Position(3218, 3228, 0);

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving() || Players.getLocal().getAnimation() != -1)
            return;

        final Npc hans = Npcs.getNearest(hansNpc);
        if (hans != null) {
            if (Dialog.isViewingChat()) {
                final InterfaceComponent hansChat = Interfaces.getComponent(hansChatInterfaceId, hansChatInterfaceChildId);
                if (hansChat == null)
                    return;

                final String[] unformattedText = hansChat.getText().split("arrived");
                final String formattedText = unformattedText[1].replaceAll("[^0-9]", "");
                Vars.get().generalData.putIfAbsent("creation_age", String.valueOf(Integer.parseInt(formattedText)));
                Vars.get().checkAge = false;
            } else {
                if (hans.interact(ageHans))
                    Time.sleepUntil(() -> Players.getLocal().isMoving() || Dialog.isViewingChat(), 1500);
            }
        } else {
            if (hansPosition.distance() > 35) {
                if (!Tabs.isOpen(Tab.MAGIC)) {
                    if (Tabs.open(Tab.MAGIC))
                        Time.sleepUntil(() -> Tabs.isOpen(Tab.MAGIC), 1500);
                } else if (Magic.cast(Spell.Modern.HOME_TELEPORT))
                    Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 1500);
            } else if (Movement.walkTo(hansPosition))
                Time.sleepUntil(() -> Npcs.getNearest(hansNpc) != null, 1500);
        }
    }

    @Override
    public String toString() {
        return "Checking age";
    }
}

