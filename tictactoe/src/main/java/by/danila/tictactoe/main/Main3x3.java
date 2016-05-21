package by.danila.tictactoe.main;

import java.util.ArrayList;
import java.util.List;

import by.danila.tictactoe.ai.DummyPlayer;
import by.danila.tictactoe.core.CellState;
import by.danila.tictactoe.core.Game;
import by.danila.tictactoe.core.Game3x3;
import by.danila.tictactoe.core.Player;
import by.danila.tictactoe.display.TicTacToeFrame;

/**
 * @author Andrei Zhemaituk
 */
public class Main3x3 {

    public static void main(String[] args) {

        List<Player> players = new ArrayList<>();

        players.add(new DummyPlayer(CellState.CROSS, "Danila"));
        players.add(new DummyPlayer(CellState.NOUGHT, "Tolik"));

        Game game = new Game3x3(players);

        TicTacToeFrame frame = new TicTacToeFrame();

        frame.render(game);
    }
}
