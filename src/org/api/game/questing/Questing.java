package org.api.game.questing;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.Interfaces;

import java.util.Arrays;

public class Questing {

    private static final int QUEST_INTERFACE = 399;
    private static final int QUEST_FREE_INTERFACE = 6;
    private static final int QUEST_MEMBER_INTERFACE = 7;
    private static final int QUEST_MINI_INTERFACE = 8;
    private static final int COMPLETED_QUEST_COLOR = 901389;

    /**
     * Gets the players total quest points.
     *
     * @return The players total quest points.
     */
    public static int getPoints() {
        return Varps.get(101);
    }


    /**
     * Gets a string of the players completed quests.
     *
     * @return A string of the players completed quests.
     */
    public static String getCompletedQuests() {
        final StringBuilder string_builder = new StringBuilder();
        final InterfaceComponent[] free_quests = Interfaces.getComponent(QUEST_INTERFACE, QUEST_FREE_INTERFACE).getComponents(a -> a.getTextColor() == COMPLETED_QUEST_COLOR);
        Arrays.stream(free_quests).forEach(a -> string_builder.append(a.getText()).append(", "));

        final InterfaceComponent[] member_quests = Interfaces.getComponent(QUEST_INTERFACE, QUEST_MEMBER_INTERFACE).getComponents(a -> a.getTextColor() == COMPLETED_QUEST_COLOR);
        Arrays.stream(member_quests).forEach(a -> string_builder.append(a.getText()).append(", "));

        final InterfaceComponent[] mini_quests = Interfaces.getComponent(QUEST_INTERFACE, QUEST_MINI_INTERFACE).getComponents(a -> a.getTextColor() == COMPLETED_QUEST_COLOR);
        Arrays.stream(mini_quests).forEach(a -> string_builder.append(a.getText()).append(", "));

        return string_builder.toString().replaceAll(", $", "");
    }
}

