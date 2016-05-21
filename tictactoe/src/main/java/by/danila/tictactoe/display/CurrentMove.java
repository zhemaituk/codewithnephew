package by.danila.tictactoe.display;

import by.danila.tictactoe.core.Cell;

/**
 * @author Andrei Zhemaituk
 */
class CurrentMove {

    private static Object monitor = new Object();

    static Cell current;

    static void set(Cell cell) {
        synchronized (monitor) {
            current = cell;
            monitor.notify();
        }
    }

    static Cell get() {
        synchronized (monitor) {
            while (true) {
                if (current != null) {
                    Cell result = current;
                    current = null;
                    return result;
                }

                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
