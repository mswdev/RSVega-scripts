package org.script.public_script.spx_account_checker.mission.worker.impl.LoginAccount;

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
import org.api.game.pricechecking.PriceCheck;
import org.api.game.questing.QuestingUtil;
import org.script.public_script.spx_account_checker.data.Vars;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BlockWelcomeScreenEvent extends ScriptBlockingEvent {

    private final WelcomeScreen welcome_screen;
    private final static int COINS_ID = 995;

    public BlockWelcomeScreenEvent(Script ctx) {
        super(ctx);
        welcome_screen = new WelcomeScreen(ctx);
    }

    @Override
    public void process() {
        Vars.get().general_data.put("display_name", Players.getLocal().getName());
        Vars.get().general_data.put("last_check", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Vars.get().general_data.put("last_sign_in", String.valueOf(lastSignIn()));
        Vars.get().general_data.put("is_members", String.valueOf(isMembers()));
        Vars.get().general_data.put("is_bank_pin", String.valueOf(hasBankPin()));
        Vars.get().osrs_data.put("last_ingame", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Vars.get().osrs_data.put("is_tutorial", String.valueOf(isTutorial()));
        Vars.get().osrs_data.put("position_x", Integer.toString(Players.getLocal().getX()));
        Vars.get().osrs_data.put("position_y", Integer.toString(Players.getLocal().getY()));
        Vars.get().osrs_data.put("position_z", Integer.toString(Players.getLocal().getFloorLevel()));
        invokeDataCollection();
        Vars.get().check_bank = hasBankPin() == 0 && isTutorial() == 0;
        Vars.get().check_age = isTutorial() == 0;
        welcome_screen.process();
    }

    @Override
    public boolean validate() {
        return welcome_screen.validate() || (isTutorial() == 1 && !Vars.get().can_logout);
    }

    private void invokeDataCollection() {
        final Thread data_collection = new Thread(() -> {
            Vars.get().osrs_data.put("inventory_worth", inventoryWorth());
            //Vars.get().osrs_data.put("equipment_worth", null);
            Vars.get().osrs_data.put("level_total", String.valueOf(getTotalLevel()));
            Vars.get().osrs_data.put("level_combat", String.valueOf(Players.getLocal().getCombatLevel()));
            Vars.get().osrs_data.put("level_attack", String.valueOf(Skills.getLevel(Skill.ATTACK)));
            Vars.get().osrs_data.put("level_defence", String.valueOf(Skills.getLevel(Skill.DEFENCE)));
            Vars.get().osrs_data.put("level_strength", String.valueOf(Skills.getLevel(Skill.STRENGTH)));
            Vars.get().osrs_data.put("level_hitpoints", String.valueOf(Skills.getLevel(Skill.HITPOINTS)));
            Vars.get().osrs_data.put("level_ranged", String.valueOf(Skills.getLevel(Skill.RANGED)));
            Vars.get().osrs_data.put("level_prayer", String.valueOf(Skills.getLevel(Skill.PRAYER)));
            Vars.get().osrs_data.put("level_magic", String.valueOf(Skills.getLevel(Skill.MAGIC)));
            Vars.get().osrs_data.put("level_cooking", String.valueOf(Skills.getLevel(Skill.COOKING)));
            Vars.get().osrs_data.put("level_woodcutting", String.valueOf(Skills.getLevel(Skill.WOODCUTTING)));
            Vars.get().osrs_data.put("level_fletching", String.valueOf(Skills.getLevel(Skill.FLETCHING)));
            Vars.get().osrs_data.put("level_fishing", String.valueOf(Skills.getLevel(Skill.FISHING)));
            Vars.get().osrs_data.put("level_firemaking", String.valueOf(Skills.getLevel(Skill.FIREMAKING)));
            Vars.get().osrs_data.put("level_crafting", String.valueOf(Skills.getLevel(Skill.CRAFTING)));
            Vars.get().osrs_data.put("level_smithing", String.valueOf(Skills.getLevel(Skill.SMITHING)));
            Vars.get().osrs_data.put("level_mining", String.valueOf(Skills.getLevel(Skill.MINING)));
            Vars.get().osrs_data.put("level_herblore", String.valueOf(Skills.getLevel(Skill.HERBLORE)));
            Vars.get().osrs_data.put("level_agility", String.valueOf(Skills.getLevel(Skill.AGILITY)));
            Vars.get().osrs_data.put("level_thieving", String.valueOf(Skills.getLevel(Skill.THIEVING)));
            Vars.get().osrs_data.put("level_slayer", String.valueOf(Skills.getLevel(Skill.SLAYER)));
            Vars.get().osrs_data.put("level_farming", String.valueOf(Skills.getLevel(Skill.FARMING)));
            Vars.get().osrs_data.put("level_runecrafting", String.valueOf(Skills.getLevel(Skill.RUNECRAFTING)));
            Vars.get().osrs_data.put("level_hunter", String.valueOf(Skills.getLevel(Skill.HUNTER)));
            Vars.get().osrs_data.put("level_construction", String.valueOf(Skills.getLevel(Skill.CONSTRUCTION)));
            Vars.get().osrs_data.put("quest_points", String.valueOf(QuestingUtil.getPoints()));
            Vars.get().osrs_data.put("quests_complete", getCompletedQuests());
            Vars.get().can_logout = true;
        });
        data_collection.start();
    }

    // [TODO - 2/27/2019]: Remove when getTotalLevel is fixed.
    private int getTotalLevel() {
        int total = 0;
        for (Skill skill : Skill.values()) {
            total += Skills.getLevel(skill);
        }

        return  total;
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
        final InterfaceComponent last_sign_in = Interfaces.getFirst(a -> a.getText().contains("You last logged in"));
        if (last_sign_in == null)
            return 0;

        if (last_sign_in.getText().contains("earlier today"))
            return 1;

        return Integer.parseInt(last_sign_in.getText().replaceAll("[^0-9]", ""));
    }

    private int hasBankPin() {
        final InterfaceComponent bank_pin = Interfaces.getFirst(a -> a.getText().contains("You do not have a Bank PIN."));
        return bank_pin == null ? 1 : 0;
    }

    private int isMembers() {
        final InterfaceComponent members = Interfaces.getFirst(a -> a.getText().contains("You are not a member."));
        return members == null ? 1 : 0;
    }

    private String getCompletedQuests() {
        final StringBuilder quests = new StringBuilder();
        final int completed_color = 901389;
        final int quest_interface = 399;
        final int free_quests_interface = 7;
        final int member_quests_interface = 8;
        final int mini_quests_interface = 9;
        final InterfaceComponent free_quests = Interfaces.getComponent(quest_interface, free_quests_interface);
        final InterfaceComponent member_quests = Interfaces.getComponent(quest_interface, member_quests_interface);
        final InterfaceComponent mini_quests = Interfaces.getComponent(quest_interface, mini_quests_interface);
        if (free_quests == null || member_quests == null || mini_quests == null)
            return "";

        for (int i = 0; i < 21; i++) {
            final InterfaceComponent quest = free_quests.getComponent(i);
            if (quest == null)
                continue;

            if (quest.getTextColor() != completed_color)
                continue;

            quests.append(quest.getText()).append(",");
        }
        for (int i = 0; i < 120; i++) {
            final InterfaceComponent quest = member_quests.getComponent(i);
            if (quest == null)
                continue;

            if (quest.getTextColor() != completed_color)
                continue;

            quests.append(quest.getText()).append(",");
        }
        for (int i = 0; i < 13; i++) {
            final InterfaceComponent quest = mini_quests.getComponent(i);
            if (quest == null)
                continue;

            if (quest.getTextColor() != completed_color)
                continue;

            quests.append(quest.getText()).append(",");
        }

        return quests.toString();
    }
}

