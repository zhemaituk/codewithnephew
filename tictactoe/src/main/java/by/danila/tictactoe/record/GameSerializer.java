package by.danila.tictactoe.record;

import by.danila.tictactoe.core.Board;
import by.danila.tictactoe.core.CellState;
import by.danila.tictactoe.core.Game;
import by.danila.tictactoe.core.Player;

/**
 * @author Andrei Zhemaituk
 */
public class GameSerializer {

    public static String serialize(Game game) {
        Board board = game.getBoard();
        return serialize(board);
    }

    public static String serialize(Board board) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                result.append(serialize(board.cell(i, j).getState()));
            }
            result.append("\n");
        }
        result.append("\n");

        return result.toString();
    }

    public static String serialize(Player winner) {
        if (winner != null) {
            return serialize(winner.getFigure()) + " wins";
        } else {
            return "tie";
        }

    }

    public static CellState deserializeWinner(String serialized) {
        switch (serialized.charAt(0)) {
            case 'X':
                return CellState.CROSS;
            case 'O':
                return CellState.NOUGHT;
            default:
                return null;
        }
    }


    public static char serialize(CellState state) {
        switch (state) {
            case CROSS:
                return 'X';
            case NOUGHT:
                return 'O';
            case OPEN:
                return '-';
            default:
                throw new IllegalStateException("Unsupported cell type: " + state);
        }
    }
}
