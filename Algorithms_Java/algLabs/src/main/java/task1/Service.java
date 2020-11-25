package task1;

import helpers.RandomGenerators;

public class Service {

    public static void fillMatrixRandomValue(Matrix matrix, Integer leftBound, Integer rightBound){
        for (Integer i = 0; i < matrix.getN(); i++ ){
            for (Integer j = 0; j < matrix.getN(); j++){
                matrix.setElement(RandomGenerators.getRandomLong(leftBound, rightBound), i, j);
            }
        }
    }

    public static long getUpperDiagSum(Matrix matrix){
        long sum = 0;
        for(int i=0; i < matrix.getN(); i++){
            for(int j = i; j < matrix.getN(); j++){
                sum += matrix.getElement(i,j);
            }
        }
        return sum;
    }

    public static long[] calculateTime(Matrix matrix){
        long[] res = new long[2];
        long start = System.nanoTime();
        res[0] = getUpperDiagSum(matrix);
        long end = System.nanoTime();
        res[1] = end - start;
        return res;
    }
}
