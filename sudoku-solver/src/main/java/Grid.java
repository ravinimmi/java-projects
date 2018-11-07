public class Grid {
    private int[][] cells = new int[9][9];

    public void print() {
        System.out.println("Grid:");
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                System.out.print(this.cells[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setCells(int[][] cells) {
        this.cells = cells;
    }

    public int[][] getCells() {
        return this.cells;
    }

    public void solve() {
        int iter= 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {

                while(!isValid(i, j) && cells[i][j] != -1) {
                    cells[i][j] = setNextCandidate(cells[i][j]);
                }

                if(cells[i][j] == -1) {
                    cells[i][j] = 0;
                    if(j - 1 >= 0){
                        j -= 2;
                    }
                    else {
                        j = 7;
                        i = i - 1;
                    }
                    cells[i][j+1] += 1;
                    continue;
                }
                iter++;
            }
        }
        System.out.println("No. of iterations = " + iter);
    }

    private boolean isValid(int i, int j) {
        if(cells[i][j] < 1 || cells[i][j] > 9)
            return false;

        for(int t=i - 1; t>=0; t--) {
            if (this.cells[t][j] == this.cells[i][j])
                return false;
        }

        for(int t=j - 1; t>=0; t--) {
            if (this.cells[i][t] == this.cells[i][j])
                return false;
        }

        return true;
    }

    private int setNextCandidate(int num) {
        if (num >= cells.length)
            return -1;
        return num + 1;
    }
}
