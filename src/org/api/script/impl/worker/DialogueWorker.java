package org.api.script.impl.worker;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

public class DialogueWorker extends Worker {

    private final Predicate<String> action;
    private final BooleanSupplier cutscene_delay;

    public DialogueWorker() {
        this(a -> true);
    }

    public DialogueWorker(Predicate<String> action) {
        this(action, null);
    }

    public DialogueWorker(BooleanSupplier cutscene_delay) {
        this(a -> true, cutscene_delay);
    }

    public DialogueWorker(Predicate<String> action, BooleanSupplier cutscene_delay) {
        this.action = action;
        this.cutscene_delay = cutscene_delay;
    }

    @Override
    public boolean needsRepeat() {
        return Dialog.isOpen();
    }

    @Override
    public void work() {
        if (cutscene_delay != null && cutscene_delay.getAsBoolean()) {
            processDialog();
            Time.sleepUntil(() -> !Dialog.isProcessing() && Dialog.isOpen(), 5000);
            return;
        }

        processDialog();
    }

    private void processDialog() {
        if (Dialog.canContinue())
            Dialog.processContinue();

        if (Dialog.isViewingChatOptions())
            Dialog.process(action);
    }

    @Override
    public String toString() {
        return "Executing dialogue worker.";
    }
}

