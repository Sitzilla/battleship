import model.Board;
import model.Ship;

import java.util.List;

public class Game {

    private final Board playerBoard = new Board();
    private final Board computerBoard = new Board();

    public void start() {
        System.out.println("Starting game");


        System.out.println("Setting up gameboard");
        List<Ship> playerShips = SetupHelper.createShips();
        SetupHelper.placePieces(playerBoard, playerShips);

        List<Ship> computerShips = SetupHelper.createShips();
        SetupHelper.placePieces(computerBoard, computerShips);


    }




}
