package sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl.buying;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.GrandExchangeSetup;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl.OpenGrandExchange;

public class BuyCosmicRune extends Worker {

    public boolean has_item;
    private final int cosmic_rune_id = 564;
    private final OpenGrandExchange open_grand_exchange = new OpenGrandExchange();;

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        // [TODO - 2018-11-09]: Fix bank caches in air orb
        final int amount_in_bank = 0;//BankCache.getValue(cosmic_rune_id);
        final int amount_to_buy = 6000 - amount_in_bank;
        if (amount_to_buy <= 0) {
            has_item = true;
            return;
        }

        if (GrandExchange.isOpen()) {
            if (GrandExchangeSetup.isOpen() && GrandExchangeSetup.getItemId() == cosmic_rune_id) {
                if (GrandExchangeSetup.getQuantity() != amount_to_buy)
                    if (GrandExchangeSetup.setQuantity(amount_to_buy))
                        Time.sleepUntil(() -> GrandExchangeSetup.getQuantity() == amount_to_buy, 1500);

                if (GrandExchangeSetup.increasePrice(1))
                    if (GrandExchangeSetup.confirm())
                        if (Time.sleepUntil(() -> GrandExchange.getView() == GrandExchange.View.OVERVIEW, 1500))
                            has_item = true;
            } else {
                if (GrandExchange.getView() == GrandExchange.View.BUY_OFFER) {
                    if (GrandExchangeSetup.setItem(cosmic_rune_id)) {
                        Time.sleepUntil(() -> GrandExchangeSetup.getItemId() == cosmic_rune_id, 1500);
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
        return "Buying Cosmic rune.";
    }
}

