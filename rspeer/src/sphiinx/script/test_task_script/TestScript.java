package sphiinx.script.test_task_script;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.api.SphiinxScript;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.MONEY_MAKING, name = "Test Task Script", desc = "Test Task Script")
public class TestScript extends SphiinxScript {

    @Override
    public void onStart() {
        submit(new TestTask());
        super.onStart();
    }
}

