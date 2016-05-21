package by.danila.tictactoe.display;

import by.danila.tictactoe.core.Board;
import by.danila.tictactoe.core.Cell;
import by.danila.tictactoe.core.CellState;
import by.danila.tictactoe.core.Player;

/**
 * @author Andrei Zhemaituk
 */
public class HumanPlayer extends Player {

    public HumanPlayer(CellState figure, String name) {
        super(figure, name);
    }

    @Override
    public Cell move(Board board) {
        return CurrentMove.get();
    }
}
