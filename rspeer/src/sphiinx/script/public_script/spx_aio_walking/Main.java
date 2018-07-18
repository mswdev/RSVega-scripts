package sphiinx.script.public_script.spx_aio_walking;

import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.api.SphiinxScript;
import sphiinx.script.public_script.spx_aio_walking.data.Vars;
import sphiinx.script.public_script.spx_aio_walking.tasks.WalkToLocation;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.MONEY_MAKING, name = "SPX AIO Walking", desc = "AIO Walking")
public class Main extends SphiinxScript {

    @Override
    public void onStart() {
        Vars.get().walk_location = new Position(3167, 3470);
        submit(new WalkToLocation());
        super.onStart();
    }

}

