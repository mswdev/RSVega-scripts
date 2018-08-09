package sphiinx.script.public_script.spx_account_checker.mission.worker.impl.LoginAccount;

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
import sphiinx.api.game.pricechecking.PriceChecking;
import sphiinx.script.public_script.spx_account_checker.data.Vars;

public class BlockWelcomeScreenEvent extends ScriptBlockingEvent {

    private final int MAIN_SCREEN_INTERFACE_ID = 378;
    private final int PLAY_SCREEN_INTERFACE_MEMBER_CHILD_ID = 74;
    private final int PLAY_SCREEN_INTERFACE_BANK_PIN_CHILD_ID = 70;
    private final int PLAY_SCREEN_INTERFACE_LAST_SIGNED_IN_CHILD_ID = 57;
    private final int COINS_ID = 995;

    static boolean successful_login;
    private final WelcomeScreen WELCOME_SCREEN;

    public BlockWelcomeScreenEvent(Script ctx) {
        super(ctx);
        WELCOME_SCREEN = new WelcomeScreen(ctx);
    }

    @Override
    public void process() {
        Vars.get().ACCOUNT_DATA.putIfAbsent("ign_username", Players.getLocal().getName());
        Vars.get().ACCOUNT_DATA.putIfAbsent("inventory_worth", inventoryWorth());
        Vars.get().ACCOUNT_DATA.putIfAbsent("equipment_worth", null);
        Vars.get().ACCOUNT_DATA.putIfAbsent("last_sign_in", String.valueOf(lastSignIn()));
        Vars.get().ACCOUNT_DATA.putIfAbsent("is_members", String.valueOf(isMembers()));
        Vars.get().ACCOUNT_DATA.putIfAbsent("is_oldschool", null);
        Vars.get().ACCOUNT_DATA.put("is_tutorial", String.valueOf(Varps.get(281) < 1000 ? 1 : 0));
        Vars.get().check_age = Varps.get(281) >= 1000;
        Vars.get().ACCOUNT_DATA.put("is_banned", null);
        Vars.get().ACCOUNT_DATA.put("is_locked", null);
        Vars.get().ACCOUNT_DATA.put("is_invalid", null);
        Vars.get().ACCOUNT_DATA.put("has_authenticator", null);
        Vars.get().ACCOUNT_DATA.put("has_bank_pin", String.valueOf(hasBankPin()));
        Vars.get().check_bank = hasBankPin() == 0;
        Vars.get().ACCOUNT_DATA.put("tile_position", Players.getLocal().getX() + ", " + Players.getLocal().getY() + ", " + Players.getLocal().getFloorLevel());
        Vars.get().ACCOUNT_DATA.put("level_combat", String.valueOf(Players.getLocal().getCombatLevel()));
        Vars.get().ACCOUNT_DATA.put("level_attack", String.valueOf(Skills.getLevel(Skill.ATTACK)));
        Vars.get().ACCOUNT_DATA.put("level_defence", String.valueOf(Skills.getLevel(Skill.DEFENCE)));
        Vars.get().ACCOUNT_DATA.put("level_strength", String.valueOf(Skills.getLevel(Skill.STRENGTH)));
        Vars.get().ACCOUNT_DATA.put("level_hitpoints", String.valueOf(Skills.getLevel(Skill.HITPOINTS)));
        Vars.get().ACCOUNT_DATA.put("level_ranged", String.valueOf(Skills.getLevel(Skill.RANGED)));
        Vars.get().ACCOUNT_DATA.put("level_prayer", String.valueOf(Skills.getLevel(Skill.PRAYER)));
        Vars.get().ACCOUNT_DATA.put("level_magic", String.valueOf(Skills.getLevel(Skill.MAGIC)));
        Vars.get().ACCOUNT_DATA.put("level_cooking", String.valueOf(Skills.getLevel(Skill.COOKING)));
        Vars.get().ACCOUNT_DATA.put("level_woodcutting", String.valueOf(Skills.getLevel(Skill.WOODCUTTING)));
        Vars.get().ACCOUNT_DATA.put("level_fletching", String.valueOf(Skills.getLevel(Skill.FLETCHING)));
        Vars.get().ACCOUNT_DATA.put("level_fishing", String.valueOf(Skills.getLevel(Skill.FISHING)));
        Vars.get().ACCOUNT_DATA.put("level_firemaking", String.valueOf(Skills.getLevel(Skill.FIREMAKING)));
        Vars.get().ACCOUNT_DATA.put("level_crafting", String.valueOf(Skills.getLevel(Skill.CRAFTING)));
        Vars.get().ACCOUNT_DATA.put("level_smithing", String.valueOf(Skills.getLevel(Skill.SMITHING)));
        Vars.get().ACCOUNT_DATA.put("level_mining", String.valueOf(Skills.getLevel(Skill.MINING)));
        Vars.get().ACCOUNT_DATA.put("level_herblore", String.valueOf(Skills.getLevel(Skill.HERBLORE)));
        Vars.get().ACCOUNT_DATA.put("level_agility", String.valueOf(Skills.getLevel(Skill.AGILITY)));
        Vars.get().ACCOUNT_DATA.put("level_thieving", String.valueOf(Skills.getLevel(Skill.THIEVING)));
        Vars.get().ACCOUNT_DATA.put("level_slayer", String.valueOf(Skills.getLevel(Skill.SLAYER)));
        Vars.get().ACCOUNT_DATA.put("level_farming", String.valueOf(Skills.getLevel(Skill.FARMING)));
        Vars.get().ACCOUNT_DATA.put("level_runecrafting", String.valueOf(Skills.getLevel(Skill.RUNECRAFTING)));
        Vars.get().ACCOUNT_DATA.put("level_hunter", String.valueOf(Skills.getLevel(Skill.HUNTER)));
        Vars.get().ACCOUNT_DATA.put("level_contruction", String.valueOf(Skills.getLevel(Skill.CONSTRUCTION)));
        successful_login = true;
        WELCOME_SCREEN.process();
    }

    @Override
    public boolean validate() {
        return WELCOME_SCREEN.validate();
    }

    private String inventoryWorth() {
        int total = 0;
        for (Item item : Inventory.getItems()) {
            if (item.getId() == COINS_ID) {
                total += item.getStackSize();
                continue;
            }

            total += PriceChecking.getRSPrice(item.getId()) * item.getStackSize();
        }

        return total > 0 ? String.valueOf(total) : null;
    }

    private int lastSignIn() {
        final InterfaceComponent LAST_SIGN_IN = Interfaces.getComponent(MAIN_SCREEN_INTERFACE_ID, PLAY_SCREEN_INTERFACE_LAST_SIGNED_IN_CHILD_ID);
        if (LAST_SIGN_IN == null)
            return -1;

        if (LAST_SIGN_IN.getText().contains("earlier today"))
            return 1;

        return Integer.parseInt(LAST_SIGN_IN.getText());
    }

    private int hasBankPin() {
        final InterfaceComponent BANK_PIN = Interfaces.getComponent(MAIN_SCREEN_INTERFACE_ID, PLAY_SCREEN_INTERFACE_BANK_PIN_CHILD_ID);
        if (BANK_PIN == null)
            return 0;

        return BANK_PIN.getText().contains("You do not have a Bank PIN.") ? 0 : 1;
    }

    private int isMembers() {
        final InterfaceComponent MEMBERS = Interfaces.getComponent(MAIN_SCREEN_INTERFACE_ID, PLAY_SCREEN_INTERFACE_MEMBER_CHILD_ID);
        if (MEMBERS == null)
            return 0;

        return MEMBERS.getText().contains("You are not a member.") ? 0 : 1;
    }

}

