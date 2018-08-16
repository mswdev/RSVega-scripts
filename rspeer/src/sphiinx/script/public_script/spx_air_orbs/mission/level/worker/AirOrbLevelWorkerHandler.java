package sphiinx.script.public_script.spx_air_orbs.mission.level.worker;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.*;
import org.rspeer.runetek.api.scene.Npcs;
import sphiinx.api.framework.worker.Worker;
import sphiinx.api.framework.worker.WorkerHandler;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.FightChicken;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.WalkToChickens;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.at_bank.EquipStaffOfAir;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.at_bank.GetLawRunes;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.at_bank.GetStaffOfAir;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.defence.level_to_30.EquipBronzeSword;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.defence.level_to_30.GetBronzeSword;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic.level_to_25.GetMindRunes;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic.level_to_45.CastVarrockTeleport;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic.level_to_45.GetFireRunes;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic.level_to_66.CastCamelotTeleport;

public class AirOrbLevelWorkerHandler extends WorkerHandler<AirOrbLevelMission> {

    private final GetStaffOfAir GET_STAFF_OF_AIR;
    private final EquipStaffOfAir EQUIP_STAFF_OF_AIR;
    private final GetMindRunes GET_MIND_RUNES;
    private final WalkToChickens WALK_TO_CHICKENS;
    private final FightChicken FIGHT_CHICKEN;
    private final GetFireRunes GET_FIRE_RUNES;
    private final GetLawRunes GET_LAW_RUNES;
    private final CastVarrockTeleport CAST_VARROCK_TELEPORT;
    private final CastCamelotTeleport CAST_CAMELOT_TELEPORT;
    private final GetBronzeSword GET_BRONZE_SWORD;
    private final EquipBronzeSword EQUIP_BRONZE_SWORD;

    public AirOrbLevelWorkerHandler(AirOrbLevelMission mission) {
        super(mission);
        GET_STAFF_OF_AIR = new GetStaffOfAir(mission);
        EQUIP_STAFF_OF_AIR = new EquipStaffOfAir(mission);
        GET_MIND_RUNES = new GetMindRunes(mission);
        WALK_TO_CHICKENS = new WalkToChickens(mission);
        FIGHT_CHICKEN = new FightChicken(mission);
        GET_FIRE_RUNES = new GetFireRunes(mission);
        GET_LAW_RUNES = new GetLawRunes(mission);
        CAST_VARROCK_TELEPORT = new CastVarrockTeleport(mission);
        CAST_CAMELOT_TELEPORT = new CastCamelotTeleport(mission);
        GET_BRONZE_SWORD = new GetBronzeSword(mission);
        EQUIP_BRONZE_SWORD = new EquipBronzeSword(mission);
    }

    @Override
    public Worker<AirOrbLevelMission> decide() {
        final int MAGIC_LEVEL = Skills.getLevel(Skill.MAGIC);
        if (MAGIC_LEVEL < 66) {
            if (Game.isLoggedIn() && !mission.has_loaded_magic) {
                Tabs.open(Tab.MAGIC);
                mission.has_loaded_magic = true;
            }

            if (!Equipment.contains("Staff of air")) {
                if (Inventory.contains(GetStaffOfAir.STAFF_OF_AIR)) {
                    return EQUIP_STAFF_OF_AIR;
                } else {
                    return GET_STAFF_OF_AIR;
                }
            }

            if (MAGIC_LEVEL < 25) {
                if (!Inventory.contains(GetMindRunes.MIND_RUNE))
                    return GET_MIND_RUNES;

                if (Npcs.getNearest(FightChicken.CHICKEN) == null)
                    return WALK_TO_CHICKENS;

                return FIGHT_CHICKEN;
            }

            if (MAGIC_LEVEL < 45) {
                if (!Inventory.contains(GetFireRunes.FIRE_RUNE))
                    return GET_FIRE_RUNES;

                if (!Inventory.contains(GetLawRunes.LAW_RUNE))
                    return GET_LAW_RUNES;

                return CAST_VARROCK_TELEPORT;
            }

            if (!Inventory.contains(GetLawRunes.LAW_RUNE))
                return GET_LAW_RUNES;

            return CAST_CAMELOT_TELEPORT;
        } else {
            final int DEFENCE_LEVEL = Skills.getLevel(Skill.DEFENCE);
            if (DEFENCE_LEVEL < 30) {
                if (!Equipment.contains("Bronze sword")) {
                    if (Inventory.contains(GetBronzeSword.BRONZE_SWORD)) {
                        return EQUIP_BRONZE_SWORD;
                    } else {
                        return GET_BRONZE_SWORD;
                    }
                }

                if (Combat.getSelectedStyle() != 3) {
                    Tabs.open(Tab.COMBAT);
                    if (Interfaces.getComponent(593, 15).click())
                        Time.sleepUntil(() -> Combat.getSelectedStyle() == 3, 1500);
                }

                if (Npcs.getNearest(FightChicken.CHICKEN) == null)
                    return WALK_TO_CHICKENS;

                return FIGHT_CHICKEN;
            }
        }

        return null;
    }
}

