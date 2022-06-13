package com.evansitzes.model;

import com.evansitzes.model.Coordinate.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;

    private Coordinate[][] grid;

    public Board() {
        grid = new Coordinate[WIDTH][HEIGHT];

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                grid[i][j] = new Coordinate(new Point(i, j), State.EMPTY, null);
            }
        }
    }

    public void prettyPrint() {
        System.out.println();

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
                if (grid[i][j].getState() == State.OCCUPIED) {
                    System.out.printf("%2c ", 'O');
                } else if (grid[i][j].getState() == State.EMPTY) {
                    System.out.printf("%2c ", ' ');
                } else if (grid[i][j].getState() == State.HIT) {
                    System.out.printf("%2c ", 'X');
                } else if (grid[i][j].getState() == State.MISS) {
                    System.out.printf("%2c ", '-');
                }
            }

            System.out.println();
        }

        System.out.println();
        System.out.println("'0' represents a ship that has not been hit");
        System.out.println("'X' represents a ship that has been hit");
        System.out.println("'-' represents a spot that has been attacked that doesn't contain a ship");
        System.out.println("blank represents a spot that hasn't been attacked yet");
        System.out.println();
        System.out.println();
    }
}
