package com.tks;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;
import com.tks.general.SortAlgo;

class SortAlgoTest {
    private Integer arr[];

    SortAlgoTest() {
        this.arr = new Integer[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*5000 + 10);
        }
    }

    @Test
    void testMergeSort() {
        Integer a[] = Arrays.copyOf(this.arr, this.arr.length);
        Integer b[] = Arrays.copyOf(this.arr, this.arr.length);
        SortAlgo.MergeSort(a, 0, a.length - 1);
        assertTrue(com.tks.util.Arrays.isSorted(a));
        SortAlgo.MergeSort(b, 0, b.length-1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }        
        });
        assertTrue(com.tks.util.Arrays.isSorted(b));
    }

    @Test
    void testQuickSort() {
        Integer a[] = Arrays.copyOf(this.arr, this.arr.length);
        Integer b[] = Arrays.copyOf(this.arr, this.arr.length);
        SortAlgo.QuickSort(a, 0, a.length - 1);
        assertTrue(com.tks.util.Arrays.isSorted(a));
        SortAlgo.QuickSort(b, 0, b.length-1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }        
        });
        assertTrue(com.tks.util.Arrays.isSorted(b));
    }

    @Test
    void testSelectionSort() {
        Integer a[] = Arrays.copyOf(this.arr, this.arr.length);
        Integer b[] = Arrays.copyOf(this.arr, this.arr.length);
        SortAlgo.SelectionSort(a, 0, a.length - 1);
        assertTrue(com.tks.util.Arrays.isSorted(a));
        SortAlgo.SelectionSort(b, 0, b.length-1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }        
        });
        assertTrue(com.tks.util.Arrays.isSorted(b));
    }

    @Test
    void testBubbleSort() {
        Integer a[] = Arrays.copyOf(this.arr, this.arr.length);
        Integer b[] = Arrays.copyOf(this.arr, this.arr.length);
        SortAlgo.BubbleSort(a, 0, a.length - 1);
        assertTrue(com.tks.util.Arrays.isSorted(a));
        SortAlgo.BubbleSort(b, 0, b.length-1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }        
        });
        assertTrue(com.tks.util.Arrays.isSorted(b));
    }
}