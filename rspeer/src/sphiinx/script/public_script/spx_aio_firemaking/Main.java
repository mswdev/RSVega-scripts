package sphiinx.script.public_script.spx_aio_firemaking;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;
import sphiinx.api.SPXScript;
import sphiinx.api.framework.mission.Mission;
import sphiinx.api.framework.ui.javafx.FXGUI;
import sphiinx.script.public_script.spx_aio_firemaking.data.LogType;
import sphiinx.script.public_script.spx_aio_firemaking.data.Vars;
import sphiinx.script.public_script.spx_aio_firemaking.mission.FireMakingMission;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.FIREMAKING, name = "[SPX] AIO Firemaking", desc = "")
public class Main extends SPXScript {

    @Override
    public void onStart() {
        loadScriptArgs();
        super.onStart();
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        LinkedList<Mission> MISSIONS = new LinkedList<>();
        MISSIONS.add(new FireMakingMission());
        return MISSIONS;
    }

    @Override
    public FXGUI getFXGUI() {
        return null;
    }

    private void loadScriptArgs() {
        final String ARG = getArgs();
        if (ARG == null) {
            Log.fine("[ARGS]: No script arguments found; using default settings");
            return;
        }

        for (LogType LOG : LogType.values()) {
            if (LOG.getName().equalsIgnoreCase(getArgs()))
                continue;

            Vars.get().log_type = LOG;
            Vars.get().is_progressive = false;
        }

        Log.fine("[ARGS]: Loaded script arguments");
    }
}

