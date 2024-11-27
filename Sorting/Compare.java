package Sorting;

import java.util.Random;

public class Compare {
    public static void main(String[] args) {
        int size = 10000;
        int[] bubbleArr = generateRandomArray(size);
        int[] mergeArr = bubbleArr.clone();

        long startTime = System.nanoTime();
        BubbleSort.bubbleSort(bubbleArr);
        long endTime = System.nanoTime();
        long bubbleSortTime = endTime - startTime;

        startTime = System.nanoTime();
        MergeSort.mergeSort(mergeArr, 0, mergeArr.length - 1);
        endTime = System.nanoTime();
        long mergeSortTime = endTime - startTime;


        System.out.println("Bubble Sort run time: " + bubbleSortTime + " nanoseconds.");
        System.out.println("Merge Sort run time: " + mergeSortTime + " nanoseconds.");
    }

    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000);
        }
        return arr;
    }
}
