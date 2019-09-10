package com.tks.ds;

import java.util.Arrays;

public class BinaryHeap<T extends Comparable<T>> {
    private T heap[];
    private static final boolean DEBUG = false;
    private static final int SIZE = 16;
    private static final int FILL = 10;
    private int low;
    private int high;
    
    public BinaryHeap() {
        this(SIZE);
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(int capacity) {
        this.heap = (T[]) new Comparable[capacity];
        this.low = 0;
        this.high = 0;
    }
    
    public void add(T e){
        if (high == this.heap.length) {
            this.increaseCapacity();
        }
        this.heap[this.high] = e;
        // Heapify
        if (DEBUG)
        System.out.println("Added New Element in "+Arrays.toString(this.heap));
        int parent = this.high == 0 ? 0 : (this.high - 1) / 2;
        int child = this.high;
        if(DEBUG) {
            System.out.println("Heapifying");
            System.out.println("Init Parent: "+parent);
            System.out.println("Init Child: "+child);
        }
        while (parent != child && this.heap[parent].compareTo(this.heap[child]) < 0) {
            swap(parent, child);
            child = parent;
            parent = child == 0 ? 0 : (child - 1) / 2;
        }
        this.high++;
    }
    
    public T remove()throws IndexOutOfBoundsException {
        if (high == low) throw new IndexOutOfBoundsException();
        T max = this.heap[this.low];
        swap(high-1, low);
        this.high--;
        this.heapify(this.low);
        return max;
    }
    
    public T replace(T val, int index)throws IndexOutOfBoundsException {
        if (index == this.high) throw new IndexOutOfBoundsException(); 
        T replaced = this.heap[index];
        this.heap[index] = val;
        // Heapify Child
        this.heapify(index);
        // Heapify parent
        int parent = index == 0 ? 0: (index - 1)/2;
        int child = index;
        if (child == 0) {
            heapify(parent);
        }
        while (parent != child && this.heap[parent].compareTo(this.heap[child]) < 0) {
            heapify(parent);
            child = parent;
            parent = child == 0 ? 0 : (child - 1)/2;
        }
        return replaced;
    }
    
    public boolean isEmpty() {
        return this.high == this.low;
    }

    public int size() {
        return this.high - this.low;
    }

    @Override
    public String toString() {
        String str = "[";
        for (int i = low; i < high-1; i++) {
            str = str+this.heap[i]+", ";
        }
        str = str+this.heap[this.high-1]+"]";
        return str;
    }

    private void heapify (int i) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int n = (this.high - this.low);
        int large = l;
        if (!isHeap(i)) {
            if (l >= n) large = r;
            else if (r >= n) large = l;
            else large = this.heap[r].compareTo(this.heap[l]) > 0 ? r : l;
            swap (large, i);
        }
        if (!isHeap(large)) {
            heapify(large);
        }
    }

    private boolean isHeap(int index) {
        int l = 2 * index + 1;
        int r = 2 * index + 2;
        int n = (this.high - this.low);
        int leafIndexStart = (n / 2);
        if (index >= leafIndexStart) {
            return true;
        }
        if (l>=n) {
            return this.heap[index].compareTo(this.heap[r]) >= 0;
        }
        else if (r >= n) {
            return this.heap[index].compareTo(this.heap[l]) >= 0;
        }
        else{
            return this.heap[index].compareTo(this.heap[l]) >= 0 && this.heap[index].compareTo(this.heap[r]) >= 0;
        }

    }

    public boolean isHeap() {
        for (int i = this.low; i < this.high; i++) {
            if (!isHeap(i)) {
                if (DEBUG)
                System.out.println("Heap failed at index: "+i);
                return false;
            }
        }
        return true;
    }

    private void swap (int i, int j) {
        T temp = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = temp;
    }
    
    @SuppressWarnings("unchecked")
    private void increaseCapacity() {
        int originalCapacity = this.heap.length;
        int newLength = originalCapacity + FILL;
        T temp[] = (T[]) new Comparable[newLength];
        for (int i = 0; i < this.heap.length; i++) {
            temp[i] = heap[i];
        }
        if (DEBUG) {
            System.out.println("Increasing Capacity");
            System.out.println("Original Array: ");
            System.out.println(Arrays.toString(this.heap));
        }
        this.heap = temp;
        if (DEBUG) {
            System.out.println("New Array: ");
            System.out.println(Arrays.toString(this.heap));
        }
    }
}