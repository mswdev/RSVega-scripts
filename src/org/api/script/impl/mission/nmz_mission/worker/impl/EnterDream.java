package org.api.script.impl.mission.nmz_mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.worker.interactables.SceneObjectWorker;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.component.Interfaces;

import java.util.function.Predicate;

public class EnterDream extends Worker {


    public final static int ACTIVE_DREAM_VARPBIT = 4605;
    private final Predicate<SceneObject> dream_potion_predicate = a -> a.getName().contains("Potion");
    private final SceneObjectWorker scene_object_worker = new SceneObjectWorker(dream_potion_predicate, a -> a.equals("Drink"));
    private final int dream_component_predicate = 129;
    private final int accept_dream_component_predicate = 6;

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        final InterfaceComponent accept_dream_component = Interfaces.getComponent(dream_component_predicate, accept_dream_component_predicate);
        if (accept_dream_component == null) {
            scene_object_worker.work();
            return;
        }

        accept_dream_component.click();
    }

    @Override
    public String toString() {
        return "Entering dream";
    }
}

