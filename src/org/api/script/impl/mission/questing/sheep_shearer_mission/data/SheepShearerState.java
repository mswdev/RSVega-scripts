package org.api.script.impl.mission.questing.sheep_shearer_mission.data;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.questing.sheep_shearer_mission.SheepShearerMission;
import org.api.script.impl.mission.questing.sheep_shearer_mission.worker.impl.SpinWoolWorker;
import org.api.script.impl.worker.DialogueWorker;
import org.api.script.impl.worker.MovementWorker;
import org.api.script.impl.worker.banking.DepositWorker;
import org.api.script.impl.worker.interactables.NpcWorker;
import org.api.script.impl.worker.interactables.PickableWorker;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;

import java.util.Arrays;
import java.util.function.BooleanSupplier;

public enum SheepShearerState {

    DEPOSIT_INVENTORY(new DepositWorker(), () -> Inventory.getFreeSlots() < 21, 0),

    TALK_TO_FRED(new NpcWorker(a -> a.getName().equals("Fred the Farmer"), new DialogueWorker(a -> a.equals("I'm looking for a quest.") || a.equals("Yes okay. I can do that.") || a.equals("Of course!") || a.equals("I'm something of an expert actually!")), new MovementWorker(new Position(3188, 3277, 0))), null, 0),

    GET_SHEARS(new PickableWorker(a -> a.getName().equals("Shears"), new MovementWorker(new Position(3191, 3272, 0))), () -> !Inventory.contains("Shears"), 1),

    SHEAR_SHEEP(new NpcWorker(a -> a.getName().equals("Sheep") && a.containsAction("Shear") && !a.containsAction("Talk-to"), new MovementWorker(new Position(3202, 3267, 0))), () -> Inventory.getCount("Wool") + Inventory.getCount("Ball of wool") < 20, 1),

    SPIN_WOOL(new SpinWoolWorker(), () -> Inventory.getCount("Ball of wool") < 20, 1),

    DELIVER_BALL_OF_WOOL(new NpcWorker(a -> a.getName().equals("Fred the Farmer"), new MovementWorker(new Position(3188, 3277, 0))), () -> Inventory.getCount("Ball of wool") >= 20, 1, 20),

    COMPLETE(null, null, 21);

    private final Worker worker;
    private final BooleanSupplier condition_supplier;
    private final int[] varps;

    SheepShearerState(Worker worker, BooleanSupplier condition_supplier, int... varps) {
        this.varps = varps;
        this.condition_supplier = condition_supplier;
        this.worker = worker;
    }

    public static SheepShearerState getValidState() {
        return Arrays.stream(values())
                .filter(a -> a.condition_supplier != null && isInCondition(a) && isInVarp(a))
                .findFirst()
                .orElseGet(() -> Arrays.stream(values())
                        .filter(a -> a.condition_supplier == null && isInVarp(a))
                        .findFirst()
                        .orElse(null));
    }

    public static boolean isInVarp(SheepShearerState state) {
        return Arrays.stream(state.getVarps()).anyMatch(a -> a == Varps.get(SheepShearerMission.SHEEP_SHEARER_VARP));
    }

    public static boolean isInCondition(SheepShearerState state) {
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
