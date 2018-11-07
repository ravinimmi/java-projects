public class GridExample {
    public static void main(String []args) {
//        generate();
        int[][] cells = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
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