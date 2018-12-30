package sphiinx.script.private_script.zerker.revenants.mission.worker.impl.ge;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.GrandExchangeSetup;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;

import java.util.function.Predicate;

public class SellItemWorker extends Worker {

    private static final OpenGrandExchange OPEN_GRAND_EXCHANGE = new OpenGrandExchange();
    private static WithdrawWorker WITHDRAW_ITEM;
    private boolean waiting_for_item;
    private String item_name;
    private int amount_to_sell;
    private Predicate<RSGrandExchangeOffer> offer;

    public SellItemWorker(String item_name) {
        this.item_name = item_name;
        WITHDRAW_ITEM = new WithdrawWorker(a -> a.getName().equals(item_name), 0);
        offer = a -> a.getItemDefinition().getName().equals(item_name);
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (!Inventory.contains(item_name)) {
            WITHDRAW_ITEM.work();
        } else {
            amount_to_sell = Inventory.getCount(item_name);
            if (GrandExchange.isOpen()) {
                waiting_for_item = true;
                if (GrandExchangeSetup.isOpen() && GrandExchangeSetup.getItem().getName().equals(item_name)) {
                    if (GrandExchangeSetup.getQuantity() != amount_to_sell)
                        if (GrandExchangeSetup.setQuantity(amount_to_sell))
                            Time.sleepUntil(() -> GrandExchangeSetup.getQuantity() == amount_to_sell, 1500);

                    if (GrandExchangeSetup.increasePrice(2))
                        if (GrandExchangeSetup.confirm())
                            Time.sleepUntil(() -> GrandExchange.getView() == GrandExchange.View.OVERVIEW, 1500);
                } else {
                    if (GrandExchange.getFirst(offer).getProgress() == RSGrandExchangeOffer.Progress.FINISHED)
                        if (GrandExchange.collectAll(true)) {
                            Time.sleepUntil(() -> GrandExchange.getFirst(offer) == null, 1500);
                            waiting_for_item = false;
                        }

                    if (GrandExchange.getView() == GrandExchange.View.SELL_OFFER) {
                        if (GrandExchangeSetup.setItem(item_name)) {
                            Time.sleepUntil(() -> GrandExchangeSetup.getItem().getName().equals(item_name), 1500);
                        }
                    } else {
                        if (GrandExchange.createOffer(RSGrandExchangeOffer.Type.SELL))
                            Time.sleepUntil(GrandExchangeSetup::isOpen, 1500);
                    }
                }
            } else {
                OPEN_GRAND_EXCHANGE.work();
            }
        }
    }

    @Override
    public String toString() {
        return "Selling items";
    }

    public boolean isWaitingForItem() {
        return waiting_for_item;
    }
}

