package com.evansitzes;

import com.evansitzes.exception.TranslationException;
import com.evansitzes.model.Board;
import com.evansitzes.model.Coordinate;
import com.evansitzes.model.Coordinate.State;
import com.evansitzes.model.Point;
import com.evansitzes.model.Ship;
import com.evansitzes.model.Ship.ShipType;

import java.util.Map;

public class MoveHelper {

    /**
     * Translates human-input to a coordinate (x, y) value on the board.
     *
     * Example:
     * - A5 returns (0, 4)
     * - J10 returns (9, 9)
     *
     * @param attackCoordinates valid attack coordinates (e.g. "A3)
     * @return a Point with (x, y) values
     */
    public static Point translateCoordinates(final String attackCoordinates) throws TranslationException {
        final int column;
        final int row;

        try {
            column = (int) attackCoordinates.toUpperCase().charAt(0) - 65;
            row = Integer.parseInt(attackCoordinates.substring(1)) - 1;
        } catch (final Exception e) {
            throw new TranslationException("Invalid input. Must input a valid coordinate set (e.g. 'D7').");
        }

        if (column < 0 || column > 9) {
            throw new TranslationException("Column must be a character between A - J (case insensitive).");
        }

        if (row < 0 || row > 9) {
            throw new TranslationException("Row must be a number between 1 - 10.");
        }

        return new Point(column, row);
    }

    /**
     * Takes in the (x, y) point that the player chose to attack, and changes the state of the coordinate at that point.
     *
     * - Empty: mark the coordinate as 'missed'
     * - Occupied: mark the coordinate as 'hit', and mark the associated ship as being hit
     * - Missed: don't do anything
     * - Hit: don't do anything
     *
     * @param target (x, y) coordinate to attack
     * @param computerBoard the computers board
     * @param playerViewScreen what the player can see of the computers board
     * @param computerShips the computer ships
     */
    public static void processAttack(final Point target, final Board computerBoard, final Board playerViewScreen, final Map<ShipType, Ship> computerShips) {
        final Coordinate coordinate = computerBoard.getGrid()[target.y][target.x];

        if (coordinate.getState() == State.EMPTY) {
            System.out.println("Miss");
            coordinate.setState(State.MISS);

            playerViewScreen.getGrid()[target.y][target.x].setState(coordinate.getState());
            return;
        }

        if (coordinate.getState() == State.OCCUPIED) {
            System.out.println("Hit!!");
            coordinate.setState(State.HIT);

            final Ship ship = computerShips.get(coordinate.getShipType());
            ship.processHit();
            if (ship.isSunk()) {
                System.out.println("Ship destroyed! You sunk their " + ship.getType());
            }

            playerViewScreen.getGrid()[target.y][target.x].setState(coordinate.getState());
            return;
        }

        System.out.println("You already attacked this location");
    }
}
