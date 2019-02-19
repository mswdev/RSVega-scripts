package sphiinx.script.public_script.spx_aio_walking.data;

import org.rspeer.runetek.api.movement.position.Position;

public enum Location {

    AL_KHARID(new Position(3271, 3167, 0)),
    ARDOUGNE(new Position(2652, 3283, 0)),
    BRIMHAVEN(new Position(2808, 3183, 0)),
    BARBARIAN_VILLAGE(new Position(3082, 3419, 0)),
    BURTHORPE(new Position(2900, 3544, 0)),
    CANIFIS(new Position(3509, 3480, 0)),
    CASTLE_WARS(new Position(2443, 3084, 0)),
    CATHERBY(new Position(2809, 3439, 0)),
    DRAYNOR_VILLAGE(new Position(3093, 3242, 0)),
    EDGEVILLE(new Position(3093, 3494, 0)),
    FALADOR_EAST(new Position(3013, 3356, 0)),
    FALADOR_WEST(new Position(2945, 3369, 0)),
    GRAND_EXCHANGE(new Position(3165, 3484, 0)),
    LUMBRIDGE(new Position(3221, 3218, 0)),
    PEST_CONTROL(new Position(2666, 2653, 0)),
    PISCARILIUS(new Position(2666, 2653, 0)),
    POLLNIVNEACH(new Position(3352, 2978, 0)),
    PORT_SARIM(new Position(3027, 3225, 0)),
    RELLEKKA(new Position(2643, 3675, 0)),
    RIMMINGTON(new Position(2956, 3215, 0)),
    SEERS_VILLAGE(new Position(2725, 3491, 0)),
    VARROCK_EAST(new Position(3253, 3421, 0)),
    VARROCK_WEST(new Position(3183, 3436, 0)),
    YANILLE(new Position(2611, 3092, 0));

    private final Position POSITION;

    Location(Position position) {
        this.POSITION = position;
    }

    public Position getPosition() {
        return POSITION;
    }

}
