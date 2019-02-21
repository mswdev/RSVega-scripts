package sphiinx.script.private_script.the_bull.house_planking.data;

import com.beust.jcommander.Parameter;
import sphiinx.api.game.skills.firemaking.LogType;

public class Args {

    @Parameter(names = "-log_type1")
    public LogType LOG_TYPE1 = LogType.OAK;

    @Parameter(names = "-log_type1_quantity")
    public int LOG_TYPE1_QUANTITY = 3800;

}

