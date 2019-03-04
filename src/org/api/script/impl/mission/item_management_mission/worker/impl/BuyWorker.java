package org.api.script.impl.mission.item_management_mission.worker.impl;

import org.api.game.pricechecking.PriceCheck;
import org.api.script.framework.item_management.ItemManagementTracker;
import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.item_management_mission.ItemManagementMission;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.GrandExchangeSetup;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;

import java.io.IOException;

public class BuyWorker extends Worker {

    private ItemManagementMission mission;


    public BuyWorker(ItemManagementMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (mission.getItemManagementEntry().getId() == ItemManagementTracker.GOLD_ID) {
            mission.should_end = true;
            return;
        }

        final RSGrandExchangeOffer item_management_entry = GrandExchange.getFirst(a -> a.getItemId() == mission.getItemManagementEntry().getId());
        if (item_management_entry != null) {
            mission.has_put_in_offer = true;

            if (GrandExchange.getFirst(a -> a.getProgress() == RSGrandExchangeOffer.Progress.FINISHED) != null && GrandExchange.getView() == GrandExchange.View.OVERVIEW) {
                final int inventory_cache = Inventory.getCount(true);
                if (GrandExchange.collectAll())
                    if (Time.sleepUntil(() -> Inventory.getCount(true) != inventory_cache, 1500))
                        mission.should_end = true;

                return;
            }
        }

        if (!mission.has_put_in_offer) {
            if (GrandExchange.getView() != GrandExchange.View.BUY_OFFER) {
                if (GrandExchange.createOffer(RSGrandExchangeOffer.Type.BUY))
                    Time.sleepUntil(() -> GrandExchange.getView() == GrandExchange.View.BUY_OFFER, 1500);
                return;
            }

            if (GrandExchangeSetup.getItem() == null || GrandExchangeSetup.getItem().getId() != mission.getItemManagementEntry().getId()) {
                if (GrandExchangeSetup.setItem(mission.getItemManagementEntry().getId()))
                    Time.sleepUntil(() -> GrandExchangeSetup.getItem() != null && GrandExchangeSetup.getItem().getId() == mission.getItemManagementEntry().getId(), 1500);
                return;
            }

            if (GrandExchangeSetup.getPricePerItem() != getItemBuyPrice(mission.getItemManagementEntry().getId())) {
                if (GrandExchangeSetup.setPrice(getItemBuyPrice(mission.getItemManagementEntry().getId())))
                    Time.sleepUntil(() -> GrandExchangeSetup.getPricePerItem() == getItemBuyPrice(mission.getItemManagementEntry().getId()), 1500);
                return;
            }

            if (GrandExchangeSetup.getQuantity() != mission.getItemManagementEntry().getQuantity()) {
                if (GrandExchangeSetup.setQuantity(mission.getItemManagementEntry().getQuantity()))
                    Time.sleepUntil(() -> GrandExchangeSetup.getQuantity() == mission.getItemManagementEntry().getQuantity(), 1500);
                return;
            }

            if (GrandExchangeSetup.confirm())
                if (Time.sleepUntil(() -> GrandExchangeSetup.getItem() == null, 1500))
                    mission.has_put_in_offer = true;
        }
    }

    private int getItemBuyPrice(int id) {
        try {
            return (int) (PriceCheck.getOSBuddyPrice(id) * mission.getItemManagementTracker().getItemManagement().buyPriceModifier());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public String toString() {
        return "Executing item management buy worker.";
    }
}
