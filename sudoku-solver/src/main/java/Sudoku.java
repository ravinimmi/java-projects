public class Sudoku {
    private int[][] init_cells;
    private int[][] cells;

    public Sudoku(int[][] cells) {
        this.init_cells = new int[9][9];
        this.cells = new int[9][9];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                this.cells[i][j] = cells[i][j];
                this.init_cells[i][j] = cells[i][j];
            }
        }
    }

    public void print() {
        System.out.println("Sudoku:");
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getSolved() {
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

    public void solve() {
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
                            j = this.cells.length - 1;
                            i = i - 1;
                        }
                    } while(!isCellEditable(i, j));

                    cells[i][j] += 1;
                    j--;
                }
                iter++;
//                printPartial(iter);
            }
        }
        System.out.println("No. of iterations = " + iter);
    }

    private boolean isCellEditable(int i, int j) {
        return this.init_cells[i][j] == 0;
    }

    private boolean areValuesExhausted(int i, int j) {
        return this.cells[i][j] == -1;
    }

    private void printPartial(int iter) {
        if(iter % 10000000 == 0) {
            System.out.println(iter);
            print();
        }
    }

    private boolean isValid(int i, int j) {
        boolean res = isValidValue(this.cells[i][j]);
        res = res && isValidRow(i, j);
        res = res && isValidCol(i, j);
        res = res && isValidBox(3 * (i/3), 3 * (j/3));
        return res;
    }

    private boolean isValidValue(int val) {
        return (val >= 1 && val <= 9);
    }

    private boolean isValidRow(int row, int col) {
        for(int t=col-1; t>=0; t--) {
            if (this.cells[row][t] == this.cells[row][col])
                return false;
        }
        return true;
    }

    private boolean isValidCol(int row, int col) {
        for(int t=row-1; t>=0; t--) {
            if (this.cells[t][col] == this.cells[row][col])
                return false;
        }
        return true;
    }

    private boolean isValidBox(int row, int col) {
        int[] hash = {0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if(cells[i][j] == 0)
                    continue;

                if(hash[cells[i][j] - 1] == 1)
                    return false;
                hash[cells[i][j] - 1] = 1;
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
