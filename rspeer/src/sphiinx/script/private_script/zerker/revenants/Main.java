package sphiinx.script.private_script.zerker.revenants;

import org.rspeer.runetek.event.listeners.ChatMessageListener;
import org.rspeer.runetek.event.types.ChatMessageEvent;
import org.rspeer.runetek.event.types.ChatMessageType;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.api.script.framework.mission.Mission;
import sphiinx.api.script.SPXScript;
import sphiinx.script.private_script.zerker.revenants.data.Args;
import sphiinx.script.private_script.zerker.revenants.mission.RevenantMission;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.SMITHING, name = "[P-SPX] Revenants", desc = "")
public class Main extends SPXScript implements ChatMessageListener {


    public static final Args ARGS = new Args();

    @Override
    public Object getArguments() {
        return ARGS;
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new RevenantMission());
        return missions;
    }

    @Override
    public void notify(ChatMessageEvent chatMessageEvent) {
        if (chatMessageEvent.getType() != ChatMessageType.SERVER)
            return;

        if (chatMessageEvent.getMessage().contains("blowpipe"))
            ARGS.refill_toxic_blowpipe = true;

        if (chatMessageEvent.getMessage().contains("craw's"))
            ARGS.refill_craws_bow = true;
        //todo ask for craws bow messages
    }
}

