package by.danila.tictactoe.record;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import by.danila.tictactoe.core.Game;

/**
 * @author Andrei Zhemaituk
 */
public class FileRecorder extends Recorder {

    private File folder;

    public FileRecorder(File folder) {
        this.folder = folder;
    }

    @Override
    public void onGameFinished(Game game) {
        try (FileWriter writer = new FileWriter(new File(folder,
                ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT).replace(":", "-")))) {
            writer.append(getRecording());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
