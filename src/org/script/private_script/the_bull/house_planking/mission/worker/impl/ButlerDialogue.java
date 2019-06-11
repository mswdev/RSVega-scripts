package org.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.worker.DialogueWorker;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.EnterInput;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.ui.Log;
import org.script.private_script.the_bull.house_planking.mission.HousePlankingMission;

public class ButlerDialogue extends Worker {


    private static final DialogueWorker BUTLER_DIALOGUE = new DialogueWorker(a -> a.contains("Sawmill") || a.equals("Yes") || a.equals("Okay, here's 10,000 coins.") || a.contains("Take to sawmill"));
    private HousePlankingMission mission;

    public ButlerDialogue(HousePlankingMission mission) {
        this.mission = mission;
    }

    @Override
    public void work() {
        final Npc servant = Npcs.getNearest("Demon butler");
        if (servant == null)
            return;

        if (isServantStuck(servant)) {
            Log.severe("The servant is stuck, attempting to fix positioning.");
            Log.fine("[Player position]: " + Players.getLocal().getPosition().toString() + " | [Servant position]: " + servant.getPosition().toString());
            Movement.walkTo(new Position(Players.getLocal().getX() - 1, Players.getLocal().getY()));
            return;
        }

        if (!Dialog.isOpen()) {
            callServant();
            Log.severe("CALLING BUTLER");
        }

        if (Interfaces.getFirst(a -> a.getText().contains("Go to the sawmill...")) != null)
            if (Inventory.use(a -> a.getId() == mission.getLogType().getItemId(), servant))
                Time.sleepUntil(Dialog::isOpen, 1500);

        if (EnterInput.isOpen())
            EnterInput.initiate(24);

        BUTLER_DIALOGUE.work();
    }

    private boolean isServantStuck(Npc servant) {
        return servant.getPosition().equals(new Position(Players.getLocal().getX() - 1, Players.getLocal().getY() - 1));
    }

    private boolean callServant() {
        final InterfaceComponent callServant = Interfaces.getComponent(370, 19);
        if (callServant == null || !callServant.isVisible())
            if (openHouseOptions())
                Time.sleepUntil(() -> Interfaces.getComponent(370, 19) != null, 1500);

        return callServant != null && callServant.click();
    }

    private boolean openHouseOptions() {
        final InterfaceComponent houseOptions = Interfaces.getComponent(261, 98);
        if (houseOptions == null)
            if (Tabs.getOpen() != Tab.OPTIONS)
                if (Tabs.open(Tab.OPTIONS))
                    Time.sleepUntil(() -> Tabs.isOpen(Tab.OPTIONS), 1500);

        return houseOptions != null && houseOptions.click();
    }

    @Override
    public String toString() {
        return "Talking to butler";
    }
}

