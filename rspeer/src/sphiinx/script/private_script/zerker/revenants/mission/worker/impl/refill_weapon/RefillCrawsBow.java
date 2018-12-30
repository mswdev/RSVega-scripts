package sphiinx.script.private_script.zerker.revenants.mission.worker.impl.refill_weapon;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.script.private_script.zerker.revenants.Main;

import java.util.function.Predicate;

public class RefillCrawsBow extends Worker {

    public static Predicate<Item> CRAWS_BOW = a -> a.getName().contains("Craw's bow");

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Equipment.isOccupied(EquipmentSlot.MAINHAND)) {
            if (Equipment.unequip(CRAWS_BOW))
                Time.sleepUntil(() -> !Equipment.isOccupied(EquipmentSlot.MAINHAND), 1500);
        } else {
            final Item revenant_ether = Inventory.getFirst(WithdrawZulrahScales.ZULRAH_SCALES);
            if (revenant_ether != null)
                if (Inventory.use(CRAWS_BOW, revenant_ether))
                    Time.sleepUntil(() -> !Inventory.contains(WithdrawZulrahScales.ZULRAH_SCALES), 1500);

            final Item craws_bow = Inventory.getFirst(CRAWS_BOW);
            if (craws_bow == null)
                return;

            if (craws_bow.click())
                Time.sleepUntil(() -> Equipment.isOccupied(EquipmentSlot.MAINHAND), 1500);

            Main.ARGS.refill_craws_bow = false;
        }
    }

    @Override
    public String toString() {
        return "Refilling Craws bow.";
    }
}

