package by.danila.tictactoe.display;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import by.danila.tictactoe.core.Cell;
import by.danila.tictactoe.core.Game;
import by.danila.tictactoe.core.GameListener;

/**
 * @author Andrei Zhemaituk
 */
public class TicTacToeFrame {

    private static final int CELL_SIZE = 40;

    public void render(Game game) {

        JFrame frame = new JFrame("TicTacToe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        final JPanel paintPanel = new JPanel();
        paintPanel.setPreferredSize(new Dimension(game.getBoard().getWidth() * CELL_SIZE, game.getBoard().getHeight() * CELL_SIZE));

        frame.getContentPane().add(paintPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        repaint(game, (Graphics2D) paintPanel.getGraphics());

        paintPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                CurrentMove.set(e.getY() / CELL_SIZE, e.getX() / CELL_SIZE);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        game.addListener(new GameListener() {
            @Override
            public void onGameStarted() {
            }

            @Override
            public void onMoved() {
                repaint(game, (Graphics2D) paintPanel.getGraphics());
            }

            @Override
            public void onGameFinished() {
                JOptionPane.showMessageDialog(null, game.getWinner() != null ? game.getWinner() + ", you win!" : "Tied. Piece.");
            }
        });

        game.play();
    }

    private void repaint(Game game, Graphics2D graphics) {
        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < game.getBoard().getHeight(); i++) {
                for (int j = 0; j < game.getBoard().getWidth(); j++) {
                    graphics.setColor(Color.BLACK);
                    graphics.drawRect(
                            CELL_SIZE * j,
                            CELL_SIZE * i,
                            CELL_SIZE,
                            CELL_SIZE);

                    graphics.setColor(cellColor(game.getBoard().cell(i, j)));
                    graphics.fillRect(
                            CELL_SIZE * j,
                            CELL_SIZE * i,
                            CELL_SIZE,
                            CELL_SIZE);
                }
            }
        });
    }

    private Color cellColor(Cell cell) {
        switch (cell.getState()) {
            case CROSS:
                return Color.BLUE;
            case NOUGHT:
                return Color.RED;
            case OPEN:
                return Color.WHITE;
            default:
                throw new IllegalStateException("Unsupported cell type: " + cell.getState());
        }
    }

}
