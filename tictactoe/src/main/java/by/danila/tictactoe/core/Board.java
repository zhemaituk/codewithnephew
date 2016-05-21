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
                cells[i][j] = new Cell(i, j);
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

    /**
     *
     * @param player
     * @param cell
     * @return if move was allowed and successful
     */
    public boolean recordMove(Player player, Cell cell) {

        Cell cellOnBoard = cell(cell.getX(), cell.getY());
        if (cellOnBoard.getState() == CellState.OPEN) {
            cellOnBoard.setState(player.getFigure());
            return true;
        } else {
            return false;
        }

    }
}
