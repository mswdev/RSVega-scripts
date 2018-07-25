package sphiinx.script.public_script.spx_tutorial_island.data;

import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.TutorialComplete;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.at_end.*;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.at_start.HideRoofs;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.at_start.TurnOffAudio;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.at_start.TurnUpBrightness;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.at_start.ZoomOut;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.bank.ExitBankStage;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.bank.PollBooth;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.bank.ToOpenBank;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.brother_brace.*;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.combat_instructor.*;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.financial_advisor.ExitFinancialAdvisor;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.financial_advisor.FinancialAdvisorDialogue;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.magic_instructor.CastAirStrike;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.magic_instructor.MagicInstructorDialogue;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.magic_instructor.OpenMagicTab;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.master_chef.*;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.mining_instructor.*;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.quest_guide.*;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.rs_guide.CharacterDesign;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.rs_guide.ExitRSGuide;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.rs_guide.OpenOptionTab;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.rs_guide.RSGuideDialogue;
import sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.survival_expert.*;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

public class Stages {

    public final TutorialStage[] STAGES;

    public Stages(TI_Mission script) {
        STAGES = new TutorialStage[]{
                ts(new CharacterDesign(script), 0), ts(new TurnOffAudio(script), -1), ts(new HideRoofs(script), -1),
                ts(new TurnUpBrightness(script), -1), ts(new ZoomOut(script), -1), ts(new RSGuideDialogue(script), 0, 7),
                ts(new OpenOptionTab(script), 3), ts(new ExitRSGuide(script), 10),
                ts(new SurvivalExpertDialogue(script), 20, 70), ts(new OpenInventory(script), 30),
                ts(new ChopTree(script), 40, 50), ts(new MakeFire(script), 50),
                ts(new OpenSkillTab(script), 60), ts(new CatchShrimp(script), 80, 90),
                ts(new CookShrimp(script), 90, 100, 110), ts(new ExitSurvivalExpert(script), 120),
                ts(new EnterMasterChef(script), 130), ts(new MasterChefDialogue(script), 140),
                ts(new MakeDough(script), 150), ts(new CookDough(script), 160), ts(new OpenMusicTab(script), 170),
                ts(new ExitMasterChef(script), 180), ts(new OpenEmoteTab(script), 183),
                ts(new PerformEmote(script), 187), ts(new OpenOptionTab(script), 190),
                ts(new ClickRun(script), 200), ts(new EnterQuestGuide(script), 210),
                ts(new QuestGuideDialogue(script), 220, 240), ts(new OpenQuestTab(script), 230),
                ts(new ExitQuestGuide(script), 250), ts(new MiningInstructorDialogue(script), 260, 290, 330),
                ts(new ProspectTin(script), 270), ts(new ProspectCopper(script), 280), ts(new MineTin(script), 300),
                ts(new MineCopper(script), 310), ts(new SmeltOre(script), 320), ts(new SmithDagger(script), 340, 350),
                ts(new ExitMiningInstructor(script), 360), ts(new ExitRatCage(script), 470),
                ts(new CombatInstructorDialogue(script), 370, 410, 470), ts(new OpenEquipmentTab(script), 390),
                ts(new OpenEquipmentStats(script), 400), ts(new WieldDagger(script), 405),
                ts(new WieldSwordAndShield(script), 420), ts(new OpenCombatTab(script), 430), ts(new EnterRatCage(script), 440),
                ts(new MeleeRat(script), 450, 460), ts(new RangeRat(script), 480, 490),
                ts(new ExitCombatInstructor(script), 500), ts(new ToOpenBank(script), 510), ts(new PollBooth(script), 520),
                ts(new ExitBankStage(script), 525), ts(new FinancialAdvisorDialogue(script), 530), ts(new ExitFinancialAdvisor(script), 540),
                ts(new BrotherBraceDialogue(script), 550, 570, 600), ts(new OpenPrayerTab(script), 560),
                ts(new OpenFriendTab(script), 580), ts(new OpenIgnoreTab(script), 590), ts(new ExitBrotherBrace(script), 610),
                ts(new MagicInstructorDialogue(script), 620, 640, 670), ts(new OpenMagicTab(script), 630),
                ts(new CastAirStrike(script), 650, 660), ts(new Logout(script), 1000), ts(new DropItems(script), 1000),
                ts(new GetIronManItems(script), 1000), ts(new BankItems(script), 1000), ts(new WalkToLocation(script), 1000),
                ts(new StayLoggedIn(script), 1000), ts(new TutorialComplete(script), 1000)
        };
    }

    public TutorialStage getValid() {
        for (TutorialStage state : STAGES) {
            if (!state.isValid())
                continue;

            return state;
        }

        return null;
    }

    private TutorialStage ts(TI_Worker worker, int... vals) {
        return new TutorialStage(worker, vals);
    }
}
