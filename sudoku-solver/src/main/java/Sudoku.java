class Sudoku {
    private int[][] init_cells;
    private int[][] cells;

    Sudoku(int[][] cells) {
        init_cells = new int[9][9];
        this.cells = new int[9][9];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                this.cells[i][j] = cells[i][j];
                init_cells[i][j] = cells[i][j];
            }
        }
    }

    void print() {
        System.out.println("Grid:");
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println();
        }
    }

    int[][] getSolved() {
        if(!isSolved())
            solve();
        return cells;
    }

    private boolean isSolved() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if(!isValid(i, j))
                    return false;
            }
        }
        return true;
    }

    void solve() {
        int iter= 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {

                while(!isValid(i, j) && !areValuesExhausted(i, j) && isCellEditable(i, j)) {
                    cells[i][j] = getNextCandidate(cells[i][j]);
                }

                if(!isValid(i, j)) {

                    if(isCellEditable(i, j))
                        cells[i][j] = 0;

                    do {
                        if (j >= 1) {
                            j -= 1;
                        } else {
                            j = cells.length - 1;
                            i = i - 1;
                        }
                    } while(!isCellEditable(i, j));

                    cells[i][j] += 1;
                    j--;
                }
                iter++;
            }
        }
        System.out.println("No. of iterations = " + iter);
    }

    private boolean isCellEditable(int i, int j) {
        return init_cells[i][j] == 0;
    }

    private boolean areValuesExhausted(int i, int j) {
        return cells[i][j] == -1;
    }

    private boolean isValid(int i, int j) {
        boolean res = isValidValue(cells[i][j]);
        res = res && isValidRow(i, j);
        res = res && isValidCol(i, j);
        res = res && isValidBox(i, j);
        return res;
    }

    private boolean isValidValue(int val) {
        return (val >= 1 && val <= 9);
    }

    private boolean isValidRow(int row, int col) {
        for(int t=0; t<cells.length; t++) {
            if (cells[row][t] != 0 && t != col && cells[row][t] == cells[row][col])
                return false;
        }
        return true;
    }

    private boolean isValidCol(int row, int col) {
        for(int t=0; t<cells.length; t++) {
            if (cells[t][col] != 0 && t != row && cells[t][col] == cells[row][col])
                return false;
        }
        return true;
    }

    private boolean isValidBox(int row, int col) {
        int len = 3;
        int r = len * (row / len);
        int c = len * (col / len);

        for (int i = r; i < r + len; i++) {
            for (int j = c; j < c + len; j++) {
                if (cells[i][j] != 0 && !(i == row && j == col) && cells[i][j] == cells[row][col])
                    return false;
            }
        }
        return true;
    }

    private int getNextCandidate(int num) {
        if (num >= cells.length)
            return -1;
        return num + 1;
    }
}
