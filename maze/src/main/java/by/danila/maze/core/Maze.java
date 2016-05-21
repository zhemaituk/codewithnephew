package by.danila.maze.core;

import java.util.Random;

import by.danila.maze.search.BreadthSearch;

/**
 * @author Andrei Zhemaituk
 */
public class Maze {

    private int height;
    private int width;

    private Cell entrance;
    private Cell[][] cells;

    private Maze() {
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell cell(int i, int j) {
        return cells[i][j];
    }

    public Cell entrance() {
        return entrance;
    }

    public static Maze createRandomMazeWithTunnel(int height, int width) {
        Maze maze = null;

        Cell exit = null;

        int i = 0;
        while (exit == null) {
            System.out.println(i++);
            maze = createRandomMaze(height, width);
            BreadthSearch search = new BreadthSearch();
            exit = search.search(maze);

            //            break;
        }

        return maze;
    }

    private static Maze createRandomMaze(int height, int width) {
        Maze maze = new Maze();
        maze.height = height;
        maze.width = width;

        maze.cells = new Cell[height][width];

        CellType[] wallAndRoad = {CellType.WALL, CellType.ROAD};

        Random random = new Random();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                CellType cellType;
                if (i == 0 && j == 0) {
                    cellType = CellType.ENTRANCE;
                } else if (i == height - 1 && j == width - 1) {
                    cellType = CellType.EXIT;
                } else {
                    cellType = wallAndRoad[random.nextInt(wallAndRoad.length)];
                }

                Cell cell = new Cell(maze, i, j, cellType);
                maze.cells[i][j] = cell;

                //                int sameNeighbours = 0;
                //                for (Cell neighbour : cell.neighbours()) {
                //                    if (neighbour.getType() == cell.getType()) {
                //                        sameNeighbours++;
                //                    }
                //                }
                //
                //                if (sameNeighbours > 0) {
                //                    if (cellType == CellType.WALL) {
                //                        cellType = CellType.ROAD;
                //                    } else {
                //                        cellType = CellType.WALL;
                //                    }
                //                    cell = new Cell(maze, i, j, cellType);
                //                    maze.cells[i][j] = cell;
                //                }

                if (cellType == CellType.ENTRANCE) {
                    maze.entrance = cell;
                }
            }
        }
        return maze;
    }

}
