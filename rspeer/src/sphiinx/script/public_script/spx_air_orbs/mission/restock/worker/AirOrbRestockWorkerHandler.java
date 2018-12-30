package sphiinx.script.public_script.spx_air_orbs.mission.restock.worker;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;
import org.rspeer.ui.Log;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.framework.worker.WorkerHandler;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.AirOrbRestockMission;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl.DepositInventory;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl.WithdrawCoins;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl.buying.*;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl.selling.SellAirOrb;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl.selling.SellGlory;

public class AirOrbRestockWorkerHandler extends WorkerHandler {


    private final SellAirOrb sell_air_orb;
    private final SellGlory sell_glory;
    private final BuyUnpoweredOrb buy_unpowered_orb;
    private final BuyGlory buy_glory;
    private final BuyStamina buy_stamina;
    private final BuyCosmicRune buy_cosmic_rune;
    private final BuyFood buy_food;
    private final BuyStaffOfAir buy_staff_of_air;
    private final BuyMindRune buy_mind_rune;
    private final BuyFireRune buy_fire_rune;
    private final BuyLawRune buy_law_rune;
    private final WithdrawCoins withdraw_coins;
    private final DepositInventory deposit_inventory;
    private final AirOrbRestockMission mission;

    public AirOrbRestockWorkerHandler(AirOrbRestockMission mission) {
        this.mission = mission;
        sell_air_orb = new SellAirOrb();
        sell_glory = new SellGlory();
        buy_unpowered_orb = new BuyUnpoweredOrb();
        buy_glory = new BuyGlory();
        buy_stamina = new BuyStamina();
        buy_cosmic_rune = new BuyCosmicRune();
        buy_food = new BuyFood();
        buy_staff_of_air = new BuyStaffOfAir();
        buy_mind_rune = new BuyMindRune();
        buy_fire_rune = new BuyFireRune();
        buy_law_rune = new BuyLawRune();
        withdraw_coins = new WithdrawCoins();
        deposit_inventory = new DepositInventory();
    }

    @Override
    public Worker decide() {
        if (GrandExchange.isOpen() && GrandExchange.getOffers(a -> a.getProgress() == RSGrandExchangeOffer.Progress.IN_PROGRESS).length > 0) {
            Log.fine("Waiting for items to buy/sell");
            return null;
        }

        if (GrandExchange.getOffers(a -> a.getProgress() == RSGrandExchangeOffer.Progress.FINISHED).length > 0) {
            if (GrandExchange.collectAll())
                Time.sleepUntil(() -> GrandExchange.getOffers(a -> a.getProgress() == RSGrandExchangeOffer.Progress.FINISHED).length <= 0, 1500);
        }

        if (!withdraw_coins.has_coins)
            return withdraw_coins;

        if (!sell_air_orb.has_listed_item)
            return sell_air_orb;

        if (!sell_glory.has_listed_item)
            return sell_glory;

        if (!buy_staff_of_air.has_item)
            return buy_staff_of_air;

        if (!buy_food.has_item)
            return buy_food;

        if (Skills.getLevel(Skill.MAGIC) < 66) {
            if (!buy_mind_rune.has_item)
                return buy_mind_rune;

            if (!buy_fire_rune.has_item)
                return buy_fire_rune;

            if (!buy_law_rune.has_item)
                return buy_law_rune;
        }

        if (!buy_unpowered_orb.has_item)
            return buy_unpowered_orb;

        if (!buy_glory.has_item)
            return buy_glory;

        if (!buy_stamina.has_item)
            return buy_stamina;

        if (!buy_cosmic_rune.has_item)
            return buy_cosmic_rune;

        if (GrandExchange.getOffers(RSGrandExchangeOffer.Type.BUY).length > 0)
            return null;

        if (Inventory.getFreeSlots() != 28)
            return deposit_inventory;

        mission.can_end = true;
        return null;
    }
}

