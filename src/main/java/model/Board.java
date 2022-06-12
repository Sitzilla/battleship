package model;

import lombok.Getter;

@Getter
public class Board {

    public static int WIDTH = 10;
    public static int HEIGHT = 10;

    Coordinate[][] grid;

    public Board() {
        grid = new Coordinate[WIDTH][HEIGHT];

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                grid[i][j] = new Coordinate(i, j, Coordinate.State.EMPTY);
            }
        }
    }

    public void prettyPrint() {

        System.out.printf("%2c ", ' ');
        System.out.printf("%2c ", 'A');
        System.out.printf("%2c ", 'B');
        System.out.printf("%2c ", 'C');
        System.out.printf("%2c ", 'D');
        System.out.printf("%2c ", 'E');
        System.out.printf("%2c ", 'F');
        System.out.printf("%2c ", 'G');
        System.out.printf("%2c ", 'H');
        System.out.printf("%2c ", 'I');
        System.out.printf("%2c ", 'J');
        System.out.println();

        for (int i = 0; i < WIDTH; i++) {

            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < HEIGHT; j++) {
                if (grid[i][j].getState() == Coordinate.State.OCCUPIED) {
                    System.out.printf("%2d ", 1);
                } else if (grid[i][j].getState() == Coordinate.State.EMPTY) {
                    System.out.printf("%2d ", 0);
                }
            }

            System.out.println();
        }

    }
}
