package org.script.private_script.the_bull.house_planking.data;

import com.beust.jcommander.Parameter;
import org.api.game.skills.firemaking.LogType;

public class Args {

    @Parameter(names = "-log_type1")
    public LogType LOG_TYPE1 = LogType.OAK;

    @Parameter(names = "-log_type1_quantity")
    public int LOG_TYPE1_QUANTITY = 48;

    @Parameter(names = "-log_type2")
    public LogType LOG_TYPE2 = LogType.MAHOGANY;

    @Parameter(names = "-log_type2_quantity")
    public int LOG_TYPE2_QUANTITY = 48;

}

