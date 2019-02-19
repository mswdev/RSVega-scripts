package sphiinx.api.script.impl.mission.item_management_mission.worker;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.api.script.framework.item_management.ItemManagementTracker;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.framework.worker.WorkerHandler;
import sphiinx.api.script.impl.mission.item_management_mission.ItemManagementMission;
import sphiinx.api.script.impl.mission.item_management_mission.worker.impl.BuyWorker;
import sphiinx.api.script.impl.mission.item_management_mission.worker.impl.SellWorker;
import sphiinx.api.script.impl.mission.item_management_mission.worker.impl.TeleportToGrandExchangeWorker;
import sphiinx.api.script.impl.mission.item_management_mission.worker.impl.WithdrawSellablesWorker;
import sphiinx.api.script.impl.worker.MovementWorker;
import sphiinx.api.script.impl.worker.banking.DepositWorker;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;
import sphiinx.api.script.impl.worker.interactables.ItemWorker;
import sphiinx.api.script.impl.worker.interactables.NpcWorker;

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

        if (BankLocation.GRAND_EXCHANGE.getPosition().distance() > 20) {
            if (Equipment.contains(TeleportToGrandExchangeWorker.RING_OF_WEALTH)) {
                return teleport_to_grand_exchange_worker;
            }

            if (Inventory.contains(TeleportToGrandExchangeWorker.RING_OF_WEALTH))
                return new ItemWorker(TeleportToGrandExchangeWorker.RING_OF_WEALTH);

            if (Arrays.stream(TeleportToGrandExchangeWorker.RING_OF_WEALTH_IDS).filter(a -> mission.getScript().bank_cache.get().getOrDefault(a, 0) != 0).findFirst().isPresent())
                return new WithdrawWorker(TeleportToGrandExchangeWorker.RING_OF_WEALTH);

            return new MovementWorker(BankLocation.GRAND_EXCHANGE.getPosition());
        }

        final long inventory_gold = Inventory.getCount(true, ItemManagementTracker.GOLD_ID);
        if (mission.has_put_in_offer || inventory_gold >= mission.item_management_entry.value_needed) {
            if (!GrandExchange.isOpen())
                return openGrandExchangeWorker();

            return buy_worker;
        } else if (mission.item_management_tracker.getTotalGold() >= mission.item_management_entry.value_needed) {
            return new WithdrawWorker(a -> a.getName().equals("Coins"), 0);
        } else {
            if (!mission.has_withdrawn_sellables) {
                return withdraw_sellables_worker;
            }

            if (!GrandExchange.isOpen())
                return openGrandExchangeWorker();

            return sell_worker;
        }
    }

    private NpcWorker openGrandExchangeWorker() {
        return new NpcWorker(a -> a.getName().equals("Grand Exchange Clerk"), a -> a.equals("Exchange"));
    }
}
