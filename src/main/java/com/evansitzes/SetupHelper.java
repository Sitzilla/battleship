package com.evansitzes;

import com.evansitzes.model.Board;
import com.evansitzes.model.Coordinate;
import com.evansitzes.model.Coordinate.State;
import com.evansitzes.model.Ship;
import com.evansitzes.model.Ship.ShipType;

import java.util.*;

import static com.evansitzes.model.Ship.ShipType.*;

public class SetupHelper {
    private static final Random RANDOM = new Random();

    /**
     * Creates the ships for human or computer player
     * @return a map of ship types to ships
     */
    public static Map<ShipType, Ship> createShips() {
        final Map<ShipType, Ship> ships = new EnumMap<>(ShipType.class);

        ships.put(DESTROYER, new Ship(2, DESTROYER));
        ships.put(CRUISER, new Ship(3, CRUISER));
        ships.put(BATTLESHIP, new Ship(4, BATTLESHIP));
        ships.put(CARRIER, new Ship(5, CARRIER));

        return ships;
    }

    /**
     * Place the pieces randomly on the board.
     *
     * Placement algorithm:
     *  - for each ship, choose a random spot on the board and direction
     *  - check that all the coordinates from that spot are valid for the ship. If not, choose another random spot
     *  - place the ship
     *
     * @param board the board to place pieces on
     * @param ships the ships to place on the board
     */
    public static void placePieces(final Board board, final Map<ShipType, Ship> ships) {
        final List<Direction> directions = List.of(Direction.values());

        for (final Ship ship : ships.values()) {
            int x = 0;
            int y = 0;
            Direction direction = null;
            boolean isValid = false;

            while (!isValid) {
                // find random spot in board
                x = RANDOM.nextInt(Board.WIDTH);
                y = RANDOM.nextInt(Board.HEIGHT);

                direction = getRandomDirection(directions);

                // check if n squares in that direction are clear
                isValid = isValidPlacement(board, ship, x, y, direction);
            }

            placeShip(board, ship, x, y, direction);
        }
    }

    /**
     * Takes in an (x, y) coordinate and a direction, and decides if it is a valid location to place a ship. Does this
     * by checking 'n' spaces in a direction (where 'n' is the ships size), and checks if there are already ships there and
     * if it is within the bounds of the board.
     *
     * @param board the board to check
     * @param ship the ship to check
     * @param x x coordinate
     * @param y y coordinate
     * @param direction the direction to place the ship
     * @return whether the point is a valid placement for the ship
     */
    private static boolean isValidPlacement(final Board board, final Ship ship, final int x, final int y, final Direction direction) {
        for (int i = 0; i < ship.getSize(); i++) {

            if (direction == Direction.RIGHT) {
                if (x + i >= Board.HEIGHT || board.getGrid()[y][x + i].getState() != State.EMPTY) {
                    return false;
                }
            }

            if (direction == Direction.LEFT) {
                if (x - i < 0 || board.getGrid()[y][x - i].getState() != State.EMPTY) {
                    return false;
                }
            }

            if (direction == Direction.DOWN) {
                if (y + i >= Board.WIDTH || board.getGrid()[y + i][x].getState() != State.EMPTY) {
                    return false;
                }
            }

            if (direction == Direction.UP) {
                if (y - i < 0 || board.getGrid()[y - i][x].getState() != State.EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void placeShip(final Board board, final Ship ship, final int x, final int y, final Direction direction) {
        for (int i = 0; i < ship.getSize(); i++) {
            final Coordinate coordinate;

            if (direction == Direction.RIGHT) {
                coordinate = board.getGrid()[y][x + i];
            } else if (direction == Direction.LEFT) {
                coordinate = board.getGrid()[y][x - i];
            } else if (direction == Direction.DOWN) {
                coordinate = board.getGrid()[y + i][x];
            } else {
                coordinate = board.getGrid()[y - i][x];
            }

            coordinate.setState(State.OCCUPIED);
            coordinate.setShipType(ship.getType());
        }
    }

    private static Direction getRandomDirection(final List<Direction> directions) {
        return directions.get(RANDOM.nextInt(directions.size()));
    }

    private enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }
}
