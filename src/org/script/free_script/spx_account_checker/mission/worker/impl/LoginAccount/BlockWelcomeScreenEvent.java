package org.script.free_script.spx_account_checker.mission.worker.impl.LoginAccount;

import org.api.game.pricechecking.PriceCheck;
import org.api.game.questing.Questing;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptBlockingEvent;
import org.rspeer.script.events.WelcomeScreen;
import org.script.free_script.spx_account_checker.data.Vars;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BlockWelcomeScreenEvent extends ScriptBlockingEvent {

    private final static int COINS_ID = 995;
    private final WelcomeScreen welcomeScreen;

    public BlockWelcomeScreenEvent(Script ctx) {
        super(ctx);
        welcomeScreen = new WelcomeScreen(ctx);
    }

    @Override
    public void process() {
        Vars.get().generalData.put("display_name", Players.getLocal().getName());
        Vars.get().generalData.put("last_check", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Vars.get().generalData.put("last_sign_in", String.valueOf(lastSignIn()));
        Vars.get().generalData.put("is_members", String.valueOf(isMembers()));
        Vars.get().generalData.put("is_bank_pin", String.valueOf(hasBankPin()));
        Vars.get().osrsData.put("last_ingame", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Vars.get().osrsData.put("is_tutorial", String.valueOf(isTutorial()));
        Vars.get().osrsData.put("position_x", Integer.toString(Players.getLocal().getX()));
        Vars.get().osrsData.put("position_y", Integer.toString(Players.getLocal().getY()));
        Vars.get().osrsData.put("position_z", Integer.toString(Players.getLocal().getFloorLevel()));
        invokeDataCollection();
        Vars.get().checkBank = hasBankPin() == 0 && isTutorial() == 0;
        Vars.get().checkAge = isTutorial() == 0;
        welcomeScreen.process();
    }

    @Override
    public boolean validate() {
        return welcomeScreen.validate() || (isTutorial() == 1 && !Vars.get().canLogout);
    }

    private void invokeDataCollection() {
        final Thread dataCollection = new Thread(() -> {
            Vars.get().osrsData.put("inventory_worth", inventoryWorth());
            //Vars.getCache().osrsData.put("equipment_worth", null);
            Vars.get().osrsData.put("level_total", String.valueOf(getTotalLevel()));
            Vars.get().osrsData.put("level_combat", String.valueOf(Players.getLocal().getCombatLevel()));
            Vars.get().osrsData.put("level_attack", String.valueOf(Skills.getLevel(Skill.ATTACK)));
            Vars.get().osrsData.put("level_defence", String.valueOf(Skills.getLevel(Skill.DEFENCE)));
            Vars.get().osrsData.put("level_strength", String.valueOf(Skills.getLevel(Skill.STRENGTH)));
            Vars.get().osrsData.put("level_hitpoints", String.valueOf(Skills.getLevel(Skill.HITPOINTS)));
            Vars.get().osrsData.put("level_ranged", String.valueOf(Skills.getLevel(Skill.RANGED)));
            Vars.get().osrsData.put("level_prayer", String.valueOf(Skills.getLevel(Skill.PRAYER)));
            Vars.get().osrsData.put("level_magic", String.valueOf(Skills.getLevel(Skill.MAGIC)));
            Vars.get().osrsData.put("level_cooking", String.valueOf(Skills.getLevel(Skill.COOKING)));
            Vars.get().osrsData.put("level_woodcutting", String.valueOf(Skills.getLevel(Skill.WOODCUTTING)));
            Vars.get().osrsData.put("level_fletching", String.valueOf(Skills.getLevel(Skill.FLETCHING)));
            Vars.get().osrsData.put("level_fishing", String.valueOf(Skills.getLevel(Skill.FISHING)));
            Vars.get().osrsData.put("level_firemaking", String.valueOf(Skills.getLevel(Skill.FIREMAKING)));
            Vars.get().osrsData.put("level_crafting", String.valueOf(Skills.getLevel(Skill.CRAFTING)));
            Vars.get().osrsData.put("level_smithing", String.valueOf(Skills.getLevel(Skill.SMITHING)));
            Vars.get().osrsData.put("level_mining", String.valueOf(Skills.getLevel(Skill.MINING)));
            Vars.get().osrsData.put("level_herblore", String.valueOf(Skills.getLevel(Skill.HERBLORE)));
            Vars.get().osrsData.put("level_agility", String.valueOf(Skills.getLevel(Skill.AGILITY)));
            Vars.get().osrsData.put("level_thieving", String.valueOf(Skills.getLevel(Skill.THIEVING)));
            Vars.get().osrsData.put("level_slayer", String.valueOf(Skills.getLevel(Skill.SLAYER)));
            Vars.get().osrsData.put("level_farming", String.valueOf(Skills.getLevel(Skill.FARMING)));
            Vars.get().osrsData.put("level_runecrafting", String.valueOf(Skills.getLevel(Skill.RUNECRAFTING)));
            Vars.get().osrsData.put("level_hunter", String.valueOf(Skills.getLevel(Skill.HUNTER)));
            Vars.get().osrsData.put("level_construction", String.valueOf(Skills.getLevel(Skill.CONSTRUCTION)));
            Vars.get().osrsData.put("quest_points", String.valueOf(Questing.getPoints()));
            Vars.get().osrsData.put("quests_complete", getCompletedQuests());
            Vars.get().canLogout = true;
        });
        dataCollection.start();
    }

    // [TODO - 2/27/2019]: Remove when getTotalLevel is fixed.
    private int getTotalLevel() {
        int total = 0;
        for (Skill skill : Skill.values()) {
            total += Skills.getLevel(skill);
        }

        return total;
    }

    private String inventoryWorth() {
        int total = 0;
        for (Item item : Inventory.getItems()) {
            if (item.getId() == COINS_ID) {
                total += item.getStackSize();
                continue;
            }

            try {
                total += PriceCheck.getOSBuddyPrice(item.getId()) * item.getStackSize();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return total > 0 ? String.valueOf(total) : "0";
    }

    private int isTutorial() {
        return Varps.get(281) < 1000 ? 1 : 0;
    }

    private int lastSignIn() {
        final InterfaceComponent lastSignIn = Interfaces.getFirst(a -> a.getText().contains("You last logged in"));
        if (lastSignIn == null)
            return 0;

        if (lastSignIn.getText().contains("earlier today"))
            return 1;

        return Integer.parseInt(lastSignIn.getText().replaceAll("[^0-9]", ""));
    }

    private int hasBankPin() {
        final InterfaceComponent bankPin = Interfaces.getFirst(a -> a.getText().contains("You do not have a Bank PIN."));
        return bankPin == null ? 1 : 0;
    }

    private int isMembers() {
        final InterfaceComponent members = Interfaces.getFirst(a -> a.getText().contains("You are not a member."));
        return members == null ? 1 : 0;
    }

    private String getCompletedQuests() {
        final StringBuilder quests = new StringBuilder();
        final int completedColor = 901389;
        final int questInterface = 399;
        final int freeQuestsInterface = 7;
        final int memberQuestsInterface = 8;
        final int miniQuestsInterface = 9;
        final InterfaceComponent freeQuests = Interfaces.getComponent(questInterface, freeQuestsInterface);
        final InterfaceComponent memberQuests = Interfaces.getComponent(questInterface, memberQuestsInterface);
        final InterfaceComponent miniQuests = Interfaces.getComponent(questInterface, miniQuestsInterface);
        if (freeQuests == null || memberQuests == null || miniQuests == null)
            return "";

        for (int i = 0; i < 21; i++) {
            final InterfaceComponent quest = freeQuests.getComponent(i);
            if (quest == null)
                continue;

            if (quest.getTextColor() != completedColor)
                continue;

            quests.append(quest.getText()).append(",");
        }
        for (int i = 0; i < 120; i++) {
            final InterfaceComponent quest = memberQuests.getComponent(i);
            if (quest == null)
                continue;

            if (quest.getTextColor() != completedColor)
                continue;

            quests.append(quest.getText()).append(",");
        }
        for (int i = 0; i < 13; i++) {
            final InterfaceComponent quest = miniQuests.getComponent(i);
            if (quest == null)
                continue;

            if (quest.getTextColor() != completedColor)
                continue;

            quests.append(quest.getText()).append(",");
        }

        return quests.toString();
    }
}

