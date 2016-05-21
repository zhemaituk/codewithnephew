package by.danila.tictactoe.ai;

import java.util.Random;

import by.danila.tictactoe.core.Board;
import by.danila.tictactoe.core.Cell;
import by.danila.tictactoe.core.CellState;
import by.danila.tictactoe.core.Player;

/**
 * @author Andrei Zhemaituk
 */
public class RandomPlayer extends Player {

    private Random random = new Random();

    public RandomPlayer(CellState figure, String name) {
        super(figure, name);
    }

    @Override
    public Cell move(Board board) {
        return board.cell(random.nextInt(board.getWidth()), random.nextInt(board.getHeight()));
    }
}
