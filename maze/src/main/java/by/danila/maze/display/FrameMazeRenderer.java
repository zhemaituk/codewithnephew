package by.danila.maze.display;

import java.awt.*;
import javax.swing.*;

import by.danila.maze.core.Cell;
import by.danila.maze.core.Maze;
import by.danila.maze.core.MazeRenderer;

/**
 * @author Andrei Zhemaituk
 */
public class FrameMazeRenderer implements MazeRenderer {

    private static final int CELL_SIZE = 10;

    public void render(Maze maze) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Maze Runner");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setResizable(false);

            final JPanel paintPanel = new JPanel();
            paintPanel.setPreferredSize(new Dimension(maze.getWidth() * CELL_SIZE, maze.getHeight() * CELL_SIZE));

            frame.getContentPane().add(paintPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            SwingUtilities.invokeLater(() -> {
                for (int i = 0; i < maze.getHeight(); i++) {
                    for (int j = 0; j < maze.getWidth(); j++) {
                        Graphics2D graphics = (Graphics2D) paintPanel.getGraphics();
                        graphics.setColor(cellColor(maze.cell(i, j)));
                        graphics.fillRect(
                                CELL_SIZE * j,
                                CELL_SIZE * i,
                                CELL_SIZE,
                                CELL_SIZE);
                    }
                }
            });
        });
    }


    private Color cellColor(Cell cell) {
        switch (cell.getType()) {
            case WALL:
                return Color.BLACK;
            case ROAD:
                return Color.WHITE;
            case ENTRANCE:
                return Color.YELLOW;
            case EXIT:
                return Color.GREEN;
            case VISITED:
                return Color.RED;
            default:
                throw new IllegalStateException("Unsupported cell type: " + cell.getType());
        }
    }
}
