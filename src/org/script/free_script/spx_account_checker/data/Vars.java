package org.script.free_script.spx_account_checker.data;

import java.util.HashMap;
import java.util.Map;

public class Vars {

    public static Vars vars;
    public Map<String, String> generalData = new HashMap<>();
    public Map<String, String> osrsData = new HashMap<>();
    public boolean checkAge;
    public boolean checkBank;
    public boolean canLogout;

    public static Vars get() {
        return vars == null ? vars = new Vars() : vars;
    }

    public static void reset() {
        vars = null;
    }

}

