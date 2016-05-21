package by.danila.tictactoe.ai;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import by.danila.tictactoe.core.Board;
import by.danila.tictactoe.core.Cell;
import by.danila.tictactoe.core.CellState;
import by.danila.tictactoe.core.Player;
import by.danila.tictactoe.record.GameSerializer;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * @author Andrei Zhemaituk
 */
public class LearningPlayer extends Player {

    private final File recordings;

    public LearningPlayer(CellState figure, String name, File recordings) {
        super(figure, name);
        this.recordings = recordings;
    }

    private Multiset<String> xstat = HashMultiset.create();
    private Multiset<String> ostat = HashMultiset.create();
    private Multiset<String> tstat = HashMultiset.create();

    private void learn() {
        File[] files = recordings.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            learnOnGame(file);
        }
    }

    private void learnOnGame(File file) {
        String game = readFile(file.toPath());
        String[] moves = game.split("\\n\\n");

        CellState winner = GameSerializer.deserializeWinner(moves[moves.length - 1]);

        Multiset<String> stat;
        if (winner == null) {
            stat = tstat;
        } else if (winner == CellState.CROSS) {
            stat = xstat;
        } else if (winner == CellState.NOUGHT) {
            stat = ostat;
        } else {
            throw new IllegalStateException("Who was the winner? \n" + game);
        }

        Collections.addAll(stat, moves);
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
        int me = xstat.count(position);
        int opponent = ostat.count(position);
        int tie = tstat.count(position);
        int total = me + opponent + tie;

        if (getFigure() == CellState.NOUGHT) {
            int temp = me;
            me = opponent;
            opponent = temp;
        }

        double CURIOSITY_LEVEL = 0.5;
        return total == 0 ? CURIOSITY_LEVEL : (me + tie) / total;
    }

    private static String readFile(Path path) {
        byte[] encoded;
        try {
            encoded = Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
