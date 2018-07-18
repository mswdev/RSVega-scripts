package antiban.priority_framework;

import java.util.ArrayList;
import java.util.List;

public class PriorityEventHandler {

    /**
     * Contains the list of events.
     */
    private List<PriorityEvent> event_list = new ArrayList<>();

    /**
     * Contains the status of the event.
     */
    private String event_status;

    /**
     * True if the event loop should stop; false otherwise.
     */
    private boolean stop_event_loop;

    /**
     * Iterates through the list of events while executing the event with the highest priority.
     *
     * @param delay The delay between each iteration.
     */
    public void iterate(int delay) {
        while (!stop_event_loop) {
            final PriorityEvent VALID_EVENT = getValidEvent();
            if (VALID_EVENT == null)
                continue;

            event_status = VALID_EVENT.toString();
            VALID_EVENT.execute();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds the specified event(s) to the list of events.
     *
     * @param events The event(s) to be added to the list of events.
     */
    public void addEvent(PriorityEvent... events) {
        for (PriorityEvent event : events) {
            if (!event_list.contains(event))
                event_list.add(event);
        }
    }

    /**
     * Removes the specified event(s) from the list of events.
     *
     * @param events The specified event(s) to be remove for the list of events.
     */
    public void removeEvent(PriorityEvent... events) {
        for (PriorityEvent event : events) {
            event_list.remove(event);
        }
    }

    /**
     * Clears the event list.
     */
    public void clearEventList() {
        event_list.clear();
    }

    /**
     * Gets the count of all the events the list of events.
     *
     * @return An int specifying the amount of events in the list of events.
     */
    public int getEventCount() {
        return event_list.size();
    }

    /**
     * Iterates through the list of events returning a valid event.
     *
     * @return A validated event; null otherwise.
     */
    private PriorityEvent getValidEvent() {
        PriorityEvent temp_event = null;
        for (PriorityEvent event : event_list) {
            if (!event.valid())
                continue;

            if (temp_event == null || temp_event.priority() > event.priority()) {
                temp_event = event;
            }
        }

        return temp_event;
    }

    /**
     * Gets the priority of the specified event.
     *
     * @return The priority of the specified event.
     */
    public int getEventPriority(PriorityEvent event) {
        return event.priority();
    }

    /**
     * Gets the event status.
     *
     * @return The event status.
     */
    public String getStatus() {
        return event_status;
    }

    /**
     * Stops the event loop.
     */
    public void stopEventLoop() {
        stop_event_loop = true;
    }

    /**
     * Sets the event status.
     *
     * @param status The String containing the status to set the event status.
     */
    public void setEventStatus(String status) {
        event_status = status;
    }

}
