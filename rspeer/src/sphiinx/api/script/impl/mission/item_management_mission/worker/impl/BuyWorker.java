package sphiinx.api.script.impl.mission.item_management_mission.worker.impl;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.GrandExchangeSetup;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.mission.item_management_mission.ItemManagementMission;

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
        if (Inventory.contains(mission.item_management_entry.id)) {
            mission.should_end = true;
            return;
        }

        final RSGrandExchangeOffer item_management_entry = GrandExchange.getFirst(a -> a.getItemId() == mission.item_management_entry.id);
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

            if (GrandExchangeSetup.getItem() == null || GrandExchangeSetup.getItem().getId() != mission.item_management_entry.id) {
                if (GrandExchangeSetup.setItem(mission.item_management_entry.id))
                    Time.sleepUntil(() -> GrandExchangeSetup.getItem() != null && GrandExchangeSetup.getItem().getId() == mission.item_management_entry.id, 1500);
                return;
            }

            if (GrandExchangeSetup.getPricePerItem() != mission.item_management_entry.price) {
                if (GrandExchangeSetup.setPrice(mission.item_management_entry.price))
                    Time.sleepUntil(() -> GrandExchangeSetup.getPricePerItem() == mission.item_management_entry.price, 1500);
                return;
            }

            if (GrandExchangeSetup.getQuantity() != mission.item_management_entry.quantity) {
                if (GrandExchangeSetup.setQuantity(mission.item_management_entry.quantity))
                    Time.sleepUntil(() -> GrandExchangeSetup.getQuantity() == mission.item_management_entry.quantity, 1500);
                return;
            }

            if (GrandExchangeSetup.confirm())
                if (Time.sleepUntil(() -> GrandExchangeSetup.getItem() == null, 1500))
                    mission.has_put_in_offer = true;
        }
    }

    @Override
    public String toString() {
        return "Executing item management buy worker.";
    }
}
