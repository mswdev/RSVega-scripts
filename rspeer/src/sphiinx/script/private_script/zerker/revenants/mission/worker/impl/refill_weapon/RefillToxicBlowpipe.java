package sphiinx.script.private_script.zerker.revenants.mission.worker.impl.refill_weapon;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.script.private_script.zerker.revenants.Main;

import java.util.function.Predicate;

public class RefillToxicBlowpipe extends Worker {

    public static Predicate<Item> TOXIC_BLOWPIPE = a -> a.getName().contains("Toxic blowpipe");

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Equipment.isOccupied(EquipmentSlot.MAINHAND)) {
            if (Equipment.unequip(TOXIC_BLOWPIPE))
                Time.sleepUntil(() -> !Equipment.isOccupied(EquipmentSlot.MAINHAND), 1500);
        } else {
            final Item darts = Inventory.getFirst(WithdrawDarts.DARTS);
            if (darts != null)
                if (Inventory.use(TOXIC_BLOWPIPE, darts))
                    Time.sleepUntil(() -> !Inventory.contains(WithdrawDarts.DARTS), 1500);

            final Item zulrah_scales = Inventory.getFirst(WithdrawZulrahScales.ZULRAH_SCALES);
            if (zulrah_scales != null)
                if (Inventory.use(TOXIC_BLOWPIPE, zulrah_scales))
                    Time.sleepUntil(() -> !Inventory.contains(WithdrawZulrahScales.ZULRAH_SCALES), 1500);

            final Item toxic_blowpipe = Inventory.getFirst(TOXIC_BLOWPIPE);
            if (toxic_blowpipe == null)
                return;

            if (toxic_blowpipe.click())
                Time.sleepUntil(() -> Equipment.isOccupied(EquipmentSlot.MAINHAND), 1500);

            Main.ARGS.refill_toxic_blowpipe = false;
        }
    }

    @Override
    public String toString() {
        return "Refilling Toxic Blowpipe";
    }
}

