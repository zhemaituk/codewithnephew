package by.danila.tictactoe.ai;

import by.danila.tictactoe.core.Board;
import by.danila.tictactoe.core.Cell;
import by.danila.tictactoe.core.CellState;
import by.danila.tictactoe.core.Player;

public class DummyPlayer extends Player {

    public DummyPlayer(CellState figure, String name) {
        super(figure, name);
    }

    @Override
    public Cell move(Board board) {
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                if (board.cell(i, j).getState() == CellState.OPEN) {
                    return board.cell(i, j);
                }
            }
        }

        throw new IllegalStateException("No possible moves!");
    }
}