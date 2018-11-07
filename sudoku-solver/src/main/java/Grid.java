public class Grid {
    private int[][] init_cells;
    private int[][] cells;

    public Grid(int[][] cells) {
        this.init_cells = new int[9][9];
        this.cells = new int[9][9];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                this.cells[i][j] = cells[i][j];
            }
        }

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                this.init_cells[i][j] = cells[i][j];
            }
        }
    }

    public void print() {
        System.out.println("Grid:");
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                System.out.print(this.cells[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void solve() {
        int iter= 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {

                while(!isValid(i, j) && cells[i][j] != -1 && init_cells[i][j] == 0) {
                    cells[i][j] = setNextCandidate(cells[i][j]);
                }

                if(!isValid(i, j)) {
                    if(init_cells[i][j] == 0)
                        cells[i][j] = 0;

                    do {
                        if (j - 1 >= 0) {
                            j -= 1;
                        } else {
                            j = 8;
                            i = i - 1;
                        }
                    } while(init_cells[i][j] != 0);


                    cells[i][j] += 1;
                    j--;
                    continue;
                }
                iter++;
//                if(iter % 10000000 == 0) {
//                    System.out.println(iter);
//                    print();
//                }
            }
        }
        System.out.println("No. of iterations = " + iter);
    }

    private boolean isValid(int i, int j) {
        if(cells[i][j] < 1 || cells[i][j] > 9)
            return false;

        for(int t=i-1; t>=0; t--) {
            if (this.cells[t][j] == this.cells[i][j])
                return false;
        }

        for(int t=j-1; t>=0; t--) {
            if (this.cells[i][t] == this.cells[i][j])
                return false;
        }

        boolean x = isValidBox(3 * (i/3), 3 * (j/3));

        return x;
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

    private int setNextCandidate(int num) {
        if (num >= cells.length)
            return -1;
        return num + 1;
    }
}
