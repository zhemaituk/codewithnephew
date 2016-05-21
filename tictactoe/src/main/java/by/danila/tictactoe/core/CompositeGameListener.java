package by.danila.tictactoe.core;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Andrei Zhemaituk
 */
public class CompositeGameListener implements GameListener {

    private Collection<GameListener> listeners = new ArrayList<>();

    public void addListener(GameListener listener) {
        listeners.add(listener);
    }

    @Override
    public void onGameStarted(Game game) {
        listeners.stream().forEach((listener) -> listener.onGameStarted(game));
    }

    @Override
    public void onMoved(Game game) {
        listeners.stream().forEach((listener) -> listener.onMoved(game));
    }

    @Override
    public void onGameFinished(Game game) {
        listeners.stream().forEach((listener) -> listener.onGameFinished(game));
    }
}
