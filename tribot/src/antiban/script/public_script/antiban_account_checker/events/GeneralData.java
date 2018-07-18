package antiban.script.public_script.antiban_account_checker.events;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSTile;
import antiban.api.ClientSocketManager;
import antiban.api.game.timing.Waiting;
import antiban.api.util.Logging;
import antiban.priority_framework.PriorityEvent;
import antiban.script.public_script.antiban_account_checker.DataCollection;
import antiban.script.public_script.antiban_account_checker.data.Vars;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class GeneralData implements PriorityEvent {

    private final int PLAY_SCREEN_INTERFACE_ID = 378;

    private final int PLAY_SCREEN_INTERFACE_CHILD_ID = 79;

    private final int HANS_CHAT_INTERFACE_ID = 231;

    private final int HANS_CHAT_INTERFACE_CHILD_ID = 3;

    private final ClientSocketManager CLIENT_SOCKET_MANAGER;


    public GeneralData(ClientSocketManager client_socket_manager) {
        this.CLIENT_SOCKET_MANAGER = client_socket_manager;
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public boolean valid() {
        return Vars.get().collect_general_data || Vars.get().collect_bank_data || Vars.get().collect_hans_data;
    }

    @Override
    public void execute() {
        //Last Checked on server
        //Level total on server
        if (Vars.get().collect_general_data) {
            Vars.get().collected_data.put("username", DataCollection.getUsername());
            Vars.get().collected_data.put("password", DataCollection.getPassword());
            Vars.get().collected_data.put("inventory_worth", DataCollection.getInventoryWorth());
            Vars.get().collected_data.put("equipment_worth", DataCollection.getEquipmentWorth());
            Vars.get().collected_data.put("last_sign_in", DataCollection.lastSignIn());
            Vars.get().collected_data.put("is_members", String.valueOf(DataCollection.isMembers()));
            Vars.get().collected_data.put("is_oldschool", Vars.get().is_oldschool);
            Vars.get().collected_data.put("is_tutorial", String.valueOf(DataCollection.isTutorial()));
            Vars.get().collected_data.put("is_banned", String.valueOf(DataCollection.isBanned()));
            Vars.get().collected_data.put("is_locked", String.valueOf(DataCollection.isLocked()));
            Vars.get().collected_data.put("is_invalid", String.valueOf(DataCollection.isInvalid()));
            Vars.get().collected_data.put("has_authenticator", String.valueOf(DataCollection.hasAuthenticator()));
            Vars.get().collected_data.put("has_bank_pin", String.valueOf(DataCollection.hasBankPin()));
            Vars.get().collected_data.put("tile_position", String.valueOf(Player.getPosition()));
            Vars.get().collected_data.put("level_combat", String.valueOf(Player.getRSPlayer().getCombatLevel()));
            Vars.get().collected_data.put("level_attack", String.valueOf(Skills.SKILLS.ATTACK.getActualLevel()));
            Vars.get().collected_data.put("level_defence", String.valueOf(Skills.SKILLS.DEFENCE.getActualLevel()));
            Vars.get().collected_data.put("level_strength", String.valueOf(Skills.SKILLS.STRENGTH.getActualLevel()));
            Vars.get().collected_data.put("level_hitpoints", String.valueOf(Skills.SKILLS.HITPOINTS.getActualLevel()));
            Vars.get().collected_data.put("level_ranged", String.valueOf(Skills.SKILLS.RANGED.getActualLevel()));
            Vars.get().collected_data.put("level_prayer", String.valueOf(Skills.SKILLS.PRAYER.getActualLevel()));
            Vars.get().collected_data.put("level_magic", String.valueOf(Skills.SKILLS.MAGIC.getActualLevel()));
            Vars.get().collected_data.put("level_cooking", String.valueOf(Skills.SKILLS.COOKING.getActualLevel()));
            Vars.get().collected_data.put("level_woodcutting", String.valueOf(Skills.SKILLS.WOODCUTTING.getActualLevel()));
            Vars.get().collected_data.put("level_fletching", String.valueOf(Skills.SKILLS.FLETCHING.getActualLevel()));
            Vars.get().collected_data.put("level_fishing", String.valueOf(Skills.SKILLS.FISHING.getActualLevel()));
            Vars.get().collected_data.put("level_firemaking", String.valueOf(Skills.SKILLS.FIREMAKING.getActualLevel()));
            Vars.get().collected_data.put("level_crafting", String.valueOf(Skills.SKILLS.CRAFTING.getActualLevel()));
            Vars.get().collected_data.put("level_smithing", String.valueOf(Skills.SKILLS.SMITHING.getActualLevel()));
            Vars.get().collected_data.put("level_mining", String.valueOf(Skills.SKILLS.MINING.getActualLevel()));
            Vars.get().collected_data.put("level_herblore", String.valueOf(Skills.SKILLS.HERBLORE.getActualLevel()));
            Vars.get().collected_data.put("level_agility", String.valueOf(Skills.SKILLS.AGILITY.getActualLevel()));
            Vars.get().collected_data.put("level_thieving", String.valueOf(Skills.SKILLS.THIEVING.getActualLevel()));
            Vars.get().collected_data.put("level_slayer", String.valueOf(Skills.SKILLS.SLAYER.getActualLevel()));
            Vars.get().collected_data.put("level_farming", String.valueOf(Skills.SKILLS.FARMING.getActualLevel()));
            Vars.get().collected_data.put("level_runecrafting", String.valueOf(Skills.SKILLS.RUNECRAFTING.getActualLevel()));
            Vars.get().collected_data.put("level_hunter", String.valueOf(Skills.SKILLS.HUNTER.getActualLevel()));
            Vars.get().collected_data.put("level_contruction", String.valueOf(Skills.SKILLS.CONSTRUCTION.getActualLevel()));

            Vars.get().collect_general_data = false;
            if (DataCollection.isTutorial() <= 0 && (Login.getLoginState() == Login.STATE.INGAME || Game.getGameState() == 30)) {
                if (DataCollection.hasBankPin() <= 0) {
                    Vars.get().collect_bank_data = true;
                }
                Vars.get().collect_hans_data = true;
            }
        }

        if (isOnPlayScreen()) {
            if (clickPlay()) {
                Waiting.waitForCondition(() -> !isOnPlayScreen(), General.random(1000, 1200));
            }
        } else {
            if (Vars.get().collect_bank_data) {
                final String BANK_WORTH = getBankWorth();
                if (BANK_WORTH == null)
                    return;

                Vars.get().collected_data.put("bank_worth", BANK_WORTH);
                Vars.get().collect_bank_data = false;
            }

            if (Vars.get().collect_hans_data) {
                final int ACCOUNT_AGE = getAgeFromHans();
                if (ACCOUNT_AGE <= 0)
                    return;

                Vars.get().collected_data.put("account_age", String.valueOf(ACCOUNT_AGE));
                Vars.get().collect_hans_data = false;
            }
        }

        if (!Vars.get().collect_general_data && !Vars.get().collect_bank_data && !Vars.get().collect_hans_data) {
            try {
                final ObjectOutputStream OUTPUT_STREAM = new ObjectOutputStream(CLIENT_SOCKET_MANAGER.getSocket().getOutputStream());
                OUTPUT_STREAM.writeObject(Vars.get().collected_data);
                Logging.clientDebug("Data written to socket server.");
            } catch (IOException e) {
                e.printStackTrace();
            }

            Vars.get().collected_data.clear();
        }

    }

    private String getBankWorth() {
        if (Banking.isBankScreenOpen()) {
            return DataCollection.getBankWorth();
        } else {
            if (Banking.isInBank()) {
                if (Banking.openBank())
                    Waiting.waitForCondition(Banking::isBankScreenOpen, General.random(1000, 1200));
            } else {
                WebWalking.walkToBank(new Condition() {
                    @Override
                    public boolean active() {
                        return Banking.isInBank();
                    }
                }, 250);
            }
        }

        return null;
    }

    private int getAgeFromHans() {
        if (NPCChat.getName() != null && NPCChat.getName().equals("Hans")) {
            final RSInterface HANS_CHAT = Interfaces.get(HANS_CHAT_INTERFACE_ID);
            if (HANS_CHAT == null)
                return -1;

            final RSInterface HANS_CHILD_CHAT = HANS_CHAT.getChild(HANS_CHAT_INTERFACE_CHILD_ID);
            if (HANS_CHILD_CHAT == null)
                return -1;

            final String[] UNFORMATTED_TEXT = HANS_CHILD_CHAT.getText().split("arrived ");
            final String[] FORMATTED_TEXT = UNFORMATTED_TEXT[1].split(" days");
            return Integer.parseInt(FORMATTED_TEXT[0]);
        } else {
            final RSNPC[] HANS = NPCs.find("Hans");
            if (HANS.length <= 0) {
                final RSTile LUMBRIDGE = new RSTile(3218, 3228, 0);
                WebWalking.walkTo(LUMBRIDGE, new Condition() {
                    @Override
                    public boolean active() {
                        return NPCs.find("Hans").length >= 1 || LUMBRIDGE.getPosition().distanceTo(Player.getRSPlayer()) <= 5;
                    }
                }, 150);
            } else {
                if (HANS[0].isOnScreen()) {
                    if (Clicking.click("Age", HANS[0])) {
                        Waiting.waitForCondition(() -> NPCChat.getName() != null && NPCChat.getName().equals("Hans"), General.random(3000, 3500));
                    }
                } else {
                    WebWalking.walkTo(HANS[0].getPosition(), new Condition() {
                        @Override
                        public boolean active() {
                            return HANS[0].isOnScreen();
                        }
                    }, 150);
                }
            }
        }

        return -1;
    }

    private boolean isOnPlayScreen() {
        final RSInterface PLAY_SCREEN = Interfaces.get(PLAY_SCREEN_INTERFACE_ID);
        if (PLAY_SCREEN == null)
            return false;

        final RSInterface PLAY_BUTTON = PLAY_SCREEN.getChild(PLAY_SCREEN_INTERFACE_CHILD_ID);
        return PLAY_BUTTON != null;
    }

    private boolean clickPlay() {
        final RSInterface PLAY_SCREEN = Interfaces.get(PLAY_SCREEN_INTERFACE_ID);
        if (PLAY_SCREEN == null)
            return false;

        final RSInterface PLAY_BUTTON = PLAY_SCREEN.getChild(PLAY_SCREEN_INTERFACE_CHILD_ID);
        if (PLAY_BUTTON == null)
            return false;

        return PLAY_BUTTON.click("Play");
    }


}
