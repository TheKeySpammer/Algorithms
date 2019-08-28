package com.tks;

import static com.tks.general.SearchAlgo.LinearSearch;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tks.general.SearchAlgo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Search Algorithm tests")
class SearchAlgoTest {
    private Integer array[];

    SearchAlgoTest() {
        array = new Integer[50];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random()*(100)+2);
        }
    }

    @Test
    void linearSearchTest() {
        for (int i = 0; i < 10; i++) {
            int randomIndex = (int) Math.random()*(array.length-1);
            assertEquals(randomIndex, LinearSearch(array, array[randomIndex]));
            assertEquals(-1, LinearSearch(array, (int)Math.random()*(500) + 200));
        }
    }


    @Test
    void binarySearchTest() {
        Integer a[] = new Integer[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = i*5;
        }
        for (int i = 0; i < 10; i++) {
            int randomIndex = (int) Math.random()*(a.length-1);
            assertEquals(randomIndex, SearchAlgo.BinarySearch(a, a[randomIndex]));
            assertEquals(-1, SearchAlgo.BinarySearch(a, (int)Math.random()*(1000) + 600));
            assertEquals(-1, SearchAlgo.BinarySearch(a, -10));
        }
    }

    @Test
    void binarySearchEdgeTest() {
        Integer a[] = new Integer[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = i*5;
        }
        assertEquals(0, SearchAlgo.BinarySearch(a, a[0]));
        assertEquals(a.length-1, SearchAlgo.BinarySearch(a, a[a.length - 1]));
    }

    @Test
    void binarySearchRecTest() {
        Integer a[] = new Integer[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = i*5;
        }
        for (int i = 0; i < 10; i++) {
            int randomIndex = (int) Math.random()*(a.length-1);
            assertEquals(randomIndex, SearchAlgo.BinarySearch(a, a[randomIndex], 0, a.length-1));
            assertEquals(-1, SearchAlgo.BinarySearch(a, (int)Math.random()*(1000) + 600, 0, a.length-1));
            assertEquals(-1, SearchAlgo.BinarySearch(a, -10, 0, a.length-1));
        }
    }
}