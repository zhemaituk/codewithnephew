package by.danila.tictactoe.display;

import javax.swing.*;

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

        try {
            board.recordMove(this, index.i, index.j);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, this + ", " + e.getLocalizedMessage());

            // Try again.
            move(board);
        }
    }
}
