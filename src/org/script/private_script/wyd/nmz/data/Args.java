package org.script.private_script.wyd.nmz.data;

import com.beust.jcommander.Parameter;

public class Args {

    @Parameter(names = "-revenant_type")
    public RevenantType REVENANT_TYPE;

    @Parameter(names = "-min_loot_value")
    public int MIN_LOOT_VALUE;

    @Parameter(names = "-min_teleport_value")
    public int MIN_TELEPORT_VALUE;

    @Parameter(names = "-ge_auto_sell")
    public boolean GE_AUTO_SELL;

    public boolean refill_toxic_blowpipe;
    public boolean refill_craws_bow;

}

