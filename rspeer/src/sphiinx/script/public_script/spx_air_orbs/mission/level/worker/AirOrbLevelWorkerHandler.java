package sphiinx.script.public_script.spx_air_orbs.mission.level.worker;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.*;
import org.rspeer.runetek.api.scene.Npcs;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.framework.worker.WorkerHandler;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.FightSeagulls;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.WalkToSeagulls;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.defence.level_to_30.EquipBronzeSword;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic.EquipStaffOfAir;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic.WithdrawLawRunes;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic.level_to_25.WithdrawMindRunes;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic.level_to_45.CastVarrockTeleport;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic.level_to_45.WithdrawFireRunes;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic.level_to_66.CastCamelotTeleport;

public class AirOrbLevelWorkerHandler extends WorkerHandler {

    private final EquipStaffOfAir equip_staff_of_air;
    private final WithdrawMindRunes withdraw_mind_runes;
    private final WalkToSeagulls walk_to_seagulls;
    private final FightSeagulls fight_seagulls;
    private final WithdrawFireRunes withdraw_fire_runes;
    private final WithdrawLawRunes withdraw_law_runes;
    private final CastVarrockTeleport cast_varrock_teleport;
    private final CastCamelotTeleport cast_camelot_teleport;
    private final EquipBronzeSword equip_bronze_sword;
    private final AirOrbLevelMission mission;

    public AirOrbLevelWorkerHandler(AirOrbLevelMission mission) {
        this.mission = mission;
        equip_staff_of_air = new EquipStaffOfAir(mission);
        withdraw_mind_runes = new WithdrawMindRunes(mission);
        walk_to_seagulls = new WalkToSeagulls();
        fight_seagulls = new FightSeagulls();
        withdraw_fire_runes = new WithdrawFireRunes(mission);
        withdraw_law_runes = new WithdrawLawRunes(mission);
        cast_varrock_teleport = new CastVarrockTeleport();
        cast_camelot_teleport = new CastCamelotTeleport();
        equip_bronze_sword = new EquipBronzeSword(mission);
    }

    @Override
    public Worker decide() {
        final int magic_level = Skills.getLevel(Skill.MAGIC);
        if (magic_level < 66) {
            //todo Temporary until ghost loading is added.
            if (Game.getState() == Game.STATE_CONNECTION_LOST)
                mission.has_loaded_magic = false;

            //todo Temporary until ghost loading is added.
            if (Game.isLoggedIn() && !mission.has_loaded_magic) {
                Tabs.open(Tab.MAGIC);
                mission.has_loaded_magic = true;
            }

            if (!Equipment.contains(EquipStaffOfAir.ITEM_NAME))
                return equip_staff_of_air;

            if (magic_level < 25) {
                if (!Inventory.contains(WithdrawMindRunes.ITEM))
                    return withdraw_mind_runes;

                return handleSeagulls();
            }

            if (!Inventory.contains(WithdrawLawRunes.ITEM))
                return withdraw_law_runes;

            if (magic_level < 45) {
                if (!Inventory.contains(WithdrawFireRunes.ITEM))
                    return withdraw_fire_runes;

                return cast_varrock_teleport;
            }

            return cast_camelot_teleport;
        }

        if (!Equipment.contains(EquipBronzeSword.ITEM_NAME))
            return equip_bronze_sword;

        if (Combat.getSelectedStyle() != 3) {
            //todo Temporary until selection is fixed
            Tabs.open(Tab.COMBAT);
            if (Interfaces.getComponent(593, 15).click())
                Time.sleepUntil(() -> Combat.getSelectedStyle() == 3, 1500);
        }

        return handleSeagulls();
    }

    private Worker handleSeagulls() {
        if (Npcs.getNearest(FightSeagulls.SEAGULL) == null)
            return walk_to_seagulls;

        return fight_seagulls;
    }
}

