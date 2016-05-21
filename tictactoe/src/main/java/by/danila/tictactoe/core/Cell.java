package by.danila.tictactoe.core;

/**
 * @author Andrei Zhemaituk
 */
public class Cell {

    private final int x;

    private final int y;

    private CellState state = CellState.OPEN;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
