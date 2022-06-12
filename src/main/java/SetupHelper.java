import model.Board;
import model.Coordinate;
import model.Ship;

import java.util.*;

import static model.Ship.ShipType.*;

public class SetupHelper {

    private static final List<Direction> DIRECTIONS = List.of(Direction.values());
    private static final Random RANDOM = new Random();


    /**
     *
     * @return
     */
    public static List<Ship> createShips() {
        List<Ship> ships = new ArrayList<>();

        ships.add(new Ship(2, DESTROYER));
        ships.add(new Ship(3, SUBMARINE));
        ships.add(new Ship(3, CRUISER));
        ships.add(new Ship(4, BATTLESHIP));
        ships.add(new Ship(5, CARRIER));

        return ships;
    }

    /**
     * Place the pieces randomly on the board
     * @param board the board to place pieces on
     * @param ships the ships to place on the board
     */
    public static void placePieces(Board board, List<Ship> ships) {

        for (Ship ship : ships) {
            int x = 0;
            int y = 0;
            Direction direction = null;
            boolean isValid = false;

            while (!isValid) {
                // find random spot in board
                x = RANDOM.nextInt(Board.WIDTH);
                y = RANDOM.nextInt(Board.HEIGHT);

                direction = getRandomDirection();

                // check if n squares in that direction are clear
                System.out.println("Attempting " + ship.getType() + ", x: " + x + ", y: " + y + ", direction: " + direction);
                isValid = isValidPlacement(board, ship, x, y, direction);
            }

            // place the ship
            for (int i = 0; i < ship.getSize(); i++) {
                if (direction == Direction.RIGHT) {
                    board.getGrid()[y][x + i].setState(Coordinate.State.OCCUPIED);
                }

                if (direction == Direction.LEFT) {
                    board.getGrid()[y][x - i].setState(Coordinate.State.OCCUPIED);
                }

                if (direction == Direction.DOWN) {
                    board.getGrid()[y + i][x].setState(Coordinate.State.OCCUPIED);
                }

                if (direction == Direction.UP) {
                    board.getGrid()[y - i][x].setState(Coordinate.State.OCCUPIED);
                }
            }

            System.out.println("Ship placed " + ship.getType() + ", x: " + x + ", y: " + y + ", direction: " + direction);
            board.prettyPrint();
        }
    }

    private static boolean isValidPlacement(Board board, Ship ship, int x, int y, Direction direction) {
        for (int i = 0; i < ship.getSize(); i++) {

            if (direction == Direction.RIGHT) {
                if (x + i >= Board.HEIGHT || board.getGrid()[y][x + i].getState() != Coordinate.State.EMPTY) {
                    System.out.println("Invalid space up, trying placement again");
                    return false;
                }
            }

            if (direction == Direction.LEFT) {
                if (x - i < 0 || board.getGrid()[y][x - i].getState() != Coordinate.State.EMPTY) {
                    System.out.println("Invalid space down, trying placement again");
                    return false;
                }
            }

            if (direction == Direction.DOWN) {
                if (y + i >= Board.WIDTH || board.getGrid()[y + i][x].getState() != Coordinate.State.EMPTY) {
                    System.out.println("Invalid space right, trying placement again");
                    return false;
                }
            }

            if (direction == Direction.UP) {
                if (y - i < 0 || board.getGrid()[y - i][x].getState() != Coordinate.State.EMPTY) {
                    System.out.println("Invalid space left, trying placement again");
                    return false;
                }
            }
        }
        return true;
    }

    private static Direction getRandomDirection() {
        return DIRECTIONS.get(RANDOM.nextInt(DIRECTIONS.size()));
    }

    private enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }
}
