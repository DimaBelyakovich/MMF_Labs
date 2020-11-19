package task1;

import helpers.RandomGenerators;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(RandomGenerators.getRandomInt(5,10000));
        Service.fillMatrixRandomValue(matrix, 5, 10_000);
        System.out.println(matrix.getN());
        int k = 5;
        long avgTime = 0;
        for(int i = 0; i < k; i++){
            System.out.println("Sum: " + Service.calculateTime(matrix)[0]);
            System.out.println("Time: " + (Service.calculateTime(matrix)[1]));
            avgTime += Service.calculateTime(matrix)[1];
        }
        System.out.println(avgTime/k);
    }
}
