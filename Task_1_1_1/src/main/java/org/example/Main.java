package org.example;

public class Main {
    public static void main(String args[])
    {
        int arr[] = {82, 24, 234, 22, 1, 2};

        HeapSort.heapsort(arr);

        System.out.println("Sorted array is");
        HeapSort.printArray(arr);
    }
}