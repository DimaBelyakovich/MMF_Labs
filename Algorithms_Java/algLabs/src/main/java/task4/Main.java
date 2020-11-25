package task4;

import helpers.RandomGenerators;
import task4.sort.BubbleSort;
import task4.sort.InsertionSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int n = 206;
        int [] listBubble = new int[n];
        int [] listInsert = new int[n];

        for (int i =0; i<n; i++) {
            listBubble[i] = RandomGenerators.getRandomInt(1, 2132);
            listInsert[i] = listBubble[i];
        }

        System.out.println(Arrays.toString(listBubble));
        long start = System.nanoTime();
        BubbleSort.bubbleSortOptimized(listBubble);
        long end = System.nanoTime();
        System.out.println(Arrays.toString(listBubble));
        System.out.println(end - start);


        System.out.println(Arrays.toString(listInsert));
        start = System.nanoTime();
        InsertionSort.insertionSort(listInsert);
        end = System.nanoTime();
        System.out.println(Arrays.toString(listInsert));
        System.out.println(end - start);

    }
}
