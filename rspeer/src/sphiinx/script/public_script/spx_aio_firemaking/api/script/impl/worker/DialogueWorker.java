package sphiinx.script.public_script.spx_aio_firemaking.api.script.impl.worker;

import org.rspeer.runetek.api.component.Dialog;
import sphiinx.script.public_script.spx_aio_firemaking.api.script.framework.worker.Worker;

import java.util.function.Predicate;

public class DialogueWorker extends Worker {

    private final Predicate<String> action;

    public DialogueWorker() {
        this(a -> true);
    }

    public DialogueWorker(Predicate<String> action) {
        this.action = action;
    }

    @Override
    public boolean needsRepeat() {
        return Dialog.isOpen();
    }

    @Override
    public void work() {
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

