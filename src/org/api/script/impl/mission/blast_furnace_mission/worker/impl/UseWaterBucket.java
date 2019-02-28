package org.api.script.impl.mission.blast_furnace_mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.SceneObjects;

import java.util.function.Predicate;

public class UseWaterBucket extends Worker {


    private static final Predicate<Pickable> BUCKET_PICKABLE = a -> a.getName().equals("Bucket");
    private static final Predicate<Item> BUCKET_ITEM = a -> a.getName().equals("Bucket");
    private static final Predicate<Item> BUCKET_OF_WATER_ITEM = a -> a.getName().equals("Bucket of water");
    private static final Predicate<SceneObject> SINK = a -> a.getName().equals("Sink");

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        Item bucket_item = Inventory.getFirst(BUCKET_ITEM);
        final Item bucket_of_water_item = Inventory.getFirst(BUCKET_OF_WATER_ITEM);
        final Pickable pickable = Pickables.getNearest(BUCKET_PICKABLE);
        if (bucket_of_water_item != null) {
            final SceneObject bar_dispenser = SceneObjects.getNearest(CollectBars.COLLECT_BARS);
            if (bar_dispenser == null)
                return;

            if (Inventory.use(BUCKET_OF_WATER_ITEM, bar_dispenser))
                if (Time.sleepUntil(() -> Inventory.getFirst(BUCKET_OF_WATER_ITEM) == null, 1500)) {
                    bucket_item = Inventory.getFirst(BUCKET_ITEM);
                    if (bucket_item == null)
                        return;

                    if (bucket_item.interact(a -> a.equals("Drop")))
                        Time.sleepUntil(() -> Inventory.getFirst(BUCKET_ITEM) == null, 1500);
                }
        } else if (bucket_item != null) {
            final SceneObject sink = SceneObjects.getNearest(SINK);
            if (sink == null)
                return;

            if (Inventory.use(BUCKET_ITEM, sink))
                Time.sleepUntil(() -> Inventory.getFirst(BUCKET_ITEM) == null, 1500);
        } else if (pickable != null) {
            if (pickable.click())
                Time.sleepUntil(() -> Inventory.getFirst(BUCKET_ITEM) != null, 3000);
        }
    }

    @Override
    public String toString() {
        return "Using water bucket.";
    }
}

