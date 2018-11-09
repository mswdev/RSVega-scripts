package sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl.buying;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.GrandExchangeSetup;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;
import sphiinx.script.public_script.spx_tutorial_island.api.game_util.BankCache;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.AirOrbRestockMission;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.AirOrbRestockWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl.OpenGrandExchange;

public class BuyLawRune extends AirOrbRestockWorker {

    public boolean has_item;
    private final int law_rune_id = 563;
    private final OpenGrandExchange open_grand_exchange;

    public BuyLawRune(AirOrbRestockMission mission) {
        super(mission);
        open_grand_exchange = new OpenGrandExchange(mission);
    }

    @Override
    public void work() {
        final int amount_in_bank = BankCache.getValue(law_rune_id);
        final int amount_to_buy = 9400 - amount_in_bank;
        if (amount_to_buy <= 0) {
            has_item = true;
            return;
        }

        if (GrandExchange.isOpen()) {
            if (GrandExchangeSetup.isOpen() && GrandExchangeSetup.getItemId() == law_rune_id) {
                if (GrandExchangeSetup.getQuantity() != amount_to_buy)
                    if (GrandExchangeSetup.setQuantity(amount_to_buy))
                        Time.sleepUntil(() -> GrandExchangeSetup.getQuantity() == amount_to_buy, 1500);

                if (GrandExchangeSetup.increasePrice(1))
                    if (GrandExchangeSetup.confirm())
                        if (Time.sleepUntil(() -> GrandExchange.getView() == GrandExchange.View.OVERVIEW, 1500))
                            has_item = true;
            } else {
                if (GrandExchange.getView() == GrandExchange.View.BUY_OFFER) {
                    if (GrandExchangeSetup.setItem(law_rune_id)) {
                        Time.sleepUntil(() -> GrandExchangeSetup.getItemId() == law_rune_id, 1500);
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
        return "Buying Law rune";
    }
}

