package org.api.client.screenshot;

import org.rspeer.runetek.event.types.RenderEvent;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Screenshot {

    private static final Queue<Consumer<Image>> RENDER_QUEUE = new LinkedList<>();

    /**
     * Creates a new screenshot consumer and adds it to the render queue.
     *
     * @param name The name of the screenshot.
     */
    public static void add(String name) {
        RENDER_QUEUE.add(new ScreenshotConsumer(name));
    }

    /**
     * Gets the queue of screenshots to be rendered.
     */
    public static Queue<Consumer<Image>> getRenderQueue() {
        return RENDER_QUEUE;
    }

    /**
     * Renders the queue of screenshots.
     *
     * @param event The render event.
     */
    public static void renderQueue(RenderEvent event) {
        if (RENDER_QUEUE.isEmpty())
            return;

        RENDER_QUEUE.remove().accept(event.getProvider().getImage());
    }
}

