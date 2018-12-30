package sphiinx.script.private_script.the_bull.house_planking.data;

import com.beust.jcommander.Parameter;
import sphiinx.api.game.skills.firemaking.LogType;

public class Args {

    @Parameter(names = "-log_type")
    public LogType LOG_TYPE = LogType.TEAK;

}

