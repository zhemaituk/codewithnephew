package by.danila.maze.main;

import by.danila.maze.core.Maze;
import by.danila.maze.core.MazeRenderer;
import by.danila.maze.display.FrameMazeRenderer;

public class Main {

    public static void main(String[] args) {
        Maze maze = Maze.createRandomMazeWithTunnel(25, 25);

        MazeRenderer renderer = new FrameMazeRenderer();

        renderer.render(maze);
    }
}
