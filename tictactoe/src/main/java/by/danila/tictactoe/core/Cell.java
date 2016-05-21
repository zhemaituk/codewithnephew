package by.danila.tictactoe.core;

/**
 * @author Andrei Zhemaituk
 */
public class Cell {
    private CellState state = CellState.OPEN;

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }
}
