package task4;

import helpers.RandomGenerators;
import task4.sort.BubbleSort;
import task4.sort.InsertionSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int n = 1_000_000;
        int [] listBubbleOpt = new int[n];
        int [] listBubble = new int[n];
        int [] listInsert = new int[n];

        for (int i =0; i<n; i++) {
            int r = RandomGenerators.getRandomInt(1, 10_000);
            listBubble[i] = r;
            listBubbleOpt[i] = r;
            listInsert[i] = r;
        }

        long start = System.nanoTime();
        long end = System.nanoTime();

        System.out.println("Bubble optimized");
        start = System.nanoTime();
        BubbleSort.bubbleSortOptimized(listBubbleOpt);
        end = System.nanoTime();
        System.out.println("Time: " +(end - start));

        System.out.println("Bubble");
        start = System.nanoTime();
        BubbleSort.bubbleSort(listBubble);
        end = System.nanoTime();
        System.out.println("Time: " +(end - start));

        System.out.println("Insertion");
        start = System.nanoTime();
        InsertionSort.insertionSort(listInsert);
        end = System.nanoTime();
        System.out.println("Time: " +(end - start));

    }
}
