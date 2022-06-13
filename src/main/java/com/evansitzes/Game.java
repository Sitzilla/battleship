package com.evansitzes;

import com.evansitzes.exception.TranslationException;
import com.evansitzes.logic.ComputerController;
import com.evansitzes.logic.MoveHelper;
import com.evansitzes.logic.SetupController;
import com.evansitzes.model.Board;
import com.evansitzes.model.Point;
import com.evansitzes.model.Ship;
import com.evansitzes.model.Ship.ShipType;

import java.util.Map;
import java.util.Scanner;

public class Game {

    private final Board playerBoard = new Board();
    private final Board computerBoard = new Board();
    private final Board playerViewScreen = new Board();

    public void start() {

        System.out.println("Setting up game board");
        final Map<ShipType, Ship> playerShips = SetupController.createShips();
        SetupController.placePieces(playerBoard, playerShips);

        final Map<ShipType, Ship> computerShips = SetupController.createShips();
        SetupController.placePieces(computerBoard, computerShips);

        final ComputerController computerController = new ComputerController(playerBoard);

        System.out.println("Setup complete. Starting Battleship game");
        boolean gameOver = false;
        final Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            System.out.println();
            System.out.println("==================================================");
            System.out.println();
            System.out.println("Please enter a number: \n (1) choose attack \n (2) print your board \n (3) print your moves  \n (4) end game");

            final String command = scanner.nextLine();

            switch (command) {
                case "1":
                    System.out.println("Enter in coordinates to fire on (e.g. 'D3').");
                    final String attackCoordinates = scanner.nextLine();

                    final Point point;
                    try {
                        point = MoveHelper.translateCoordinates(attackCoordinates);
                    } catch (final TranslationException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    MoveHelper.processAttack(point, computerBoard, playerViewScreen, computerShips);

                    final boolean sunkAllComputerShips = checkForGameEnd(computerShips);

                    if (sunkAllComputerShips) {
                        System.out.println("Congrats you win!");
                        gameOver = true;
                    }

                    computerController.processComputerAttack(playerBoard, playerShips);
                    final boolean sunkAllHumanShips = checkForGameEnd(playerShips);

                    if (sunkAllHumanShips) {
                        System.out.println("You have lost all of your ships. You lose.");
                        gameOver = true;
                    }
                    break;

                case "2":
                    playerBoard.prettyPrint();
                    break;

                case "3":
                    playerViewScreen.prettyPrint();
                    break;

                case "4":
                    System.out.println("Are you sure you want to end the game? Type 'yes' to confirm.");

                    final String manuallyEndGame = scanner.nextLine();
                    if ("yes".equals(manuallyEndGame)) {
                        gameOver = true;
                    }
                    break;

                case "42":
                    System.out.println("Hidden cheat command found! Showing computer's board.");
                    computerBoard.prettyPrint();
                    break;

                default:
                    System.out.println("Invalid selection. Please enter a number 1 - 4");
            }
        }
    }

    private static boolean checkForGameEnd(final Map<ShipType, Ship> ships) {
        return ships.values().stream()
                .allMatch(Ship::isSunk);
    }

}
