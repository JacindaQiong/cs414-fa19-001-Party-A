private static final int OFF_BOARD = -1;

    public interface Direction {
        int at(int square, int distance);
        Direction LEFT = KingsTable::left;
        Direction RIGHT = KingsTable::right;
        Direction UP = KingsTable::up;
        Direction DOWN = KingsTable::down;
        Direction[] DIRS = {LEFT, RIGHT, UP, DOWN};
    }


    private static char[] calculateCoverage() {
        char[] coverage = new char[ROWS * ROWS];
        Arrays.fill(coverage, '.');
        for (int i = 0; i < coverage.length; i++) {
            char mover = board[i];
            if (mover == 'X' || mover == 'O' || mover == 'H') {
                for (Direction dir : Direction.DIRS) {
                    for (int j = 1; j < ROWS; j++) {
                        int neighbor = dir.at(i, j);
                        if (neighbor != OFF_BOARD) {
                            if (board[neighbor] == '-') {
                                coverage[neighbor] = cover(mover, coverage[neighbor]);
                            } else if (mover == 'H' && (board[neighbor] == '#' || board[neighbor] == 'T')) {
                                coverage[neighbor] = cover(mover, coverage[neighbor]);
                            } else if (board[neighbor] == 'X' || board[neighbor] == 'O' || board[neighbor] == 'H') {
                                break;
                            }
                        } else { break; }
                    }

                }
            }
        }
        return coverage;
    }
