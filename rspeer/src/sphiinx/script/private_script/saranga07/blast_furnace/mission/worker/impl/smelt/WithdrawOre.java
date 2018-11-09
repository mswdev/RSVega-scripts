package sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.impl.smelt;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.WithdrawItemWorker;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.BlastFurnaceMission;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.BlastFurnaceWorker;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.impl.WithdrawCoalBag;

import java.util.function.Predicate;

public class WithdrawOre extends BlastFurnaceWorker {

    private static final int COAL_VARP = 547;
    private final int ingrediant_one_minimum;
    private final Predicate<Item> ingrediant_one;
    private final Predicate<Item> ingrediant_two;
    private final WithdrawItemWorker withdraw_ingrediant_one;
    private final WithdrawItemWorker withdraw_ingrediant_two;

    public WithdrawOre(BlastFurnaceMission mission) {
        super(mission);
        ingrediant_one_minimum = (mission.bar_type.ingrediant_one_amount / 2) * 27;
        ingrediant_one = a -> a.getName().equals(mission.bar_type.getIngrediantOne().getName());
        ingrediant_two = a -> a.getName().equals(mission.bar_type.getIngrediantTwo().getName());
        withdraw_ingrediant_one = new WithdrawItemWorker(ingrediant_one, Bank.WithdrawMode.ITEM, 0);
        withdraw_ingrediant_two = new WithdrawItemWorker(ingrediant_two, Bank.WithdrawMode.ITEM, 0);
    }

    @Override
    public void work() {
        if (mission.is_coal_bag_empty) {
            if (Inventory.contains(ingrediant_one)) {
                fillCoalBag();
            } else {
                withdrawIngrediantOne();
            }
        } else if (Varps.get(COAL_VARP) <= ingrediant_one_minimum - 27) {
            if (Inventory.contains(ingrediant_one)) {
                mission.is_smelting = true;
            } else {
                withdrawIngrediantOne();
            }
        } else {
            if (Inventory.contains(ingrediant_two)) {
                mission.is_smelting = true;
            } else {
                withdrawIngrediantTwo();
            }
        }
    }

    private void fillCoalBag() {
        final Item coal_bag = Inventory.getFirst(WithdrawCoalBag.COAL_BAG);
        if (coal_bag.click())
            if (Time.sleepUntil(Dialog::isOpen, 1500))
                mission.is_coal_bag_empty = false;
    }

    private void withdrawIngrediantOne() {
        withdraw_ingrediant_one.work();
        mission.can_end = withdraw_ingrediant_one.itemNotFound();
    }

    private void withdrawIngrediantTwo() {
        withdraw_ingrediant_two.work();
        mission.can_end = withdraw_ingrediant_two.itemNotFound();
    }

    @Override
    public String toString() {
        return "Withdrawing ore";
    }
}

