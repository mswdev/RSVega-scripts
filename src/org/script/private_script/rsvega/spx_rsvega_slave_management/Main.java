package org.script.private_script.rsvega.spx_rsvega_slave_management;

import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.api.script.impl.mission.mule_slave_management.slave_management_mission.SlaveManagementMission;
import org.api.script.impl.mission.mule_slave_management.slave_management_mission.TradeMessageListener;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.event.listeners.ChatMessageListener;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "[SPX] RSVega Slave Management", desc = "[SPX] RSVega Slave Management")
public class Main extends SPXScript {

    private final TradeMessageListener tradeMessageListener = new TradeMessageListener();

    @Override
    public void onStart() {
        super.onStart();
        Game.getEventDispatcher().register(tradeMessageListener);
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new SlaveManagementMission(this, tradeMessageListener));
        return missions;
    }

    @Override
    public void onStop() {
        super.onStop();
        Game.getEventDispatcher().deregister(tradeMessageListener);
    }

    public ChatMessageListener getTradeMessageListener() {
        return tradeMessageListener;
    }
}
