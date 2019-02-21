package sphiinx.api.script.impl.mission.item_management_mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.GrandExchangeSetup;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;
import sphiinx.api.game.pricechecking.PriceCheck;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.mission.item_management_mission.ItemManagementMission;

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
        final RSGrandExchangeOffer item_management_entry = GrandExchange.getFirst(a -> a.getItemId() == mission.getItemManagementEntry().id);
        if (item_management_entry != null) {
            mission.has_put_in_offer = true;

            if (item_management_entry.getProgress() == RSGrandExchangeOffer.Progress.FINISHED) {
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

            if (GrandExchangeSetup.getItem() == null || GrandExchangeSetup.getItem().getId() != mission.getItemManagementEntry().id) {
                if (GrandExchangeSetup.setItem(mission.getItemManagementEntry().id))
                    Time.sleepUntil(() -> GrandExchangeSetup.getItem() != null && GrandExchangeSetup.getItem().getId() == mission.getItemManagementEntry().id, 1500);
                return;
            }

            if (GrandExchangeSetup.getPricePerItem() != getItemBuyPrice(mission.getItemManagementEntry().id)) {
                if (GrandExchangeSetup.setPrice(getItemBuyPrice(mission.getItemManagementEntry().id)))
                    Time.sleepUntil(() -> GrandExchangeSetup.getPricePerItem() == getItemBuyPrice(mission.getItemManagementEntry().id), 1500);
                return;
            }

            if (GrandExchangeSetup.getQuantity() != mission.getItemManagementEntry().quantity) {
                if (GrandExchangeSetup.setQuantity(mission.getItemManagementEntry().quantity))
                    Time.sleepUntil(() -> GrandExchangeSetup.getQuantity() == mission.getItemManagementEntry().quantity, 1500);
                return;
            }

            if (GrandExchangeSetup.confirm())
                if (Time.sleepUntil(() -> GrandExchangeSetup.getItem() == null, 1500))
                    mission.has_put_in_offer = true;
        }
    }

    private int getItemBuyPrice(int id) {
        try {
            return (int) (PriceCheck.getOSBuddyPrice(id) * mission.getItemManagementTracker().item_management.buyPriceModifier());
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
