package sphiinx.script.public_script.spx_tutorial_island.api.game_util;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.SPXScript;

import java.util.HashMap;
import java.util.Map;

public class BankCache extends Thread {

    private static final Map<Integer, Integer> BANK_CACHE = new HashMap<>();
    private final SPXScript script;

    public BankCache(SPXScript script) {
        this.script = script;
    }

    @Override
    public void run() {
        while (!script.isStopping()) {
            if (Bank.isOpen()) {
                update();
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates the bank cache.
     */
    private void update() {
        final Item[] items = Bank.getItems();
        if (items == null)
            return;

        BANK_CACHE.clear();
        for (Item item : items) {
            if (item == null)
                continue;

            BANK_CACHE.put(item.getId(), item.getStackSize());
        }
    }

    /**
     * Gets the hash map containing the bank cache.
     *
     * @return The hash map containing the bank cache.
     */
    public static Map<Integer, Integer> getMap() {
        return BANK_CACHE;
    }

    /**
     * Gets the amount of items from the specified id.
     *
     * @param id The id to get the amount of items.
     * @return The amount of items from the specified id.
     */
    public static int getValue(int id) {
        return getMap().get(id) != null ? getMap().get(id) : 0;
    }
}

