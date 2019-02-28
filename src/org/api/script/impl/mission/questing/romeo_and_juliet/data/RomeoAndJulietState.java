package org.api.script.impl.mission.questing.romeo_and_juliet.data;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.questing.romeo_and_juliet.RomeoAndJulietMission;
import org.api.script.impl.worker.DialogueWorker;
import org.api.script.impl.worker.MovementWorker;
import org.api.script.impl.worker.banking.DepositWorker;
import org.api.script.impl.worker.interactables.NpcWorker;
import org.api.script.impl.worker.interactables.SceneObjectWorker;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;

import java.util.Arrays;
import java.util.function.BooleanSupplier;

public enum RomeoAndJulietState {

    DEPOSIT_INVENTORY(new DepositWorker(), () -> Inventory.getFreeSlots() < 2, 0),

    TALK_TO_ROMEO(new NpcWorker(a -> a.getName().equals("Romeo"), new DialogueWorker(a -> a.equals("Yes, I have seen her actually!") || a.equals("Yes, ok, I'll let her know.") || a.equals("Ok, thanks.")), new MovementWorker(new Position(3212, 3428, 0))), null, 0),

    //Temporary until webwalker supports juliet house stairs.
    USE_JULIET_STAIRCASE(new SceneObjectWorker(a -> a.getName().equals("Staircase") && (a.getPosition().equals(new Position(3156, 3435, 0)) || a.getPosition().equals(new Position(3156, 3435, 1))), new MovementWorker(new Position(3159, 3435, 0))), null, 10, 20),

    TALK_TO_JULIET(new NpcWorker(a -> a.getName().equals("Juliet"), new MovementWorker(new Position(3158, 3429, 1))), () -> Players.getLocal().getPosition().getFloorLevel() == 1 && !Inventory.contains("Message"), 10, 20),

    TALK_TO_ROMEO_2(new NpcWorker(a -> a.getName().equals("Romeo"), new MovementWorker(new Position(3212, 3428, 0))), () -> Inventory.contains("Message") && Players.getLocal().getPosition().getFloorLevel() == 0, 20),

    TALK_TO_FATHER_LAWRENCE(new NpcWorker(a -> a.getName().equals("Father Lawrence"), new DialogueWorker(a -> a.equals("Ok, thanks.")), new MovementWorker(new Position(3254, 3482, 0))), null, 30),

    PICK_CADAVA_BERRIES(new SceneObjectWorker(a -> a.getName().equals("Cadava bush") && a.getId() == 23625, new MovementWorker(new Position(3270, 3369, 0))), () -> !Inventory.contains("Cadava potion") && !Inventory.contains("Cadava berries"), 40, 50),

    //Temporary until webwalker supports juliet house stairs.
    USE_JULIET_STAIRCASE_2(new SceneObjectWorker(a -> a.getName().equals("Staircase") && (a.getPosition().equals(new Position(3156, 3435, 0)) || a.getPosition().equals(new Position(3156, 3435, 1))), new MovementWorker(new Position(3159, 3435, 0))), null, 50, 60),

    TALK_TO_APOTHECARY(new NpcWorker(a -> a.getName().equals("Apothecary"), new DialogueWorker(a -> a.equals("Talk about something else.") || a.equals("Talk about Romeo & Juliet.")), new MovementWorker(new Position(3193, 3403, 0))), () -> Inventory.contains("Cadava berries"), 40, 50),

    TALK_TO_JULIET_2(new NpcWorker(a -> a.getName().equals("Juliet"), new MovementWorker(new Position(3158, 3429, 1))), () -> Players.getLocal().getPosition().getFloorLevel() == 1 && Inventory.contains("Cadava potion"), 50),

    TALK_TO_ROMEO_3(new NpcWorker(a -> a.getName().equals("Romeo"), new DialogueWorker(() -> Interfaces.getFirst(a -> a.getText().contains("Oh , ok...come on!")) != null), new MovementWorker(new Position(3212, 3428, 0))), () -> Players.getLocal().getPosition().getFloorLevel() == 0, 60),

    COMPLETE(null, null, 100);

    private final Worker worker;
    private final BooleanSupplier condition_supplier;
    private final int[] varps;

    RomeoAndJulietState(Worker worker, BooleanSupplier condition_supplier, int... varps) {
        this.varps = varps;
        this.condition_supplier = condition_supplier;
        this.worker = worker;
    }

    public static RomeoAndJulietState getValidState() {
        return Arrays.stream(values())
                .filter(a -> a.condition_supplier != null && isInCondition(a) && isInVarp(a))
                .findFirst()
                .orElseGet(() -> Arrays.stream(values())
                        .filter(a -> a.condition_supplier == null && isInVarp(a))
                        .findFirst()
                        .orElse(null));
    }

    public static boolean isInVarp(RomeoAndJulietState state) {
        return Arrays.stream(state.getVarps()).anyMatch(a -> a == Varps.get(RomeoAndJulietMission.ROMEO_AND_JULIET_VARP));
    }

    public static boolean isInCondition(RomeoAndJulietState state) {
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
