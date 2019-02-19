package sphiinx.script.public_script.spx_account_checker.mission.worker.impl.LoginAccount;

import sphiinx.api.script.framework.worker.Worker;

public class Wait extends Worker {


    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
    }

    @Override
    public String toString() {
        return "Waiting";
    }
}

