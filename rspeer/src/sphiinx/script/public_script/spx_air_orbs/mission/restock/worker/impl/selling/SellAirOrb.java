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

public class SellAirOrb extends AirOrbRestockWorker {

    public boolean has_listed_item;
    private final int air_orb_id = 573;
    private final WithdrawItemWorker withdraw_air_orb;
    private final OpenGrandExchange open_grand_exchange;

    public SellAirOrb(AirOrbRestockMission mission) {
        super(mission);
        withdraw_air_orb = new WithdrawItemWorker<>(a -> a.getId() == air_orb_id, Bank.WithdrawMode.NOTE, 0);
        open_grand_exchange = new OpenGrandExchange(mission);
    }

    @Override
    public void work() {
        if (Inventory.contains(air_orb_id + 1)) {
            if (GrandExchange.isOpen()) {
                if (GrandExchangeSetup.isOpen() && GrandExchangeSetup.getItemId() == air_orb_id) {
                    if (GrandExchangeSetup.decreasePrice(1))
                        if (GrandExchangeSetup.confirm())
                            if (Time.sleepUntil(() -> GrandExchange.getView() == GrandExchange.View.OVERVIEW, 1500))
                                has_listed_item = true;
                } else {
                    if (GrandExchange.getView() == GrandExchange.View.SELL_OFFER) {
                        if (GrandExchangeSetup.setItem(air_orb_id + 1)) {
                            Time.sleepUntil(() -> GrandExchangeSetup.getItemId() == air_orb_id, 1500);
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
            withdraw_air_orb.work();
            has_listed_item = withdraw_air_orb.itemNotFound();
        }
    }

    @Override
    public String toString() {
        return "Selling Air orb";
    }
}

