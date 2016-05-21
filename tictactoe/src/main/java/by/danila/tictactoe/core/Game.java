package by.danila.tictactoe.core;

/**
 * @author Andrei Zhemaituk
 */
public interface Game {

    Board getBoard();

    void restart();

    void play();

    Player getWinner();

    void addListener(GameListener listener);
}
