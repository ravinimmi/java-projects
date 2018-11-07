public class GridExample {
    public static void main(String []args) {
//        generate();
        int[][] cells = {
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 6, 0, 0, 0, 0, 0},
            {0, 7, 0, 0, 9, 0, 2, 0, 0},
            {0, 5, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 4, 5, 7, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 3, 0},
            {0, 0, 1, 0, 0, 0, 0, 6, 8},
            {0, 0, 8, 5, 0, 0, 0, 1, 0},
            {0, 9, 0, 0, 0, 0, 4, 0, 0},
        };

        Grid grid = new Grid();
        grid.setCells(cells);
        grid.print();
        grid.solve();
        grid.print();
    }

    public static void generate() {
        System.out.println('{');
        for (int i = 0; i < 9; i++) {
            System.out.print("\t");
            System.out.print('{');
            for (int j = 0; j < 9; j++) {
                System.out.print("0");
                if (j != 8)
                    System.out.print(", ");
            }
            System.out.println("},");
        }
        System.out.println('}');
    }
}