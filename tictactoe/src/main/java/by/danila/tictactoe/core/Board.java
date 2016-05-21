package by.danila.tictactoe.core;

/**
 * @author Andrei Zhemaituk
 */
public class Board {

    private Cell[][] cells;

    public Board(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Width and Height must be positive");
        }

        cells = new Cell[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public int getWidth() {
        return cells.length;
    }

    public int getHeight() {
        return cells[0].length;
    }

    public Cell cell(int i, int j) {
        return cells[i][j];
    }

    public void recordMove(Player player, int i, int j) {

        if (cell(i, j).getState() == CellState.OPEN) {
            cell(i, j).setState(player.getFigure());
        } else {
            throw new IllegalStateException("Forbidden move");
        }

    }
}
