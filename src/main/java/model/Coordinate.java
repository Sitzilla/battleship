package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coordinate {
    private final int x;
    private final int y;

    private State state;

    public enum State {
        OCCUPIED,
        EMPTY
    }
}
