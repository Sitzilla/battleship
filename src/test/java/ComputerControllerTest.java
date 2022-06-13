import com.evansitzes.ComputerController;
import com.evansitzes.SetupHelper;
import com.evansitzes.model.Board;
import com.evansitzes.model.Coordinate;
import com.evansitzes.model.Coordinate.State;
import com.evansitzes.model.Point;
import com.evansitzes.model.Ship;
import com.evansitzes.model.Ship.ShipType;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputerControllerTest {

    @Test
    void testComputerMoveSetup() {
        final Board playerBoard = new Board();

        final ComputerController computerController = new ComputerController(playerBoard);

        assertEquals(100, computerController.getMoves().size());

        final Set<Point> uniqueMoves = new HashSet<>(computerController.getMoves());
        assertEquals(100, uniqueMoves.size());
    }

    @Test
    void testComputerAttackHit() {
        // mock out board
        final Point point = new Point(3, 7);
        final Board playerBoard = new Board();
        final Map<ShipType, Ship> playerShips = SetupHelper.createShips();

        playerBoard.getGrid()[point.y][point.x].setState(Coordinate.State.OCCUPIED);
        playerBoard.getGrid()[point.y][point.x].setShipType(ShipType.BATTLESHIP);

        // mock out computer moves
        final Deque<Point> moves = new ArrayDeque<>();
        moves.add(point);

        final ComputerController computerController = new ComputerController(playerBoard);
        computerController.setMoves(moves);

        computerController.processComputerAttack(playerBoard, playerShips);

        assertEquals(3, playerShips.get(ShipType.BATTLESHIP).getLifeRemaining());
        assertEquals(playerBoard.getGrid()[point.y][point.x].getState(), State.HIT);
    }
}
