package org.script.public_script.spx_account_checker.http;

import com.google.gson.JsonObject;
import org.api.script.SPXScript;
import org.rspeer.script.GameAccount;
import org.rspeer.ui.Log;

public class AccountManager {


    private final SPXScript script;
    private int account_id;
    private String username;
    private String password;

    public AccountManager(SPXScript script) {
        this.script = script;
    }

    public int getAccountID() {
        return account_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setNext() {
        final JsonObject json_object = AccountData.getNext();
        if (json_object == null || !json_object.has("id") || !json_object.has("username") || !json_object.has("password")) {
            Log.severe("Failed to set next account.");
            return;
        }

        account_id = json_object.get("id").getAsInt();
        username = json_object.get("username").getAsString();
        password = json_object.get("password").getAsString();
        script.setAccount(new GameAccount(username, password));
        Log.info("[ACCOUNT CHECKER]: [Total: " + AccountData.getTotal() + "] [ID:" + account_id + " | Username:" + username + " | Password:" + password + "]");
    }
}
