package by.danila.tictactoe.record;

import by.danila.tictactoe.ai.TrainingSet;
import by.danila.tictactoe.core.Game;

/**
 * Keeps training set (the one in memory) up to date.
 *
 * @author Andrei Zhemaituk
 */
public class TrainingSetRecorder extends Recorder {

    private final TrainingSet trainingSet;

    public TrainingSetRecorder(TrainingSet trainingSet) {
        this.trainingSet = trainingSet;
    }

    @Override
    public void onGameFinished(Game game) {
        trainingSet.learnOnGame(getRecording());
    }
}
