package org.api.script.impl.mission.item_management_mission.worker;

import org.api.script.framework.item_management.ItemManagementTracker;
import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.api.script.impl.mission.item_management_mission.ItemManagementMission;
import org.api.script.impl.mission.item_management_mission.worker.impl.BuyWorker;
import org.api.script.impl.mission.item_management_mission.worker.impl.SellWorker;
import org.api.script.impl.mission.item_management_mission.worker.impl.TeleportToGrandExchangeWorker;
import org.api.script.impl.mission.item_management_mission.worker.impl.WithdrawSellablesWorker;
import org.api.script.impl.worker.MovementWorker;
import org.api.script.impl.worker.banking.DepositWorker;
import org.api.script.impl.worker.banking.WithdrawWorker;
import org.api.script.impl.worker.interactables.ItemWorker;
import org.api.script.impl.worker.interactables.NpcWorker;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;

import java.util.Arrays;

public class ItemManagementWorkerHandler extends WorkerHandler {

    private final ItemManagementMission mission;
    private final BuyWorker buy_worker;
    private final SellWorker sell_worker;
    private final WithdrawSellablesWorker withdraw_sellables_worker;
    private final TeleportToGrandExchangeWorker teleport_to_grand_exchange_worker;

    public ItemManagementWorkerHandler(ItemManagementMission mission) {
        this.mission = mission;
        this.buy_worker = new BuyWorker(mission);
        this.sell_worker = new SellWorker(mission);
        this.withdraw_sellables_worker = new WithdrawSellablesWorker(mission);
        this.teleport_to_grand_exchange_worker = new TeleportToGrandExchangeWorker();
    }

    @Override
    public Worker decide() {
        if (Inventory.isFull())
            return new DepositWorker();

        if (Inventory.contains(a -> a.getName().equals("Ring of wealth")))
            return new DepositWorker(a -> a.getName().equals("Ring of wealth"));

        if (BankLocation.GRAND_EXCHANGE.getPosition().distance() > 20) {
            if (!Tabs.isOpen(Tab.EQUIPMENT))
                if (Tabs.open(Tab.EQUIPMENT))
                    Time.sleepUntil(() -> Tabs.isOpen(Tab.EQUIPMENT), 1500);

            if (Equipment.contains(TeleportToGrandExchangeWorker.RING_OF_WEALTH))
                return teleport_to_grand_exchange_worker;

            if (Inventory.contains(TeleportToGrandExchangeWorker.RING_OF_WEALTH))
                return new ItemWorker(TeleportToGrandExchangeWorker.RING_OF_WEALTH);

            if (Arrays.stream(TeleportToGrandExchangeWorker.RING_OF_WEALTH_IDS).filter(a -> mission.getScript().bank_cache.get().getOrDefault(a, 0) != 0).findFirst().isPresent())
                return new WithdrawWorker(TeleportToGrandExchangeWorker.RING_OF_WEALTH);

            return new MovementWorker(BankLocation.GRAND_EXCHANGE.getPosition());
        }

        final long inventory_gold = Inventory.getCount(true, ItemManagementTracker.GOLD_ID);
        if (mission.has_put_in_offer || inventory_gold >= mission.getItemManagementEntry().getValueNeeded(mission.getItemManagementTracker().getItemManagement().buyPriceModifier())) {
            if (!GrandExchange.isOpen())
                return openGrandExchangeWorker();

            return buy_worker;
        } else if (mission.getItemManagementTracker().getTotalGold() >= mission.getItemManagementEntry().getValueNeeded(mission.getItemManagementTracker().getItemManagement().buyPriceModifier())) {
            return new WithdrawWorker(a -> a.getName().equals("Coins"), 0);
        } else {
            if (!mission.has_withdrawn_sellables)
                return withdraw_sellables_worker;

            if (!GrandExchange.isOpen())
                return openGrandExchangeWorker();

            return sell_worker;
        }
    }

    private NpcWorker openGrandExchangeWorker() {
        return new NpcWorker(a -> a.getName().equals("Grand Exchange Clerk"), a -> a.equals("Exchange"));
    }
}
