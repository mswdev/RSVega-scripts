package sphiinx.script.public_script.spx_account_checker.data;

import java.util.HashMap;
import java.util.Map;

public class Vars {

    public static Vars vars;

    public static Vars get() {
        return vars == null ? vars = new Vars() : vars;
    }

    public static void reset() {
        vars = null;
    }

    public Map<String, String> general_data = new HashMap<>();
    public Map<String, String> osrs_data = new HashMap<>();
    public boolean check_age;
    public boolean check_bank;
    public boolean can_logout;

}

