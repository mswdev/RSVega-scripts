package org.script.free_script.spx_account_checker.http;

import com.google.gson.JsonObject;
import org.api.script.SPXScript;
import org.rspeer.script.GameAccount;
import org.rspeer.ui.Log;

public class AccountManager {


    private final SPXScript script;
    private int accountId;
    private String username;
    private String password;

    public AccountManager(SPXScript script) {
        this.script = script;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setNext() {
        final JsonObject jsonObject = AccountData.getNext();
        if (jsonObject == null || !jsonObject.has("id") || !jsonObject.has("username") || !jsonObject.has("password")) {
            Log.severe("Failed to set next account.");
            return;
        }

        accountId = jsonObject.get("id").getAsInt();
        username = jsonObject.get("username").getAsString();
        password = jsonObject.get("password").getAsString();
        script.setAccount(new GameAccount(username, password));
        Log.info("[ACCOUNT CHECKER]: [Total: " + AccountData.getTotal() + "] [ID:" + accountId + " | Username:" + username + " | Password:" + password + "]");
    }
}
