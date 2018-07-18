package antiban.priority_framework;

public interface PriorityEvent {

    int priority();

    boolean valid();

    void execute();

}
