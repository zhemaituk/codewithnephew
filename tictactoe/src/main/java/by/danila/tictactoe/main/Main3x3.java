package by.danila.tictactoe.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import by.danila.tictactoe.ai.LearningPlayer;
import by.danila.tictactoe.ai.TrainingSet;
import by.danila.tictactoe.core.CellState;
import by.danila.tictactoe.core.Game;
import by.danila.tictactoe.core.Game3x3;
import by.danila.tictactoe.core.Player;
import by.danila.tictactoe.display.HumanPlayer;
import by.danila.tictactoe.display.TicTacToeFrame;
import by.danila.tictactoe.record.FileRecorder;
import by.danila.tictactoe.record.TrainingSetRecorder;

/**
 * @author Andrei Zhemaituk
 */
public class Main3x3 {

    public static void main(String[] args) {
        File recordings = new File("recordings");
        recordings.mkdirs();

        List<Player> players = new ArrayList<>();
        TrainingSet trainingSet = new TrainingSet(recordings);

        players.add(new LearningPlayer(CellState.CROSS, "Danila", trainingSet));
        players.add(new HumanPlayer(CellState.NOUGHT, "Tolik"));

        Game game = new Game3x3(players);

        game.addListener(new FileRecorder(recordings));
        game.addListener(new TrainingSetRecorder(trainingSet));

        TicTacToeFrame frame = new TicTacToeFrame();

        frame.render(game);
    }
}
