package by.danila.tictactoe.core;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Andrei Zhemaituk
 */
public class Game3x3 implements Game {

    private Board board = new Board(3, 3);

    private Collection<Player> players;

    private Player winner;

    private Collection<GameListener> listeners = new ArrayList<>();

    public Game3x3(Collection<Player> players) {
        this.players = players;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Player getWinner() {
        return winner;
    }

    @Override
    public void addListener(GameListener listener) {
        listeners.add(listener);
    }

    @Override
    public void play() {
        while (true) {
            for (Player player : players) {
                player.move(board);

                if (playerWon(player)) {
                    winner = player;
                    return;
                }

                if (theEnd()) {
                    winner = null;
                    return;
                }
            }
        }
    }

    private boolean theEnd() {
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                if (board.cell(i, j).getState() == CellState.OPEN) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean playerWon(Player player) {
        for (int i = 0; i < board.getWidth(); i++) {
            boolean won = true;
            for (int j = 0; j < board.getHeight(); j++) {
                if (board.cell(i, j).getState() != player.getFigure()) {
                    won = false;
                    break;
                }
            }

            if (won) {
                return true;
            }
        }

        for (int i = 0; i < board.getHeight(); i++) {
            boolean won = true;
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.cell(i, j).getState() != player.getFigure()) {
                    won = false;
                    break;
                }
            }

            if (won) {
                return true;
            }
        }

        boolean won = true;
        for (int i = 0; i < board.getHeight(); i++) {
            if (board.cell(i, i).getState() != player.getFigure()) {
                won = false;
                break;
            }
        }

        return won;
    }
}
