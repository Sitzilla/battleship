package model;

import lombok.Data;

@Data
public class Ship {

    private final int size;
    private final ShipType type;
    private boolean isSunk;
    private int lifeRemaining;

    public Ship(final int size, final ShipType type) {
        this.size = size;
        this.type = type;
        this.isSunk = false;
        this.lifeRemaining = size;
    }

    public void processHit() {
        lifeRemaining--;
        if (lifeRemaining == 0) {
            isSunk = true;
        }
    }

    public enum ShipType {
        DESTROYER,
        CRUISER,
        BATTLESHIP,
        CARRIER
    }
}
