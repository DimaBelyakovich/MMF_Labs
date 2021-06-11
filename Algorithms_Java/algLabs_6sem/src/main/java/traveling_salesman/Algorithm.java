package traveling_salesman;

public class Algorithm {
    public static void algorithmOfLittle(int[][] matrix) {
        int[][][] res = new int[1][0][2];
        int[][] D = getResultOfFloyd(matrix);

        int[] rows = new int[matrix.length];
        int[] columns = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            rows[i] = i+1;
            columns[i] = i+1;
        }
        int dlina = 0;

        int cnt= 1;
        do{
            Handler.printMatrixD(D,rows,columns);
            dlina+=subtractMinForRows(D)+subtractMinForColumns(D);
            subtractMinForRows(D);
            subtractMinForColumns(D);

            int[]zero = findZeroWithMaxDegree(D);

            int v1 = rows[zero[0]];
            int v2 = columns[zero[1]];

            D[zero[0]][zero[1]] = zero[2];

            D = deleteRowAndColumn(D,zero[0],zero[1]);
            rows = deleteRowOrColumn(rows, zero[0]);
            columns = deleteRowOrColumn(columns,zero[1]);

            if (isRowOrColumnExisted(rows,v2)&& isRowOrColumnExisted(columns,v1)){
                D[indexOf(rows,v2)][indexOf(columns,v1)] = -1;
            }else{
                int []zeros = findRowAndColumnWithoutZero(D);
                D[zeros[0]][zeros[1]] = -1;
            }

            System.out.println("{"+v1+";"+v2+"}");
            res = addNewEdge(res,new int[]{v1,v2});
            Handler.printMatrixD(D,rows,columns);

            cnt++;
        }while(cnt<5);

        int[][]lastRoutes = getLastRoutes(D,rows,columns);
        //System.out.println("{"+lastRoutes[0][0]+";"+lastRoutes[0][1]+"}");
        //System.out.println("{"+lastRoutes[1][0]+";"+lastRoutes[1][1]+"}");

        res = addNewEdge(res,lastRoutes[0]);
        res = addNewEdge(res,lastRoutes[1]);

        Handler.printRoute(res);
        Handler.sortRoute(res[0]);
        Handler.printRoute(res);
        System.out.println(dlina);
    }

    private static int[][][] addNewEdge(int[][][]route,int []edge){
        int[][][]copy = new int[route.length][0][2];
        for (int i = 0; i < route.length-1; i++) {
            copy[i] = route[i];
        }

        copy[route.length-1] = new int[route[route.length-1].length+1][2];

        for (int i = 0; i < route[route.length-1].length; i++) {
            copy[route.length-1][i] = route[route.length-1][i];
        }
        copy[route.length-1][route[route.length-1].length] = edge;
        return copy;
    }

    private static int[][] getLastRoutes(int[][]matrix,int[]rows,int[]columns){
        int[] v1;
        if (matrix[0][0]==-1){
            v1 = new int[]{rows[0],columns[1]};
        }else{
            v1 = new int[]{rows[0],columns[0]};
        }

        int[]v2;
        if (matrix[1][0] == -1){
            v2 = new int[]{rows[1],columns[1]};
        }else {
            v2 = new int[]{rows[1],columns[0]};
        }

        return new int[][]{v1,v2};
    }

    private static int indexOf(int []array,int element){
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element){
                return i;
            }
        }
        return -1;
    }

    private static int[] findRowAndColumnWithoutZero(int [][]matrix){
        int[]rowAndColumn = new int[]{0, 0};

        for (int i = 0; i < matrix.length; i++) {
            boolean flag = false;

            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == -1){
                    flag = true;
                }
            }

            if (!flag){
                rowAndColumn[0] = i;
                break;
            }
        }

        for (int j = 0; j < matrix.length; j++) {
            boolean flag = false;

            for (int i = 0; i < matrix.length;i++) {
                if (matrix[i][j] == -1){
                    flag = true;
                }
            }

            if (!flag){
                rowAndColumn[1] = j;
                break;
            }
        }

        return rowAndColumn;
    }

    private static boolean isRowOrColumnExisted(int[]rowsOrColumns,int rowOrColumn){
        for (int i = 0; i < rowsOrColumns.length; i++) {
            if (rowsOrColumns[i]==rowOrColumn){
                return true;
            }
        }
        return false;
    }

    private static int[][]deleteRowAndColumn(int[][]matrix,int i,int j){
        int[][]copy1 = new int[matrix.length-1][matrix.length];

        for (int k = 0; k < copy1.length; k++) {
            if (k < i) {
                copy1[k] = matrix[k];
            } else {
                copy1[k] = matrix[k + 1];
            }
        }

        int[][]copy2 = new int[copy1.length][copy1[0].length-1];

        for (int k = 0; k < copy2.length; k++) {
            for (int l = 0; l < copy2.length; l++) {
                if (l < j) {
                    copy2[k][l] = copy1[k][l];
                } else {
                    copy2[k][l] = copy1[k][l + 1];
                }
            }
        }

        return copy2;
    }

    private static int[] deleteRowOrColumn(int[]rows, int index){
        int[]copy = new int[rows.length-1];

        for (int i = 0; i < copy.length; i++) {
            if (i<index){
                copy[i] = rows[i];
            }else{
                copy[i] = rows[i+1];
            }
        }

        return copy;
    }

    private static int[] findZeroWithMaxDegree(int[][]matrix){
        int[]res = {0,0,0};
        int maxDegree = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j]==0){
                    int degree = findDegreeOfZero(matrix,i,j);
                    if (degree>maxDegree){
                        maxDegree = degree;
                        res[0] = i;
                        res[1] = j;
                    }
                }
            }
        }
        res[2] = maxDegree;

        return res;
    }

    private static int findDegreeOfZero(int[][] matrix,int i,int j) {
        int minForRow = 100;
        for (int k = 0; k < matrix.length; k++) {
            if (matrix[i][k] < minForRow && matrix[i][k] != -1 && k!=j) {
                minForRow = matrix[i][k];
            }
        }

        int minForCol = 100;
        for (int k = 0; k < matrix.length; k++) {
            if (matrix[k][j] < minForCol && matrix[k][j] != -1 && k!=i) {
                minForCol = matrix[k][j];
            }
        }

        return minForCol + minForRow;
    }

    private static int subtractMinForColumns(int[][]matrix){
        int size = matrix.length;
        int[] min = new int[size];

        for (int j = 0; j < size; j++) {
            min[j] = 100000000;//matrix[j!=0?j-1:1][j];
            for (int i = 0; i < size; i++) {
                min[j] = matrix[i][j] == -1 ? min[j] :Math.min(min[j],matrix[i][j]);
            }

            for (int i = 0; i < size; i++) {
                if (matrix[i][j]!=-1){
                    matrix[i][j] -= min[j];
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < min.length; i++) {
            sum+=min[i];
        }
        return sum;
    }

    private static int subtractMinForRows(int [][]matrix) {
        int size = matrix.length;
        int[] min = new int[size];

        for (int i = 0; i < size; i++) {
            min[i] = 100000000;//matrix[i][i != 0 ? i-1 : 1];
            for (int j = 0; j < size; j++) {
                min[i] = matrix[i][j] == -1 ? min[i] : Math.min(min[i], matrix[i][j]);
            }

            for (int j = 0; j < size; j++) {
                if(matrix[i][j]!=-1) {
                    matrix[i][j] -= min[i];
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < min.length; i++) {
            sum+=min[i];
        }
        return sum;
    }

    public static int[][] getResultOfFloyd(int[][] matrix) {
        int size = matrix.length;
        int[][] D = matrix;
        int[][] S = fillSMatrix(matrix);

        for (int k = 0; k < size; k++) {
            changeElementsForS(S, getChangingElements(D, k), k);
            changeElementsForD(D, getChangingElements(D, k));
        }

        Handler.printMatrix(D);
        //Handler.printMatrix(S);

        return D;
    }

    private static void changeElementsForS(int[][] matrix, int[][] change, int k) {
        int size = matrix.length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j && change[i][j] != 0) {
                    matrix[i][j] = k + 1;
                }
            }
        }

    }

    private static void changeElementsForD(int[][] matrix, int[][] change) {
        int size = matrix.length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = change[i][j] != 0 ? change[i][j] : matrix[i][j];
            }
        }

    }

    private static int[][] getChangingElements(int[][] matrix, int k) {
        int size = matrix.length;
        int[][] result = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != k && j != k && matrix[k][j] + matrix[i][k] < matrix[i][j]) {
                    result[i][j] = matrix[k][j] + matrix[i][k];
                }
            }
        }

        return result;
    }

    private static int[][] fillSMatrix(int[][] matrix) {
        int size = matrix.length;
        int[][] result = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == -1 || matrix[i][j] == 100) {
                    result[i][j] = -1;
                } else {
                    result[i][j] = j + 1;
                }
            }
        }

        return result;
    }
}
