package org.api.script.impl.mission.questing.restless_ghost_mission.data;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.questing.restless_ghost_mission.RestlessGhostMission;
import org.api.script.impl.mission.questing.restless_ghost_mission.worker.impl.PlaceSkullWorker;
import org.api.script.impl.worker.DialogueWorker;
import org.api.script.impl.worker.MovementWorker;
import org.api.script.impl.worker.banking.DepositWorker;
import org.api.script.impl.worker.interactables.ItemWorker;
import org.api.script.impl.worker.interactables.NpcWorker;
import org.api.script.impl.worker.interactables.SceneObjectWorker;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;

import java.util.Arrays;
import java.util.function.BooleanSupplier;

public enum RestlessGhostState {

    DEPOSIT_INVENTORY(new DepositWorker(), () -> Inventory.getFreeSlots() < 2, 0),

    TALK_TO_FATHER_AERECK(new NpcWorker(a -> a.getName().equals("Father Aereck"), new DialogueWorker(a -> a.equals("I'm looking for a quest!") || a.equals("Ok, let me help then.")), new MovementWorker(new Position(3242, 3207, 0))), null, 0),

    GET_GHOST_SPEAK_AMULET(new NpcWorker(a -> a.getName().equals("Father Urhney"), new DialogueWorker(a -> a.equals("Father Aereck sent me to talk to you.") || a.equals("He's got a ghost haunting his graveyard.") || a.equals("I've lost the Amulet of Ghostspeak.")), new MovementWorker(new Position(3148, 3175, 0))), null, 1, 2),

    WEAR_GHOST_SPEAK_AMULET(new ItemWorker(a -> a.getName().equals("Ghostspeak amulet")), () -> Inventory.contains("Ghostspeak amulet") && !Equipment.contains("Ghostspeak amulet"), 2),

    SEARCH_COFFIN(new SceneObjectWorker(a -> a.getName().equals("Coffin"), a -> a.equals("Search"), new MovementWorker(new Position(3248, 3192, 0))), () -> Equipment.contains("Ghostspeak amulet") && Npcs.getFirst(a -> a.getName().equals("Restless ghost")) == null, 2, 4),

    TALK_TO_RESTLESS_GHOST(new NpcWorker(a -> a.getName().equals("Restless ghost"), new DialogueWorker(a -> a.equals("Yep, now tell me what the problem is."))), () -> Equipment.contains("Ghostspeak amulet"), 2),

    GET_SKULL(new SceneObjectWorker(a -> a.getName().equals("Altar") && a.containsAction("Search"), new MovementWorker(new Position(3119, 9566, 0))), null, 3, 4),

    OPEN_COFFIN(new SceneObjectWorker(a -> a.getName().equals("Coffin"), a -> a.equals("Open"), new MovementWorker(new Position(3248, 3192, 0))), () -> Inventory.contains("Ghost's skull") && Npcs.getFirst(a -> a.getName().equals("Restless ghost")) == null, 4),

    PLACE_SKULL(new PlaceSkullWorker(), () -> Inventory.contains("Ghost's skull"), 4),

    COMPLETE(null, null, 5);

    private final Worker worker;
    private final BooleanSupplier condition_supplier;
    private final int[] varps;

    RestlessGhostState(Worker worker, BooleanSupplier condition_supplier, int... varps) {
        this.varps = varps;
        this.condition_supplier = condition_supplier;
        this.worker = worker;
    }

    public static RestlessGhostState getValidState() {
        return Arrays.stream(values())
                .filter(a -> a.condition_supplier != null && isInCondition(a) && isInVarp(a))
                .findFirst()
                .orElseGet(() -> Arrays.stream(values())
                        .filter(a -> a.condition_supplier == null && isInVarp(a))
                        .findFirst()
                        .orElse(null));
    }

    public static boolean isInVarp(RestlessGhostState state) {
        return Arrays.stream(state.getVarps()).anyMatch(a -> a == Varps.get(RestlessGhostMission.RESTLESS_GHOST_VARP));
    }

    public static boolean isInCondition(RestlessGhostState state) {
        return state.getConditionSupplier().getAsBoolean();
    }

    public Worker getWorker() {
        return worker;
    }

    public BooleanSupplier getConditionSupplier() {
        return condition_supplier;
    }

    public int[] getVarps() {
        return varps;
    }
}
