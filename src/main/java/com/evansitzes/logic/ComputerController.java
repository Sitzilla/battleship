package com.evansitzes.logic;

import com.evansitzes.model.Board;
import com.evansitzes.model.Coordinate;
import com.evansitzes.model.Coordinate.State;
import com.evansitzes.model.Point;
import com.evansitzes.model.Ship;
import com.evansitzes.model.Ship.ShipType;

import java.util.*;

public class ComputerController {

    private Deque<Point> moves = new ArrayDeque<>();

    /**
     * Builds a random list of ordered possible moves for the computer. Does this by
     * - find all points on the board
     * - randomize them
     * - add them to a queue
     * @param playerBoard the player's board
     */
    public ComputerController(final Board playerBoard) {
        final List<Point> possibleMoves = new ArrayList<>();

        for (int i = 0; i < Board.WIDTH; i++) {
            for (int j = 0; j < Board.HEIGHT; j++) {
                final Coordinate coordinate = playerBoard.getGrid()[i][j];
                possibleMoves.add(coordinate.getPoint());
            }
        }

        Collections.shuffle(possibleMoves);
        moves.addAll(possibleMoves);
    }

    /**
     * Execute the computer's next attack.  All the computer's moves are queued up at the beginning of the game randomly,
     * so to execute the attack we just dequeue the top item and check if it is a hit or miss, then update the player's
     * board/ships respectively.
     * @param playerBoard the player's board
     * @param playerShips the player's ships.
     */
    public void processComputerAttack(final Board playerBoard, final Map<ShipType, Ship> playerShips) {
        final Point nextMove = moves.pop();

        final Coordinate coordinate = playerBoard.getGrid()[nextMove.y][nextMove.x];
        System.out.println("Computer fire at x: " + nextMove.x + ", y: " + nextMove.y);

        if (coordinate.getState() == State.OCCUPIED) {
            final Ship ship = playerShips.get(coordinate.getShipType());

            System.out.println("Computer hit your " + ship.getType());
            coordinate.setState(State.HIT);

            ship.processHit();
            if (ship.isSunk()) {
                System.out.println("Ship destroyed! Computer sunk your " + ship.getType());
            }

        } else {
            System.out.println("Computer misses");
            coordinate.setState(State.MISS);
        }
    }

    public Deque<Point> getMoves() {
        return moves;
    }

    public void setMoves(final Deque<Point> moves) {
        this.moves = moves;
    }
}
