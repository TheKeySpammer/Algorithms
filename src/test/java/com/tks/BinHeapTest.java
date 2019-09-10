package com.tks;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.tks.ds.BinaryHeap;

class BinHeapTest {

    @Test
    void testHeapAdd() {
        Integer arr[] = new Integer[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 9999 + 1);
        }
        BinaryHeap<Integer> bh = new BinaryHeap<>();
        for (int i = 0; i < arr.length; i++) {
            bh.add(arr[i]);
        }
        assertTrue(bh.isHeap(), "Binary Heap is not heap after insertion. Heap Size: "+bh.size());
    }
    @Test
    void testHeapRemove() {
        Integer arr[] = new Integer[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 9999 + 1);
        }
        BinaryHeap<Integer> bh = new BinaryHeap<>();
        for (int i = 0; i < arr.length; i++) {
            bh.add(arr[i]);
        }
        assertTrue(bh.isHeap(), "Binary Heap is not heap after insertion. Heap Size: "+bh.size());
        int val = bh.remove();
        int limit = (bh.size() / 2) - 1;
        for (int i = 0; i < limit; i++) {
            int n = bh.remove();
            assertTrue(val >= n, "Removed value "+n+" is not greater than "+val);
        }
        assertTrue(bh.isHeap(), "Binary Heap is not heap after removal. Heap Remaining Length: "+bh.size());
    }

    @Test
    void testHeapReplace() {
        Integer arr[] = new Integer[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 9999 + 1);
        }
        BinaryHeap<Integer> bh = new BinaryHeap<>();
        for (int i = 0; i < arr.length; i++) {
            bh.add(arr[i]);
        }
        assertTrue(bh.isHeap(), "Binary Heap is not heap after insertion. Heap Size: "+bh.size());
        for (int i = 0; i < 1000; i++) {
            int val = (int) (Math.random()*9999 +1);
            int index = (int)(Math.random() * bh.size());
            int replaced = bh.replace(val, index);
            assertTrue(bh.isHeap(), "Binary Heap not a heap after replacing "+val+" at index "+index+" on "+i+"th attempt. Replaced Value "+replaced);
        }
    }

    @Test
    void testHeapFromArray() {
        Integer arr[] = new Integer[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 9999 + 1);
        }
        BinaryHeap<Integer> bh = BinaryHeap.fromArray(arr);
        assertTrue(bh.isHeap(), "BinaryHeap from array failed");
    }
}