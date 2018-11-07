public class GridExample {
    public static void main(String []args) {
//        int[][] cells = {
//            {8, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 3, 6, 0, 0, 0, 0, 0},
//            {0, 7, 0, 0, 9, 0, 2, 0, 0},
//            {0, 5, 0, 0, 0, 7, 0, 0, 0},
//            {0, 0, 0, 0, 4, 5, 7, 0, 0},
//            {0, 0, 0, 1, 0, 0, 0, 3, 0},
//            {0, 0, 1, 0, 0, 0, 0, 6, 8},
//            {0, 0, 8, 5, 0, 0, 0, 1, 0},
//            {0, 9, 0, 0, 0, 0, 4, 0, 0},
//        };
        int[][] cells = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9},
        };

        Grid grid = new Grid(cells);
        grid.print();
        long startTime = System.nanoTime();
        grid.solve();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;
        grid.print();
        System.out.println("Time taken to solve = " + duration + " ms");
    }
}