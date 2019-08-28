package com.tks.general;
/**
 * Provides static methods of different Searching algorithms
 */
public class SearchAlgo {
    
    /**
     * Accepts an array and a value to be searched
     * Linearly searches the array
     * Time Complexity O(n)
     * @param array The array to be searched in
     * @param val Value to be searched
     * @param start Starting index of array
     * @param last Last index of array (excluded)
     * @return Array index if found else -1
     */
    public static <T extends Comparable<T>> int LinearSearch(T array[], T val, int start, int last) {
        for (int i = start; i < last; i++) {
            if (array[i] == val) return i;
        }
        return -1;
    } 

    /**
     * @see SearchAlgo#LinearSearch(array, val)
     */
    public static <T extends Comparable<T>> int LinearSearch(T array[], T val) {
        return LinearSearch(array, val, 0, array.length);
    }

    /**
     * Assumes array to be sorted
     * Accepts an array and a value to be searched
     * Binary searching algorithm
     * Time Complexity O(log n)
     * @param array The array to be searched in
     * @param val Value to be searched
     * @return Array index if found else -1
     */
    public static <T extends Comparable<T>> int BinarySearch(T array[], T val) {
        int low = 0;
        int high = array.length - 1;
        int middle;
        while (low <= high) {
            middle = (low + high) / 2;
            if (array[middle].compareTo(val) == 0) return middle;
            else if (array[middle].compareTo(val) < 0)  low = middle+1;
            else high = middle-1;
        }
        return -1;
    }

    /**
     * Assumes array to be sorted
     * Accepts an array and a value to be searched
     * Binary searching algorithm recursively
     * Time Complexity O(log n)
     * @param array The array to be searched in
     * @param val Value to be searched
     * @param low start index of array
     * @param high last index of array (excluded)
     * @return Array index if found else -1
     */
    public static <T extends Comparable<T>> int BinarySearch(T array[], T val, int low, int high) {
        int middle = (low + high) / 2;
        if (high < low) return -1;
        if (array[middle].compareTo(val) == 0) return middle;
        else if (array[middle].compareTo(val) < 0) return BinarySearch(array, val, middle+1, high);
        else return BinarySearch(array, val, low, middle-1);
    }
}