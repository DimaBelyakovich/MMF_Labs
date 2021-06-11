package traveling_salesman;

public class Main {
    public static void main(String[] args) {
        int inf = 100;

        int[][] matrix = {
                {-1, 12, 4, 5, 10, 8},
                {12, -1, 5, 4, 10, 2},
                {4, 5, -1, 10, 15, 3},
                {5, 4, 10, -1, 5, 6},
                {10, inf, inf, 5, -1, 8},
                {8, 2, 3, 6, 8, -1}
        };

        Handler.printMatrix(matrix);
        Algorithm.algorithmOfLittle(matrix);
    }
}
