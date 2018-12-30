package sphiinx.script.private_script.the_bull.house_planking.mission.worker.impl;

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
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.DialogueWorker;
import sphiinx.script.private_script.the_bull.house_planking.Main;

public class ButlerDialogue extends Worker {


    private static final DialogueWorker BUTLER_DIALOGUE = new DialogueWorker(a -> a.contains("Sawmill") || a.contains("sawmill") || a.equals("Yes") || a.equals("Okay, here's 10,000 coins.") || a.contains("Take them back"));
    private boolean is_setup;

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        final Npc servant = Npcs.getNearest("Demon butler");
        if (servant == null) {
            callServant();
        } else {
            if (!Dialog.isOpen())
                callServant();

            if (isServantStuck(servant))
                Movement.walkTo(new Position(Players.getLocal().getX() - 1, Players.getLocal().getY()));

            if (!is_setup)
                if (Inventory.use(a -> a.getId() == Main.ARGS.LOG_TYPE.getItemID(), servant))
                    is_setup = true;

            if (EnterInput.isOpen())
                EnterInput.initiate(24);

            BUTLER_DIALOGUE.work();
        }
    }

    private boolean isServantStuck(Npc servant) {
        return servant.getPosition().equals(new Position(Players.getLocal().getX() - 1, Players.getLocal().getY() - 1));
    }

    private boolean callServant() {
        final InterfaceComponent call_servant = Interfaces.getComponent(370, 19);
        if (call_servant == null || !call_servant.isVisible())
            if (openHouseOptions())
                Time.sleepUntil(() -> Interfaces.getComponent(370, 19) != null, 1500);

        return call_servant != null && call_servant.click();
    }

    private boolean openHouseOptions() {
        final InterfaceComponent house_options = Interfaces.getComponent(261, 98);
        if (house_options == null)
            if (Tabs.getOpen() != Tab.OPTIONS)
                if (Tabs.open(Tab.OPTIONS))
                    Time.sleepUntil(() -> Tabs.isOpen(Tab.OPTIONS), 1500);

        return house_options != null && house_options.click();
    }

    @Override
    public String toString() {
        return "Talking to butler";
    }
}

