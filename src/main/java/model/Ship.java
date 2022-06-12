package model;

import lombok.Data;

@Data
public class Ship {

    private final int size;
    private final ShipType type;

    public enum ShipType {
        DESTROYER,
        SUBMARINE,
        CRUISER,
        BATTLESHIP,
        CARRIER
    }
}
