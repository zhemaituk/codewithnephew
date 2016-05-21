package by.danila.tictactoe.record;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import by.danila.tictactoe.core.CellState;
import by.danila.tictactoe.core.Game;
import by.danila.tictactoe.core.GameListener;

/**
 * @author Andrei Zhemaituk
 */
public class Recorder implements GameListener {

    private StringBuilder result = new StringBuilder();

    private File folder;

    public Recorder(File folder) {
        this.folder = folder;
    }

    @Override
    public void onGameStarted(Game game) {
        result = new StringBuilder();
    }

    @Override
    public void onMoved(Game game) {
        append(game);
    }

    @Override
    public void onGameFinished(Game game) {
        result.append(print(game.getWinner().getFigure()) + " wins");

        try (FileWriter writer = new FileWriter(new File(folder,
                ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT).replace(":", "-")))) {
            writer.append(result.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void append(Game game) {
        for (int i = 0; i < game.getBoard().getHeight(); i++) {
            for (int j = 0; j < game.getBoard().getHeight(); j++) {
                result.append(print(game.getBoard().cell(i, j).getState()));
            }
            result.append("\n");
        }
        result.append("\n");
    }

    private String print(CellState state) {
        switch (state) {
            case CROSS:
                return "X";
            case NOUGHT:
                return "O";
            case OPEN:
                return "-";
            default:
                throw new IllegalStateException("Unsupported cell type: " + state);
        }
    }
}
