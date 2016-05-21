package by.danila.tictactoe.display;

import by.danila.tictactoe.core.Board;
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
    public void move(Board board) {
        CurrentMove.Index2D index = CurrentMove.get();

        board.recordMove(this, index.i, index.j);
    }
}
