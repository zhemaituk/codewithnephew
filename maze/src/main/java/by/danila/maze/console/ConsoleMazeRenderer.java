package by.danila.maze.console;

import by.danila.maze.core.Cell;
import by.danila.maze.core.Maze;
import by.danila.maze.core.MazeRenderer;

/**
 * @author Andrei Zhemaituk
 */
public class ConsoleMazeRenderer implements MazeRenderer {

    public void render(Maze maze) {

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                Cell cell = maze.cell(i, j);
                System.out.print(cellCharacter(cell));
            }

            System.out.println();
        }

    }

    private Character cellCharacter(Cell cell) {
        switch (cell.getType()) {
            case WALL:
                return '\u2588';
            case ROAD:
                return ' ';
            case ENTRANCE:
                return 'o';
            case EXIT:
                return '[';
            default:
                throw new IllegalStateException("Unsupported cell type: " + cell);
        }
    }
}
