package sphiinx.script.public_script.spx_aio_walking.data;

import org.rspeer.runetek.api.movement.position.Position;

public enum Location {

    LUMBRIDGE(new Position(0000, 0000, 0000)),
    VARROCK_EAST(new Position(0000, 0000, 0000)),
    VARROCK_WEST(new Position(0000, 0000, 0000)),
    GRAND_EXCHANGE(new Position(0000, 0000, 0000)),
    EDGEVILLE(new Position(0000, 0000, 0000)),
    CAMELOT(new Position(0000, 0000, 0000)),
    SEERS_VILLAGE(new Position(0000, 0000, 0000));

    private final Position POSITION;

    Location(Position position) {
        this.POSITION = position;
    }

    public Position getPosition() {
        return POSITION;
    }

}
