package by.danila.tictactoe.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import by.danila.tictactoe.ai.RandomPlayer;
import by.danila.tictactoe.core.CellState;
import by.danila.tictactoe.core.Game;
import by.danila.tictactoe.core.Game3x3;
import by.danila.tictactoe.core.Player;
import by.danila.tictactoe.display.HumanPlayer;
import by.danila.tictactoe.display.TicTacToeFrame;
import by.danila.tictactoe.record.Recorder;

/**
 * @author Andrei Zhemaituk
 */
public class Main3x3 {

    public static void main(String[] args) {

        List<Player> players = new ArrayList<>();

        players.add(new RandomPlayer(CellState.CROSS, "Danila"));
        players.add(new HumanPlayer(CellState.NOUGHT, "Tolik"));

        Game game = new Game3x3(players);

        File recordings = new File("recordings");
        recordings.mkdirs();
        game.addListener(new Recorder(recordings));

        TicTacToeFrame frame = new TicTacToeFrame();

        frame.render(game);
    }
}
