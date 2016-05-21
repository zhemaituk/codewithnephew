package by.danila.tictactoe.core;

import java.util.Collection;

/**
 * @author Andrei Zhemaituk
 */
public class Game3x3 implements Game {

    private Board board;

    private Collection<Player> players;

    private Player winner;

    private CompositeGameListener listener = new CompositeGameListener();

    public Game3x3(Collection<Player> players) {
        restart();
        this.players = players;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void restart() {
        board = new Board(3, 3);
        winner = null;
    }

    @Override
    public Player getWinner() {
        return winner;
    }

    @Override
    public void addListener(GameListener listener) {
        this.listener.addListener(listener);
    }

    @Override
    public void play() {
        listener.onGameStarted(this);

        while (true) {
            for (Player player : players) {

                while (true) {
                    Cell cell = player.move(board);
                    if (board.recordMove(player, cell)) {
                        break;
                    }
                }

                listener.onMoved(this);

                if (playerWon(player)) {
                    winner = player;

                    listener.onGameFinished(this);
                    return;
                }

                if (theEnd()) {
                    winner = null;

                    listener.onGameFinished(this);
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

            won = true;
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.cell(j, i).getState() != player.getFigure()) {
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

        if (won) {
            return true;
        }

        won = true;
        for (int i = 0; i < board.getHeight(); i++) {
            if (board.cell(i, board.getHeight() - i - 1).getState() != player.getFigure()) {
                won = false;
                break;
            }
        }

        return won;
    }
}
