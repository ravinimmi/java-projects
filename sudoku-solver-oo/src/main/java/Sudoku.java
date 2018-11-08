class Cell {
    Integer value;
    private boolean editable;

    Cell(int val) {
        if(val != 0) {
            value = val;
            editable = false;
        }
        if(val == 0) {
            value = null;
            editable = true;
        }
    }

    @Override
    public String toString() {
        if(value != null) {
            return value.toString();
        }
        else {
            return "-";
        }
    }

    boolean isEditable() {
        return editable;
    }

}


class CellPosition {
    int row;
    int col;

    CellPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
}


class Sudoku {
    private static final int MIN_CELL_VALUE = 1;
    private static final int MAX_CELL_VALUE = 9;

    private static final int GRID_LEN = 9;
    private Cell[][] cells = new Cell[GRID_LEN][GRID_LEN];

    Sudoku(int[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                this.cells[i][j] = new Cell(cells[i][j]);
            }
        }
    }

    void print() {
        System.out.println("Grid:");
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                System.out.print(cells[i][j] + " ");
                if(j==2 || j==5)
                    System.out.print('|');
            }
            if(i == 2 || i == 5) {
                System.out.println();
                for (int j = 0; j <= 2 * cells[i].length; j++) {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }

    public int[][] getSolved() {
        if(!isSolved())
            solve();
        return to2DArray();
    }

    void solve() {
        int iter= 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = cells[i][j];

                while(!isValid(i, j) && !areValuesExhausted(cell) && cell.isEditable()) {
                    cell.value = getNextCandidate(cell.value);
                }

                if(!isValid(i, j)) {

                    if(cell.isEditable())
                        cell.value = null;

                    CellPosition pos = getPrevEditableCellPos(new CellPosition(i, j));
                    i = pos.row;
                    j = pos.col;

                    cells[i][j].value += 1;
                    j--;
                }
                iter++;
            }
        }
        System.out.println("No. of iterations = " + iter);
    }

    private int[][] to2DArray() {
        int[][] output = new int[GRID_LEN][GRID_LEN];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                output[i][j] = cells[i][j].value;
            }
        }
        return output;
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

    private CellPosition getPrevEditableCellPos(CellPosition pos) {
        int row = pos.row;
        int col = pos.col;

        do {
            if (col >= 1) {
                col = col - 1;
            } else {
                row = row - 1;
                col = cells.length - 1;
            }
        } while(!cells[row][col].isEditable());

        return new CellPosition(row, col);
    }

    private boolean areValuesExhausted(Cell cell) {
        return cell.value != null && cell.value == -1;
    }

    private boolean isValid(int i, int j) {
        boolean res = isValidValue(cells[i][j]);
        res = res && isValidRow(i, j);
        res = res && isValidCol(i, j);
        res = res && isValidBox(i, j);
        return res;
    }

    private boolean isValidValue(Cell cell) {
        return (cell.value != null && cell.value >= MIN_CELL_VALUE && cell.value <= MAX_CELL_VALUE);
    }

    private boolean isValidRow(int row, int col) {
        for(int t=0; t<cells.length; t++) {
            Cell cell = cells[row][t];
            if (cell.value != null && t != col && cell.value.equals(cells[row][col].value))
                return false;
        }
        return true;
    }

    private boolean isValidCol(int row, int col) {
        for(int t=0; t<cells.length; t++) {
            Cell cell = cells[t][col];
            if (cell.value != null && t != row && cell.value.equals(cells[row][col].value))
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
                Cell cell = cells[i][j];
                if (cell.value != null && !(i == row && j == col) && cell.value.equals(cells[row][col].value))
                    return false;
            }
        }
        return true;
    }

    private int getNextCandidate(Integer num) {
        if (num == null)
            return 1;
        if (num >= cells.length)
            return -1;
        return num + 1;
    }
}

