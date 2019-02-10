package sphiinx.script.public_script.spx_aio_firemaking.data;

import com.beust.jcommander.Parameter;
import sphiinx.api.game.skills.firemaking.LogType;

public class Args {

    @Parameter(names = "-log_type")
    public LogType log_type;

    @Parameter(names = "-set_progressive", arity = 1)
    public boolean set_progressive = true;

}
