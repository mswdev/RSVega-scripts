package sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl.selling;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.GrandExchangeSetup;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.WithdrawItemWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.AirOrbRestockMission;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.AirOrbRestockWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl.OpenGrandExchange;

public class SellGlory extends AirOrbRestockWorker {

    public boolean has_listed_item;
    private final int amulet_of_glory_id = 1704;
    private final WithdrawItemWorker withdraw_amulet_of_glory;
    private final OpenGrandExchange open_grand_exchange;

    public SellGlory(AirOrbRestockMission mission) {
        super(mission);
        withdraw_amulet_of_glory = new WithdrawItemWorker<>(a -> a.getId() == amulet_of_glory_id, Bank.WithdrawMode.NOTE, 0);
        this.open_grand_exchange = new OpenGrandExchange(mission);
    }

    @Override
    public void work() {
        if (Inventory.contains(amulet_of_glory_id + 1)) {
            if (GrandExchange.isOpen()) {
                if (GrandExchangeSetup.isOpen() && GrandExchangeSetup.getItemId() == amulet_of_glory_id) {
                    if (GrandExchangeSetup.decreasePrice(2))
                        if (GrandExchangeSetup.confirm())
                            if (Time.sleepUntil(() -> GrandExchange.getView() == GrandExchange.View.OVERVIEW, 1500))
                                has_listed_item = true;
                } else {
                    if (GrandExchange.getView() == GrandExchange.View.SELL_OFFER) {
                        if (GrandExchangeSetup.setItem(amulet_of_glory_id + 1)) {
                            Time.sleepUntil(() -> GrandExchangeSetup.getItemId() == amulet_of_glory_id, 1500);
                        }
                    } else {
                        if (GrandExchange.createOffer(RSGrandExchangeOffer.Type.SELL))
                            Time.sleepUntil(GrandExchangeSetup::isOpen, 1500);
                    }
                }
            } else {
                open_grand_exchange.work();
            }
        } else {
            withdraw_amulet_of_glory.work();
            has_listed_item = withdraw_amulet_of_glory.itemNotFound();
        }
    }

    @Override
    public String toString() {
        return "Selling Amulet of glory";
    }
}

