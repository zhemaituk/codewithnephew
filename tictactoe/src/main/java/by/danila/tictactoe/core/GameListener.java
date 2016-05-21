package by.danila.tictactoe.core;

/**
 * @author Andrei Zhemaituk
 */
public interface GameListener {

    void onGameStarted();

    void onMoved();

    void onGameFinished();
}
