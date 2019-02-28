package org.api.script.impl.mission.blast_furnace_mission.worker;

import org.api.script.framework.worker.Worker;
import org.api.script.framework.worker.WorkerHandler;
import org.api.script.impl.mission.blast_furnace_mission.BlastFurnaceMission;
import org.api.script.impl.mission.blast_furnace_mission.worker.impl.*;
import org.api.script.impl.mission.blast_furnace_mission.worker.impl.smelt.SmeltBars;
import org.api.script.impl.mission.blast_furnace_mission.worker.impl.smelt.WithdrawOre;
import org.api.script.impl.worker.banking.DepositWorker;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.Movement;

public class BlastFurnaceWorkerHandler extends WorkerHandler {

    private final PayCoffer pay_coffer;
    private final PayForeman pay_foreman;
    private final WithdrawCoalBag withdraw_coal_bag;
    private final EquipIceGloves equip_ice_gloves;
    private final UseWaterBucket use_water_bucket;
    private final CollectBars collect_bars;
    private final WithdrawOre withdraw_ore;
    private final SmeltBars smelt_bars;
    private final DrinkStamina drink_stamina;
    private final BlastFurnaceMission mission;

    public BlastFurnaceWorkerHandler(BlastFurnaceMission mission) {
        this.mission = mission;
        pay_coffer = new PayCoffer(mission);
        pay_foreman = new PayForeman(mission);
        withdraw_coal_bag = new WithdrawCoalBag(mission);
        equip_ice_gloves = new EquipIceGloves(mission);
        use_water_bucket = new UseWaterBucket();
        collect_bars = new CollectBars(mission);
        withdraw_ore = new WithdrawOre(mission);
        smelt_bars = new SmeltBars(mission);
        drink_stamina = new DrinkStamina();
    }

    @Override
    public Worker decide() {
        if (Skills.getLevel(Skill.SMITHING) < 60 && !pay_foreman.paid_foreman || PayForeman.shouldPayForeman())
            return pay_foreman;

        if (Varps.get(PayCoffer.COFFER_VARP) <= PayCoffer.COFFER_MIN)
            return pay_coffer;

        if (!Equipment.contains("Ice gloves"))
            return equip_ice_gloves;

        if (((!Movement.isStaminaEnhancementActive() && Bank.isOpen()) || Inventory.contains(DrinkStamina.stamina) || Inventory.contains(DrinkStamina.vial)) && !drink_stamina.out_of_stamina)
            return drink_stamina;

        if (Inventory.getFirst(WithdrawCoalBag.COAL_BAG) == null)
            return withdraw_coal_bag;

        /*if (Varps.get(CollectBars.COLLECT_BARS_VARP) > 0 && Varps.get(CollectBars.COLLECT_BARS_VARP) != CollectBars.COLLECT_BARS_COOLED_SETTING)
            return use_water_bucket;*/

        if (collect_bars.isDoneSmelting()) {
            if (Inventory.isFull())
                return new DepositWorker();

            return collect_bars;
        }

        if (!mission.is_smelting)
            return withdraw_ore;

        return smelt_bars;
    }
}

