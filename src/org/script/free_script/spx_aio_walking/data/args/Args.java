package org.script.free_script.spx_aio_walking.data.args;

import com.beust.jcommander.Parameter;
import org.rspeer.runetek.api.commons.BankLocation;

public class Args {

    @Parameter(names = "-location", arity = 1)
    public BankLocation LOCATION;

}

