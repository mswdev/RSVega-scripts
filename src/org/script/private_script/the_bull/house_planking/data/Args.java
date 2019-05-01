package org.script.private_script.the_bull.house_planking.data;

import com.beust.jcommander.Parameter;
import org.api.game.skills.firemaking.LogType;

public class Args {

    @Parameter(names = "-log_type1", arity = 1)
    public LogType LOG_TYPE1 = LogType.OAK;

    @Parameter(names = "-log_type1_quantity", arity = 1)
    public int LOG_TYPE1_QUANTITY = 48;

    @Parameter(names = "-log_type2", arity = 1)
    public LogType LOG_TYPE2 = LogType.MAHOGANY;

    @Parameter(names = "-log_type2_quantity", arity = 1)
    public int LOG_TYPE2_QUANTITY = 48;

}

