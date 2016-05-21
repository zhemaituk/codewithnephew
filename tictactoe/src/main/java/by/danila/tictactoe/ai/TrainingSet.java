package by.danila.tictactoe.ai;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import by.danila.tictactoe.core.CellState;
import by.danila.tictactoe.record.GameSerializer;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * @author Andrei Zhemaituk
 */
public class TrainingSet {

    private final File recordings;

    private Multiset<String> xstat = HashMultiset.create();
    private Multiset<String> ostat = HashMultiset.create();
    private Multiset<String> tstat = HashMultiset.create();

    public TrainingSet(File recordings) {
        this.recordings = recordings;
    }

    private void learn() {
        File[] files = recordings.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            learnOnGame(readFile(file.toPath()));
        }
    }

    public void learnOnGame(String game) {
        String[] moves = game.split("\\n\\n");

        CellState winner = GameSerializer.deserializeWinner(moves[moves.length - 1]);

        Multiset<String> stat = stat(winner);
        Collections.addAll(stat, moves);
    }

    public int count(String position, CellState winner) {
        return stat(winner).count(position);
    }

    private Multiset<String> stat(CellState winner) {
        if (winner == null) {
            return tstat;
        }

        switch (winner) {
            case CROSS:
                return xstat;
            case NOUGHT:
                return ostat;
            default:
                throw new IllegalStateException("Who was the winner? \n" + winner);
        }
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
