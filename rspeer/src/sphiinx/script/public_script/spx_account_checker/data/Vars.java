package sphiinx.script.public_script.spx_account_checker.data;

import java.util.HashMap;

public class Vars {

    public static Vars vars;

    public static Vars get() {
        return vars == null ? vars = new Vars() : vars;
    }

    public static void reset() {
        vars = null;
    }

    public HashMap<String, String> ACCOUNT_DATA = new HashMap<>();
    public boolean check_age;
    public boolean check_bank;
    public boolean can_logout;

}

