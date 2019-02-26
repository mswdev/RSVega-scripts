package org.api.script.impl.mission.tutorial_island_mission.data.args;

import com.beust.jcommander.IStringConverter;
import org.rspeer.runetek.api.movement.position.Position;

import java.util.Arrays;

class PositionConverter implements IStringConverter<Position> {


    @Override
    public Position convert(String s) {
        final int[] xyz = Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray();
        return new Position(xyz[0], xyz[1], xyz[2]);
    }
}

