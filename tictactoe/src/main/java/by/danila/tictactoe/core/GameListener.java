package by.danila.tictactoe.core;

/**
 * @author Andrei Zhemaituk
 */
public interface GameListener {

    void onGameStarted(Game game);

    void onMoved(Game game);

    void onGameFinished(Game game);
}
