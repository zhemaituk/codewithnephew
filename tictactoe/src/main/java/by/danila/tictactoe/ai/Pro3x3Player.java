package by.danila.tictactoe.ai;

import by.danila.tictactoe.core.Board;
import by.danila.tictactoe.core.Cell;
import by.danila.tictactoe.core.CellState;
import by.danila.tictactoe.core.Player;

/**
 * @author Andrei Zhemaituk
 */
public class Pro3x3Player extends Player {

    public Pro3x3Player(CellState figure, String name) {
        super(figure, name);
    }

    @Override
    public Cell move(Board board) {
        return null;
    }

    // x 0 -
    // 9
    // 3 ^ 9 = 19683
}
