package org.example;

public class Main {
    public static void main(String args[])
    {
        int arr[] = {82, 24, 234, 22, 1, 2};

        HeapSort.sort(arr);

        System.out.println("Sorted array is");
        int n = arr.length;
        for (int j : arr) System.out.print(j + " ");
        System.out.println();
    }
}