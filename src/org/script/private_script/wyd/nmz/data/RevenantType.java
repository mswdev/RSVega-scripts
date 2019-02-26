package org.script.private_script.wyd.nmz.data;

import org.rspeer.runetek.api.movement.position.Position;

public enum RevenantType {

    IMP("Revenant imp", new Position(3241, 10182, 0)),
    GOBLIN("Revenant goblin", new Position(3234, 10182, 0)),
    PYREFIEND("Revenant pyrefiend", new Position(3234, 10172, 0)),
    HOBGOBLIN("Revenant hobgoblin", new Position(3252, 10157, 0)),
    CYCLOPS("Revenant cyclops", new Position(3247, 10159, 0)),
    HELLHOUND("Revenant hellhound", new Position(3253, 10168, 0)),
    DEMON("Revenant demon", new Position(3243, 10171, 0)),
    ORK("Revenant ork", new Position(3242, 10160, 0)),
    DARK_BEAST("Revenant dark beast", new Position(3252, 10142, 0)),
    KNIGHT("Revenant knight", new Position(3244, 10173, 0)),
    DRAGON("Revenant dragon", new Position(3242, 10177, 0));

    private final String name;
    private final Position position;

    RevenantType(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }
}
