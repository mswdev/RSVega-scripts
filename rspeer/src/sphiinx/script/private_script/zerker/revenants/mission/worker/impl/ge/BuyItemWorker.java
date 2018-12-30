package sphiinx.script.private_script.zerker.revenants.mission.worker.impl.ge;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.GrandExchangeSetup;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.game.pricechecking.PriceCheck;
import sphiinx.script.private_script.zerker.revenants.mission.RevenantMission;

import java.util.function.Predicate;

public class BuyItemWorker extends Worker {

    private static final OpenGrandExchange OPEN_GRAND_EXCHANGE = new OpenGrandExchange();
    private final WithdrawCoins WITHDRAW_COINS;
    private boolean waiting_for_item;
    private int item_id;
    private int amount_to_buy;
    private Predicate<RSGrandExchangeOffer> offer;

    public BuyItemWorker(RevenantMission mission, int item_id, int amount_to_buy) {
        WITHDRAW_COINS = new WithdrawCoins(mission);
        this.item_id = item_id;
        this.amount_to_buy = amount_to_buy;
        offer = a -> a.getItemId() == item_id;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (!waiting_for_item && Inventory.getCount(WithdrawCoins.COINS) <= PriceCheck.getRSPrice(item_id) * amount_to_buy) {
            WITHDRAW_COINS.work();
        } else {
            if (GrandExchange.isOpen()) {
                waiting_for_item = true;
                if (GrandExchangeSetup.isOpen() && GrandExchangeSetup.getItem().getId() == item_id) {
                    if (GrandExchangeSetup.getQuantity() != amount_to_buy)
                        if (GrandExchangeSetup.setQuantity(amount_to_buy))
                            Time.sleepUntil(() -> GrandExchangeSetup.getQuantity() == amount_to_buy, 1500);

                    if (GrandExchangeSetup.increasePrice(2))
                        if (GrandExchangeSetup.confirm())
                            Time.sleepUntil(() -> GrandExchange.getView() == GrandExchange.View.OVERVIEW, 1500);
                } else {
                    if (GrandExchange.getFirst(offer).getProgress() == RSGrandExchangeOffer.Progress.FINISHED)
                        if (GrandExchange.collectAll(true)) {
                            Time.sleepUntil(() -> GrandExchange.getFirst(offer) == null, 1500);
                            waiting_for_item = false;
                        }

                    if (GrandExchange.getView() == GrandExchange.View.BUY_OFFER) {
                        if (GrandExchangeSetup.setItem(item_id)) {
                            Time.sleepUntil(() -> GrandExchangeSetup.getItem().getId() == item_id, 1500);
                        }
                    } else {
                        if (GrandExchange.createOffer(RSGrandExchangeOffer.Type.BUY))
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
        return "Buying items";
    }

    public boolean isWaitingForItem() {
        return waiting_for_item;
    }
}

