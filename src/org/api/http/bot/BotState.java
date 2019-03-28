package org.api.http.bot;

public enum BotState {

    DISABLED("is_disabled"),
    LOCKED("is_locked"),
    AUTHENTICATOR("is_auth"),
    BILLING("is_billing"),
    INVALID("is_invalid");

    private final String table_name;

    BotState(String table_name) {
        this.table_name = table_name;
    }

    public String getTableName() {
        return table_name;
    }
}
