package by.danila.maze.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Andrei Zhemaituk
 */
public class Cell {

    private Maze maze;
    private int row;
    private int column;
    private CellType type;

    public Cell(Maze maze, int row, int column, CellType type) {
        this.maze = maze;
        this.row = row;
        this.column = column;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public Collection<Cell> neighbours() {
        List<Cell> result = new ArrayList<>();

        addNotNull(result, top());
        addNotNull(result, bottom());
        addNotNull(result, left());
        addNotNull(result, right());

        return result;
    }

    public void visit() {
        type = CellType.VISITED;
    }

    private static void addNotNull(List<Cell> list, Cell cell) {
        if (cell != null) {
            list.add(cell);
        }
    }

    private Cell top() {
        return row > 0 ? maze.cell(row - 1, column) : null;
    }

    private Cell bottom() {
        return row + 1 < maze.getHeight() ? maze.cell(row + 1, column) : null;
    }

    private Cell left() {
        return column > 0 ? maze.cell(row, column - 1) : null;
    }

    private Cell right() {
        return column + 1 < maze.getHeight() ? maze.cell(row, column + 1) : null;
    }
}
