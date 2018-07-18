package antiban.script.public_script.antiban_account_checker.data;

import java.util.ArrayList;
import java.util.HashMap;

public class Vars {

    public static Vars vars;

    public static Vars get() {
        return vars == null ? vars = new Vars() : vars;
    }

    public static void reset() {
        vars = null;
    }

    public ArrayList<String> account_details = new ArrayList<>();

    public HashMap<String, String> collected_data = new HashMap<>();

    public boolean collect_general_data;

    public boolean collect_bank_data;

    public boolean collect_hans_data;

    public String is_oldschool = "1";

}
