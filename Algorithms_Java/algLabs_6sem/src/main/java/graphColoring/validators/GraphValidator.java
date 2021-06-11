package graphColoring.validators;

public class GraphValidator {
    public static boolean isAdjacencyMatrixValid(int[][] adjacencyMatrix) {
        if (adjacencyMatrix.length != adjacencyMatrix[1].length) {
            return false;
        }

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[i][i] != 0) {
                return false;
            }
        }

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            int row = 0;
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                row += adjacencyMatrix[i][j];
            }

            int col = 0;
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                col += adjacencyMatrix[j][i];
            }

            if (row != col) {
                return false;
            }
        }

        return true;
    }
}
