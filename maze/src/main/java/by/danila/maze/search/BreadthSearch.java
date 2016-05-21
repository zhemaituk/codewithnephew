package by.danila.maze.search;

import java.util.ArrayDeque;
import java.util.Queue;

import by.danila.maze.core.Cell;
import by.danila.maze.core.CellType;
import by.danila.maze.core.Maze;

/**
 * @author Andrei Zhemaituk
 */
public class BreadthSearch {

    private Queue<Cell> opportunities = new ArrayDeque<>();

    public Cell search(Maze maze) {
        opportunities.add(maze.entrance());

        while (!opportunities.isEmpty()) {

            Cell cell = opportunities.remove();

            for (Cell neighbour : cell.neighbours()) {
                if (neighbour.getType() == CellType.ROAD) {
                    neighbour.visit();

                    opportunities.add(neighbour);
                }

                if (neighbour.getType() == CellType.EXIT) {
                    return neighbour;
                }
            }

        }

        return null;
    }
}
