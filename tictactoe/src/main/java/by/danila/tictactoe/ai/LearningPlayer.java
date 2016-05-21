package by.danila.tictactoe.ai;

import by.danila.tictactoe.core.Board;
import by.danila.tictactoe.core.Cell;
import by.danila.tictactoe.core.CellState;
import by.danila.tictactoe.core.Player;
import by.danila.tictactoe.record.GameSerializer;

/**
 * @author Andrei Zhemaituk
 */
public class LearningPlayer extends Player {

    private final TrainingSet trainingSet;

    public LearningPlayer(CellState figure, String name, TrainingSet trainingSet) {
        super(figure, name);
        this.trainingSet = trainingSet;
    }

    @Override
    public Cell move(Board board) {
        String boardView = GameSerializer.serialize(board);

        double bestScore = -1;
        int bestI = -1;
        int bestJ = -1;

        for (int index = 0; index < boardView.length(); index++) {
            char current = boardView.charAt(index);

            if (current == GameSerializer.serialize(CellState.OPEN)) {
                StringBuilder builder = new StringBuilder(boardView);
                builder.setCharAt(index, GameSerializer.serialize(getFigure()));

                double score = score(builder.toString());
                if (score > bestScore) {
                    bestScore = score;
                    bestJ = index % (board.getWidth() + 1);
                    bestI = index / (board.getWidth() + 1);
                }
            }
        }

        return board.cell(bestI, bestJ);
    }

    private double score(String position) {
        int me = trainingSet.count(position, CellState.CROSS);
        int opponent = trainingSet.count(position, CellState.NOUGHT);
        int tie = trainingSet.count(position, null);
        int total = me + opponent + tie;

        if (getFigure() == CellState.NOUGHT) {
            int temp = me;
            me = opponent;
            opponent = temp;
        }

        double CURIOSITY_LEVEL = 0.5;
        return total == 0 ? CURIOSITY_LEVEL : (me + tie) / total;
    }
}
