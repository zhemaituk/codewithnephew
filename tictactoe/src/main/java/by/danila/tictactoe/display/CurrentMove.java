package by.danila.tictactoe.display;

/**
 * @author Andrei Zhemaituk
 */
class CurrentMove {

    private static Object monitor = new Object();

    static class Index2D {

        int i;
        int j;
    }

    static Index2D current;

    static void set(int i, int j) {
        Index2D index = new Index2D();
        index.i = i;
        index.j = j;

        synchronized (monitor) {
            current = index;
            monitor.notify();
        }
    }

    static Index2D get() {
        synchronized (monitor) {
            while (true) {
                if (current != null) {
                    Index2D result = current;
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
