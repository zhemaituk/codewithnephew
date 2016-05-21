package by.danila.tictactoe.record;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
        result.append(GameSerializer.serialize(game));
    }

    @Override
    public void onGameFinished(Game game) {
        if (game.getWinner() != null) {
            result.append(GameSerializer.serialize(game.getWinner()));
        } else {
            result.append("tie");
        }

        try (FileWriter writer = new FileWriter(new File(folder,
                ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT).replace(":", "-")))) {
            writer.append(result.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
