package org.api.script.impl.mission.questing.rune_mysteries_mission.data;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.questing.rune_mysteries_mission.RuneMysteriesMission;
import org.api.script.impl.worker.DialogueWorker;
import org.api.script.impl.worker.MovementWorker;
import org.api.script.impl.worker.banking.DepositWorker;
import org.api.script.impl.worker.interactables.NpcWorker;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;

import java.util.Arrays;
import java.util.function.BooleanSupplier;

public enum RuneMysteriesState {

    DEPOSIT_INVENTORY(new DepositWorker(), () -> Inventory.getFreeSlots() < 1, 0),

    GET_TALISMAN(new NpcWorker(a -> a.getName().equals("Duke Horacio"), new DialogueWorker(a -> a.equals("Talk about something else.") || a.equals("Have you any quests for me?") || a.equals("Sure, no problem.")), new MovementWorker(new Position(3209, 3222, 1))), null, 0, 1),

    DELIVER_TALISMAN(new NpcWorker(a -> a.getName().equals("Sedridor"), new DialogueWorker(a -> a.equals("I'm looking for the head wizard.") || a.equals("Ok, here you are.") || a.equals("Yes, certainly.")), new MovementWorker(new Position(3106, 9570, 0))), () -> Inventory.contains("Air talisman"), 1),

    GET_PACKAGE(new NpcWorker(a -> a.getName().equals("Sedridor"), new MovementWorker(new Position(3106, 9570, 0))), null, 2, 3),

    DELIVER_PACKAGE(new NpcWorker(a -> a.getName().equals("Aubury"), new DialogueWorker(a -> a.equals("I have been sent here with a package for you.")), new MovementWorker(new Position(3253, 3400, 0))), () -> Inventory.contains("Research package"), 3),

    GET_NOTE(new NpcWorker(a -> a.getName().equals("Aubury"), new MovementWorker(new Position(3253, 3400, 0))), null, 4, 5),

    DELIVER_NOTE(new NpcWorker(a -> a.getName().equals("Sedridor"), new MovementWorker(new Position(3106, 9570, 0))), () -> Inventory.contains("Notes"), 5),

    COMPLETE(null, null, 6);

    private final Worker worker;
    private final BooleanSupplier condition_supplier;
    private final int[] varps;

    RuneMysteriesState(Worker worker, BooleanSupplier condition_supplier, int... varps) {
        this.varps = varps;
        this.condition_supplier = condition_supplier;
        this.worker = worker;
    }

    public static RuneMysteriesState getValidState() {
        return Arrays.stream(values())
                .filter(a -> a.condition_supplier != null && isInCondition(a) && isInVarp(a))
                .findFirst()
                .orElseGet(() -> Arrays.stream(values())
                        .filter(a -> a.condition_supplier == null && isInVarp(a))
                        .findFirst()
                        .orElse(null));
    }

    public static boolean isInVarp(RuneMysteriesState state) {
        return Arrays.stream(state.getVarps()).anyMatch(a -> a == Varps.get(RuneMysteriesMission.RUNE_MYSTERIES_VARP));
    }

    public static boolean isInCondition(RuneMysteriesState state) {
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

