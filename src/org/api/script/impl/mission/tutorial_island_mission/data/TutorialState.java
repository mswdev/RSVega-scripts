package org.api.script.impl.mission.tutorial_island_mission.data;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.tutorial_island_mission.TutorialIslandMission;
import org.api.script.impl.mission.tutorial_island_mission.worker.impl.HintWorker;
import org.api.script.impl.mission.tutorial_island_mission.worker.impl.OpenTab;
import org.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.account_guide.PollBooth;
import org.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.combat_instructor.FightRat;
import org.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.combat_instructor.OpenEquipmentStats;
import org.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.magic_instructor.CastAirStrike;
import org.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.master_chef.MakeDough;
import org.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.mining_instructor.SmithDagger;
import org.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.mining_instructor.TempSmeltOreWorker;
import org.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.survival_expert.CatchShrimp;
import org.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.survival_expert.ChopTree;
import org.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.survival_expert.CookShrimp;
import org.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.survival_expert.LightFire;
import org.api.script.impl.worker.DialogueWorker;
import org.api.script.impl.worker.MovementWorker;
import org.api.script.impl.worker.interactables.ItemWorker;
import org.api.script.impl.worker.interactables.NpcWorker;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.movement.position.Position;

import java.util.Arrays;

public enum TutorialState {

    //Gielinor Guide
    CHARACTER_DESIGN(null, 1),
    GIELINOR_GUIDE_DIALOGUE(new NpcWorker(a -> a.getName().equals("Gielinor Guide")), 2, 7),
    OPEN_OPTION_TAB(new OpenTab(Tab.OPTIONS), 3),

    //Survival Expert
    ENTER_SURVIVAL_EXPERT(new HintWorker(), 10),
    SURVIVAL_EXPERT_DIALOGUE(new NpcWorker(a -> a.getName().equals("Survival Expert")), 20, 60),
    OPEN_INVENTORY_TAB(new OpenTab(Tab.INVENTORY), 30),
    CATCH_SHRIMP(new CatchShrimp(), 40),
    OPEN_SKILL_TAB(new OpenTab(Tab.SKILLS), 50),
    CHOP_TREE(new ChopTree(), 70),
    LIGHT_FIRE(new LightFire(), 80),
    COOK_SHRIMP(new CookShrimp(), 90),

    //Master Chef
    ENTER_MASTER_CHEF(new HintWorker(), 120, 130),
    MASTER_CHEF_DIALOGUE(new NpcWorker(a -> a.getName().equals("Master Chef")), 140),
    MAKE_DOUGH(new MakeDough(), 150),
    COOK_DOUGH(new HintWorker(), 160),

    //Quest Guide
    ENTER_QUEST_GUIDE(new HintWorker(), 170),
    QUEST_GUIDE_DIALOGUE(new NpcWorker(a -> a.getName().equals("Quest Guide"), new MovementWorker(new Position(3086, 3123, 0))), 200, 210, 220, 240),
    OPEN_QUEST_TAB(new OpenTab(Tab.QUEST_LIST), 230),

    //Mining Instructor
    ENTER_MINING_INSTRUCTOR(new HintWorker(), 250),
    MINING_INSTRUCTOR_DIALOGUE(new NpcWorker(a -> a.getName().equals("Mining Instructor"), new MovementWorker(new Position(3080, 9505, 0))), 260, 270, 330),
    MINE_TIN(new HintWorker(), 300),
    MINE_COPPER(new HintWorker(), 310),
    /*SMELT_ORE(new HintWorker(), 320),*/
    SMELT_ORE(new TempSmeltOreWorker(), 320),
    OPEN_ANVIL(new HintWorker(), 340),
    SMITH_DAGGER(new SmithDagger(), 350),

    //Combat Instructor
    ENTER_COMBAT_INSTRUCTOR(new HintWorker(), 360),
    COMBAT_INSTRUCTOR_DIALOGUE(new NpcWorker(a -> a.getName().equals("Combat Instructor")), 370, 410, 470),
    OPEN_EQUIPMENT_TAB(new OpenTab(Tab.EQUIPMENT), 390),
    OPEN_EQUIPMENT_STATS(new OpenEquipmentStats(), 400),
    EQUIP_DAGGER(new ItemWorker(a -> a.getName().equals("Bronze dagger")), 405),
    EQUIP_SWORD_AND_SHEILD(new ItemWorker(a -> a.getName().equals("Bronze sword") || a.getName().equals("Wooden shield")), 420),
    OPEN_COMBAT_TAB(new OpenTab(Tab.COMBAT), 430),
    MELEE_RAT(new FightRat(), 440, 450, 460),
    RANGE_RAT(new FightRat(), 480, 490),

    //Account Guide
    ENTER_ACCOUNT_GUIDE(new HintWorker(), 500),
    OPEN_BANK(new HintWorker(), 510),
    POLL_BOOTH(new PollBooth(), 520),
    ACCOUNT_GUIDE_DIALOGUE(new NpcWorker(a -> a.getName().equals("Account Guide")), 525, 530, 532),
    OPEN_ACCOUNT_MANAGEMENT_TAB(new OpenTab(Tab.ACCOUNT_MANAGEMENT), 531),

    //Brother Brace
    ENTER_BROTHER_BRACE(new HintWorker(), 540),
    BROTHER_BRACE_DIALOGUE(new NpcWorker(a -> a.getName().equals("Brother Brace"), new MovementWorker(new Position(3127, 3107, 0))), 550, 570, 600),
    OPEN_PRAYER_TAB(new OpenTab(Tab.PRAYER), 560),
    OPEN_FRIEND_TAB(new OpenTab(Tab.FRIENDS_LIST), 580),

    //Magic Instructor
    ENTER_MAGIC_INSTRUCTOR(new HintWorker(), 610),
    MAGIC_INSTRUCTOR_DIALOGUE(new NpcWorker(a -> a.getName().equals("Magic Instructor"), new DialogueWorker(a -> a.equals("Yes.") || a.equals("No, I'm not planning to do that.")), new MovementWorker(new Position(3141, 3087, 0))), 620, 640, 670),
    OPEN_MAGIC_TAB(new OpenTab(Tab.MAGIC), 630),
    CAST_AIR_STRIKE(new CastAirStrike(), 650),

    //Tutorial Island Complete
    COMPLETE(null, 1000);

    private final Worker worker;
    private final int[] varps;

    TutorialState(Worker worker, int... varps) {
        this.varps = varps;
        this.worker = worker;
    }

    public static TutorialState getValidState() {
        return Arrays.stream(values()).filter(TutorialState::isInVarp).findFirst().orElse(null);
    }

    public static boolean isInVarp(TutorialState state) {
        return Arrays.stream(state.getVarps()).anyMatch(a -> a == Varps.get(TutorialIslandMission.TUTORIAL_ISLAND_VARP));
    }

    public Worker getWorker() {
        return worker;
    }

    public int[] getVarps() {
        return varps;
    }
}
