package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker;

import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Combat;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.local.Health;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.api.framework.worker.Worker;
import sphiinx.api.framework.worker.WorkerHandler;
import sphiinx.api.game.Wilderness;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.ChargeOrbs;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.TeleportToEdgeville;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.WalkToObelisk;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank.*;

import java.util.Arrays;

public class AirOrbChargeWorkerHandler extends WorkerHandler<AirOrbChargeMission> {

    private final DrinkStamina DRINK_STAMINA;
    private final EatFood EAT_FOOD;
    private final EmptyInventory EMPTY_INVENTORY;
    private final EquipGlory EQUIP_GLORY;
    private final EquipStaffOfAir EQUIP_STAFF_OF_AIR;
    private final GetCosmicRunes GET_COSMIC_RUNES;
    private final GetFood GET_FOOD;
    private final GetGlory GET_GLORY;
    private final GetStaffOfAir GET_STAFF_OF_AIR;
    private final GetStamina GET_STAMINA;
    private final GetUnpoweredOrbs GET_UNPOWERED_ORBS;
    private final ChargeOrbs CHARGE_ORBS;
    private final TeleportToEdgeville TELEPORT_TO_EDGEVILLE;
    private final WalkToObelisk WALK_TO_OBELISK;

    public AirOrbChargeWorkerHandler(AirOrbChargeMission mission) {
        super(mission);
        DRINK_STAMINA = new DrinkStamina(mission);
        EAT_FOOD = new EatFood(mission);
        EMPTY_INVENTORY = new EmptyInventory(mission);
        EQUIP_GLORY = new EquipGlory(mission);
        EQUIP_STAFF_OF_AIR = new EquipStaffOfAir(mission);
        GET_COSMIC_RUNES = new GetCosmicRunes(mission);
        GET_FOOD = new GetFood(mission);
        GET_GLORY = new GetGlory(mission);
        GET_STAFF_OF_AIR = new GetStaffOfAir(mission);
        GET_STAMINA = new GetStamina(mission);
        GET_UNPOWERED_ORBS = new GetUnpoweredOrbs(mission);
        CHARGE_ORBS = new ChargeOrbs(mission);
        TELEPORT_TO_EDGEVILLE = new TeleportToEdgeville(mission);
        WALK_TO_OBELISK = new WalkToObelisk(mission);
    }

    @Override
    public Worker<AirOrbChargeMission> decide() {
        if (Combat.isAutoRetaliateOn())
            Combat.toggleAutoRetaliate(false);

        if (Wilderness.hasWildernessWarning())
            if (Wilderness.enterWilderness())
                Time.sleepUntil(() -> Players.getLocal().isMoving(), 1500);

        final Player ATTACKING_PLAYER = Arrays.stream(Players.getLoaded()).filter(a -> a.getTarget() != null && a.getTarget().getName().equals(Players.getLocal().getName())).findFirst().orElse(null);
        if (ATTACKING_PLAYER != null) {
            return TELEPORT_TO_EDGEVILLE;
        }

        if (!Equipment.contains("Staff of air")) {
            if (!Inventory.contains(GetStaffOfAir.STAFF_OF_AIR))
                return GET_STAFF_OF_AIR;

            return EQUIP_STAFF_OF_AIR;
        }

        if (EquipmentSlot.NECK.getItemName().equals("Amulet of glory") || !Equipment.isOccupied(EquipmentSlot.NECK)) {
            if (Inventory.isFull())
                return EMPTY_INVENTORY;

            if (!Inventory.contains(GetGlory.GLORY))
                return GET_GLORY;

            return EQUIP_GLORY;
        }

        if (Inventory.getCount(ChargeOrbs.AIR_ORB) == 27) {
            if (SceneObjects.getNearest(ChargeOrbs.OBELISK) != null)
                return TELEPORT_TO_EDGEVILLE;

            return EMPTY_INVENTORY;
        }

        if (Inventory.getCount(GetUnpoweredOrbs.UNPOWERED_ORB) > 0 && Inventory.getCount(true, GetCosmicRunes.COSMIC_RUNE) >= 3) {
            if (SceneObjects.getNearest(ChargeOrbs.OBELISK) != null)
                return CHARGE_ORBS;

            return WALK_TO_OBELISK;
        }

        if (Health.getPercent() != 100) {
            if (!Inventory.contains(GetFood.FOOD))
                return GET_FOOD;

            return EAT_FOOD;
        }

        if (!Movement.isStaminaEnhancementActive() && GetStamina.has_stamina) {
            if (!Inventory.contains(GetStamina.STAMINA))
                return GET_STAMINA;

            return DRINK_STAMINA;
        }

        if (!Inventory.isEmpty() && (Inventory.containsAnyExcept(GetCosmicRunes.COSMIC_RUNE) || !Inventory.containsAnyExcept(GetUnpoweredOrbs.UNPOWERED_ORB)))
            return EMPTY_INVENTORY;

        if (Inventory.getCount(true, GetCosmicRunes.COSMIC_RUNE) < 81)
            return GET_COSMIC_RUNES;

        if (!Inventory.contains(GetUnpoweredOrbs.UNPOWERED_ORB))
            return GET_UNPOWERED_ORBS;

        return null;
    }
}

