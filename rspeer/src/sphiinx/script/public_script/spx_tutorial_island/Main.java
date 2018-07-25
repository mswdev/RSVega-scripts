package sphiinx.script.public_script.spx_tutorial_island;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.api.SPXScript;
import sphiinx.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.OTHER, name = "SPX Tutorial Island", desc = "Completes tutorial island.")
public class Main extends SPXScript {

    private LinkedHashMap<String, String> ACCOUNT_DETAILS = new LinkedHashMap<>();

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> MISSIONS = new LinkedList<>();
        ACCOUNT_DETAILS.put("rspeerdev9@gmail.com", "Killkid5");
        ACCOUNT_DETAILS.put("rspeerdev8@gmail.com", "Killkid5");
        MISSIONS.add(new TI_Mission(this, ACCOUNT_DETAILS));
        return MISSIONS;
    }

}

