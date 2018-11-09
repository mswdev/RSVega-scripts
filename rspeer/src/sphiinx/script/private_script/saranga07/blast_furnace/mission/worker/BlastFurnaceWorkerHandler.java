package sphiinx.script.private_script.saranga07.blast_furnace.mission.worker;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.WorkerHandler;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.BlastFurnaceMission;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.impl.*;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.impl.smelt.SmeltBars;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.impl.smelt.WithdrawOre;

public class BlastFurnaceWorkerHandler extends WorkerHandler<BlastFurnaceMission> {

    private final PayCoffer pay_coffer;
    private final PayForeman pay_foreman;
    private final WithdrawCoalBag withdraw_coal_bag;
    private final EquipIceGloves equip_ice_gloves;
    private final UseWaterBucket use_water_bucket;
    private final CollectBars collect_bars;
    private final WithdrawOre withdraw_ore;
    private final SmeltBars smelt_bars;
    private final DrinkStamina drink_stamina;

    public BlastFurnaceWorkerHandler(BlastFurnaceMission mission) {
        super(mission);
        pay_coffer = new PayCoffer(mission);
        pay_foreman = new PayForeman(mission);
        withdraw_coal_bag = new WithdrawCoalBag(mission);
        equip_ice_gloves = new EquipIceGloves(mission);
        use_water_bucket = new UseWaterBucket(mission);
        collect_bars = new CollectBars(mission);
        withdraw_ore = new WithdrawOre(mission);
        smelt_bars = new SmeltBars(mission);
        drink_stamina = new DrinkStamina(mission);
    }

    @Override
    public Worker<BlastFurnaceMission> decide() {
        if (!pay_foreman.paid_foreman || PayForeman.shouldPayForeman())
            return pay_foreman;

        if (pay_foreman.paid_foreman && Varps.get(PayCoffer.COFFER_VARP) <= PayCoffer.COFFER_MIN)
            return pay_coffer;

        if (!Equipment.contains("Ice gloves"))
            return equip_ice_gloves;

        if (Inventory.getFirst(WithdrawCoalBag.COAL_BAG) == null)
            return withdraw_coal_bag;

        // [TODO - 2018-10-26]: Remove this once the deposit worker is added.
        if (((!Movement.isStaminaEnhancementActive() && Bank.isOpen()) || Inventory.contains(DrinkStamina.stamina) || Inventory.contains(DrinkStamina.vial)) && !drink_stamina.out_of_stamina)
            return drink_stamina;

        /*if (Varps.get(CollectBars.COLLECT_BARS_VARP) > 0 && Varps.get(CollectBars.COLLECT_BARS_VARP) != CollectBars.COLLECT_BARS_COOLED_SETTING)
            return use_water_bucket;*/

        if (collect_bars.isDoneSmelting())
            return collect_bars;

        if (!mission.is_smelting)
            return withdraw_ore;

        return smelt_bars;
    }
}

