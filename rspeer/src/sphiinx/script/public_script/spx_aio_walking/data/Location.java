package sphiinx.script.public_script.spx_aio_walking.data;

import org.rspeer.runetek.api.movement.position.Position;

public enum Location {

    LUMBRIDGE(new Position(0000, 0000, 0000));

    private final Position POSITION;

    Location(Position position) {
        this.POSITION = position;
    }

    public Position getPosition() {
        return POSITION;
    }

}
