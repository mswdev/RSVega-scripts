package sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl.buying;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.GrandExchangeSetup;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl.OpenGrandExchange;

public class BuyStamina extends Worker {

    public boolean has_item;
    private final int stamina_potion_id = 12625;
    private final OpenGrandExchange open_grand_exchange = new OpenGrandExchange();

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        final int amount_in_bank = 0;//(int) Math.round((BankCache.getValue(12625) * 4) + (BankCache.getValue(12627) * 3) + (BankCache.getValue(12629) * 2) + BankCache.getValue(12631) / 4.0);
        final int amount_to_buy = 60 - amount_in_bank;
        if (amount_to_buy <= 0) {
            has_item = true;
            return;
        }

        if (GrandExchange.isOpen()) {
            if (GrandExchangeSetup.isOpen() && GrandExchangeSetup.getItemId() == stamina_potion_id) {
                if (GrandExchangeSetup.getQuantity() != amount_to_buy)
                    if (GrandExchangeSetup.setQuantity(amount_to_buy))
                        Time.sleepUntil(() -> GrandExchangeSetup.getQuantity() == amount_to_buy, 1500);

                if (GrandExchangeSetup.increasePrice(3))
                    if (GrandExchangeSetup.confirm())
                        if (Time.sleepUntil(() -> GrandExchange.getView() == GrandExchange.View.OVERVIEW, 1500))
                            has_item = true;
            } else {
                if (GrandExchange.getView() == GrandExchange.View.BUY_OFFER) {
                    if (GrandExchangeSetup.setItem(stamina_potion_id)) {
                        Time.sleepUntil(() -> GrandExchangeSetup.getItemId() == stamina_potion_id, 1500);
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
        return "Buying Stamina potion.";
    }
}

