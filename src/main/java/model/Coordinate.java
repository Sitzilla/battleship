package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.Ship.ShipType;

@Data
@AllArgsConstructor
public class Coordinate {
    private final Point point;

    private State state;
    private ShipType shipType;

    public enum State {
        OCCUPIED,
        EMPTY,
        HIT,
        MISS
    }
}
