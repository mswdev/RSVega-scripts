package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker;

import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Combat;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.local.Health;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.ui.Log;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.framework.worker.WorkerHandler;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.ChargeOrbs;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.HopWorld;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.TeleportToEdgeville;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.WalkToObelisk;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank.*;

import java.util.Arrays;

public class AirOrbChargeWorkerHandler extends WorkerHandler {

    private final DrinkStamina drink_stamina;
    private final EatFood eat_food;
    private final EquipGlory equip_glory;
    private final EquipStaffOfAir equip_staff_of_air;
    private final WithdrawCosmicRunes withdraw_cosmic_runes;
    private final WithdrawUnpoweredOrbs withdraw_unpowered_orbs;
    private final ChargeOrbs charge_orbs;
    private final TeleportToEdgeville teleport_to_edgeville;
    private final WalkToObelisk walk_to_obelisk;
    private final HopWorld hop_world;
    private final DepositInventory deposit_inventory;

    public AirOrbChargeWorkerHandler(AirOrbChargeMission mission) {
        drink_stamina = new DrinkStamina();
        eat_food = new EatFood(mission);
        equip_glory = new EquipGlory(mission);
        equip_staff_of_air = new EquipStaffOfAir(mission);
        withdraw_cosmic_runes = new WithdrawCosmicRunes(mission);
        withdraw_unpowered_orbs = new WithdrawUnpoweredOrbs(mission);
        charge_orbs = new ChargeOrbs();
        teleport_to_edgeville = new TeleportToEdgeville(mission);
        walk_to_obelisk = new WalkToObelisk(mission);
        hop_world = new HopWorld();
        deposit_inventory = new DepositInventory();
    }

    @Override
    public Worker decide() {
        if (Combat.isAutoRetaliateOn())
            if (Combat.toggleAutoRetaliate(false))
                Time.sleepUntil(() -> !Combat.isAutoRetaliateOn(), 1500);

        if (hop_world.should_hop_world)
            return hop_world;

        if (EquipmentSlot.NECK.getItemName().equals("Amulet of glory") || !Equipment.isOccupied(EquipmentSlot.NECK))
            return equip_glory;

        if (isInEdgeville() && Health.getPercent() != 100)
            return eat_food;

        if (isInEdgeville() && !Equipment.contains(EquipStaffOfAir.ITEM_NAME))
            return equip_staff_of_air;

        if (isInEdgeville() && !Movement.isStaminaEnhancementActive() && !drink_stamina.out_of_stamina)
            return drink_stamina;

        if (isInEdgeville() && Inventory.getCount(true, WithdrawCosmicRunes.ITEM) < 81)
            return withdraw_cosmic_runes;

        if (isInEdgeville() && Inventory.getCount(WithdrawUnpoweredOrbs.UNPOWERED_ORB) != 27)
            return withdraw_unpowered_orbs;

        //todo Add this back in when getSkullIcon is fixed.
        Arrays.stream(Players.getLoaded()).filter(a -> a.getCombatLevel() >= Players.getLocal().getCombatLevel() - 7 && a.getCombatLevel() <= Players.getLocal().getCombatLevel() + 7 && a.getSkullIcon() > 0).findFirst().ifPresent(dangerous_player -> hop_world.should_hop_world = true);

        final Player attacking_player = Arrays.stream(Players.getLoaded()).filter(a -> a.getTarget() != null && a.getTarget().getName().equals(Players.getLocal().getName())).findFirst().orElse(null);
        if (attacking_player != null || Inventory.getCount(WithdrawUnpoweredOrbs.UNPOWERED_ORB) < 0 || Inventory.getCount(true, WithdrawCosmicRunes.ITEM) < 3)
            return teleport_to_edgeville;

        if (Inventory.getCount("Coins") > 0 || Inventory.getCount(WithdrawCosmicRunes.ITEM) >= 85) {
            Log.severe("We've somehow managed to take coins or more than 85 cosmic runes with us.");
            Log.severe("This could potentially be from restocking, don't worry!");
            Log.severe("Banking all items to ensure we do not accidentally lose these items.");
            return deposit_inventory;
        }

        if (SceneObjects.getNearest(ChargeOrbs.OBELISK) != null)
            return charge_orbs;

        return walk_to_obelisk;
    }

    private boolean isInEdgeville() {
        return BankLocation.EDGEVILLE.getPosition().distance() <= 30;
    }
}

