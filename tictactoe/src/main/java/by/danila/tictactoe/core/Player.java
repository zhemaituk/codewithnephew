package by.danila.tictactoe.core;

/**
 * @author Andrei Zhemaituk
 */
public abstract class Player {

    private final CellState figure;

    private final String name;

    public Player(CellState figure, String name) {
        this.figure = figure;
        this.name = name;
    }

    public CellState getFigure() {
        return figure;
    }

    public abstract void move(Board board);

    @Override
    public String toString() {
        return name;
    }
}
