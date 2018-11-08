class Cell {
    private static final int MIN_CELL_VALUE = 1;
    private static final int MAX_CELL_VALUE = 9;

    private Integer value;
    private boolean editable;
    private CellPosition position;

    Cell(int val, int row, int col) {
        setValue(val);
        setEditable();
        setPosition(row, col);
    }

    void setValue(Integer val) {
        if(val == null || val == 0) {
            value = null;
        }
        else {
            value = val;
        }
    }

    private void setEditable() {
        editable = (value == null);
    }

    Integer getValue() {
        return value;
    }

    private void setPosition(int row, int col) {
        position = new CellPosition(row, col);
    }

    CellPosition getPosition() {
        return position;
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

    boolean isValueValid() {
        return (value != null && value >= MIN_CELL_VALUE && value <= MAX_CELL_VALUE);
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
    private static final int GRID_LEN = 9;
    private Cell[][] cells = new Cell[GRID_LEN][GRID_LEN];

    Sudoku(int[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                this.cells[i][j] = new Cell(cells[i][j], i, j);
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

                if (cell.isEditable()) {
                    while (!isValid(cell) && !areValuesExhausted(cell)) {
                        cell.setValue(getNextCandidate(cell.getValue()));
                    }
                }

                if(!isValid(cell)) {
                    if(cell.isEditable())
                        cell.setValue(null);
                    CellPosition pos = getPrevEditableCellPos(cell.getPosition());
                    cell = cells[pos.row][pos.col];
                    cell.setValue(cell.getValue() + 1);

                    i = pos.row;
                    j = pos.col - 1;
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
                output[i][j] = cells[i][j].getValue();
            }
        }
        return output;
    }

    private boolean isSolved() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if(!isValid(cells[i][j]))
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
                col = GRID_LEN - 1;
            }
        } while(!cells[row][col].isEditable());

        return cells[row][col].getPosition();
    }

    private boolean areValuesExhausted(Cell cell) {
        return cell.getValue() != null && cell.getValue() == -1;
    }

    private boolean isValid(Cell cell) {
        CellPosition pos = cell.getPosition();
        int i = pos.row;
        int j = pos.col;

        boolean res = cell.isValueValid();
        res = res && isValidRow(i, j);
        res = res && isValidCol(i, j);
        res = res && isValidBox(i, j);
        return res;
    }

    private boolean isValidRow(int row, int col) {
        for(int t=0; t<cells.length; t++) {
            Cell cell = cells[row][t];
            if (cell.getValue() != null && t != col && cell.getValue().equals(cells[row][col].getValue()))
                return false;
        }
        return true;
    }

    private boolean isValidCol(int row, int col) {
        for(int t=0; t<cells.length; t++) {
            Cell cell = cells[t][col];
            if (cell.getValue() != null && t != row && cell.getValue().equals(cells[row][col].getValue()))
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
                if (cell.getValue() != null && !(i == row && j == col) && cell.getValue().equals(cells[row][col].getValue()))
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

