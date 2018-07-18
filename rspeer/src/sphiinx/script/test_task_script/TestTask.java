package sphiinx.script.test_task_script;

import org.rspeer.script.task.Task;

public class TestTask extends Task {

    @Override
    public int execute() {

        return 200;
    }

    @Override
    public boolean validate() {
        return true;
    }

}

