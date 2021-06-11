package traveling_salesman;

public class Handler {
    public static void printRoute(int[][][]route){
        for (int i = 0; i < route.length; i++) {
            for (int j = 0; j < route[i].length; j++) {
                System.out.print("{"+route[i][j][0]+";"+route[i][j][1]+"};");
            }
            System.out.println();
        }
    }

    public static void sortRoute(int[][]route){
        for (int i = 0; i < route.length; i++) {
            int nextStep = route[i][1];

            for (int j = i+1; j < route.length; j++) {
                if (route[j][0] == nextStep){
                    int[]tmp = route[j];
                    route[j] = route[i+1];
                    route[i+1] = tmp;
                    break;
                }
            }
        }
    }

    public static void printMatrix(int[][]matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%2d\t",matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printMatrixD(int[][]matrix,int []rows, int[]columns){
        System.out.printf("%2s\t",0+"|");
        for (int i = 0; i < columns.length; i++) {
            System.out.printf("%2d\t",columns[i]);
        }
        System.out.println();
        System.out.println("___________________________");

        int ind = 0;
        for (int i = 0; i < matrix.length; i++) {
            System.out.printf("%2s\t",rows[ind]+"|");
            for (int j = 0; j < matrix.length; j++) {
                System.out.printf("%2d\t",matrix[i][j]);
            }
            System.out.println();
            ind++;
        }
        System.out.println();
    }

    public static void printArray(int[]array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+";");
        }
        System.out.println();
    }
}
