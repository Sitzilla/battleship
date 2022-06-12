import model.Board;
import model.Coordinate;
import model.Coordinate.State;
import model.Point;
import model.Ship;
import model.Ship.ShipType;

import java.util.*;

public class ComputerController {

    private final Deque<Point> moves = new ArrayDeque<>();

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

    public void processComputerAttack(final Board playerBoard, final Map<ShipType, Ship> playerShips) {
        final Point nextMove = moves.pop();

        final Coordinate coordinate = playerBoard.getGrid()[nextMove.y][nextMove.x];

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
}
