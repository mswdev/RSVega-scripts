package sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl.buying;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.GrandExchangeSetup;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl.OpenGrandExchange;

public class BuyGlory extends Worker {

    public boolean has_item;
    private final int amulet_of_glory_id = 11978;
    private final OpenGrandExchange open_grand_exchange = new OpenGrandExchange();

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        final int amount_in_bank = 0;//(int) Math.round((BankCache.getValue(11978) * 6) + (BankCache.getValue(11976) * 5) + (BankCache.getValue(1712) * 4) + (BankCache.getValue(1710) * 3) + (BankCache.getValue(1708) * 2) + BankCache.getValue(1706) / 6.0);
        final int amount_to_buy = 40 - amount_in_bank;
        if (amount_to_buy <= 0) {
            has_item = true;
            return;
        }

        if (GrandExchange.isOpen()) {
            if (GrandExchangeSetup.isOpen() && GrandExchangeSetup.getItemId() == amulet_of_glory_id) {
                if (GrandExchangeSetup.getQuantity() != amount_to_buy)
                    if (GrandExchangeSetup.setQuantity(amount_to_buy))
                        Time.sleepUntil(() -> GrandExchangeSetup.getQuantity() == amount_to_buy, 1500);

                if (GrandExchangeSetup.increasePrice(1))
                    if (GrandExchangeSetup.confirm())
                        if (Time.sleepUntil(() -> GrandExchange.getView() == GrandExchange.View.OVERVIEW, 1500))
                            has_item = true;
            } else {
                if (GrandExchange.getView() == GrandExchange.View.BUY_OFFER) {
                    if (GrandExchangeSetup.setItem(amulet_of_glory_id)) {
                        Time.sleepUntil(() -> GrandExchangeSetup.getItemId() == amulet_of_glory_id, 1500);
                    }
                } else {
                    if (GrandExchange.createOffer(RSGrandExchangeOffer.Type.BUY))
                        Time.sleepUntil(GrandExchangeSetup::isOpen, 1500);
                }
            }
        } else {
            open_grand_exchange.work();
        }
    }

    @Override
    public String toString() {
        return "Buying Amulet of glory.";
    }
}

