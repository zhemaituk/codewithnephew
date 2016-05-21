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
    public void onGameStarted() {
        listeners.stream().forEach(GameListener::onGameStarted);
    }

    @Override
    public void onMoved() {
        listeners.stream().forEach(GameListener::onMoved);
    }

    @Override
    public void onGameFinished() {
        listeners.stream().forEach(GameListener::onGameFinished);
    }
}
