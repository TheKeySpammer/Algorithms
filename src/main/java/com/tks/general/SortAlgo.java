package com.tks.general;

import java.util.Comparator;

import com.tks.ds.BinaryHeap;

/**
 * Collections of various sorting algorithms. Contains: MergeSort, QuickSort,
 * SelectionSort, BubbleSort
 */
public class SortAlgo {

	/**
	 * MergeSort: Sorts a given array based on the Comparator passed
	 * 
	 * @param <T>  The type of array to be sorted
	 * @param array    The Array to be sorted
	 * @param low    Lower bound of array
	 * @param high    Upper bound of array
	 * @param comparator Comparator for sorting order
	 */
	public static <T> void MergeSort(T array[], int low, int high, Comparator<? super T> comparator) {
		if (high <= low)
			return;
		int mid = (high + low) / 2;
		// Divding from middle and merging
		MergeSort(array, low, mid, comparator);
		MergeSort(array, mid + 1, high, comparator);
		merge(array, low, mid, high, comparator);
	}

	/**
	 * MergeSort: Sorts a given arrays based on Natural Order
	 * 
	 * @param <T> Type of array to be sorted must be Comparable
	 * @param array   Array to be sorted
	 * @param low   Lower bound of the array
	 * @param high   Upper bound of the array
	 */
	public static <T extends Comparable<T>> void MergeSort(T array[], int low, int high) {
		MergeSort(array, low, high, Comparator.naturalOrder());
	}

	private static <T> void merge(T a[], int l, int m, int n, Comparator<? super T> comp) {
		// Merges given array in sorted order from l to m and m+1 to n
		int n1 = (m - l) + 1;
		int n2 = (n - (m + 1)) + 1;
		// Create two temproray array to store array value before merging it into array
		@SuppressWarnings("unchecked")
		T L[] = (T[]) new Object[n1];
		@SuppressWarnings("unchecked")
		T R[] = (T[]) new Object[n2];
		int j = 0;
		for (int i = l; i <= m; i++) {
			L[j++] = a[i];
		}

		j = 0;
		for (int i = m + 1; i <= n; i++) {
			R[j++] = a[i];
		}

		int i = 0, k = 0;
		j = l;
		// Merge based on order
		while (i < n1 && k < n2) {
			if (comp.compare(L[i], R[k]) > 0)
				a[j++] = R[k++];
			else
				a[j++] = L[i++];
		}
		// Merge remaining values
		while (i < n1) {
			a[j++] = L[i++];
		}
		// Merge remaining values
		while (k < n2) {
			a[j++] = R[k++];
		}
	}

	/**
	 * QuickSort: Sorts a given array based on the Comparator passed
	 * 
	 * @param <T>  The type of array to be sorted
	 * @param array    The Array to be sorted
	 * @param low    Lower bound of array
	 * @param high    Upper bound of array
	 * @param comparator Comparator for sorting order
	 */
	public static <T> void QuickSort(T array[], int low, int high, Comparator<? super T> comparator) {
		if (high <= low) return;
		int p = partition(array, low, high, comparator);

		QuickSort(array, low, p-1, comparator);
		QuickSort(array, p+1, high, comparator);
	} 

	/**
	 * QuickSort: Sorts a given arrays based on Natural Order
	 * 
	 * @param <T> Type of array to be sorted must be Comparable
	 * @param array   Array to be sorted
	 * @param low   Lower bound of the array
	 * @param high   Upper bound of the array
	 */
	public static <T extends Comparable<T>> void QuickSort(T array[], int low, int high) {
		QuickSort(array, low, high, Comparator.naturalOrder());
	}

	private static <T> int partition(T a[], int low, int high, Comparator<? super T> comp) {
		T pivot = a[high];

		int i = -1;
		for (int j = 0; j < high-1; j++) {
			if (comp.compare(a[j], pivot) < 0) {
				i++;
				swap(a, i, j);
			} 
		}
		swap(a, i+1, high);
		return i+1;
	}


	/**
	 * SelectionSort: Sorts a given array based on the Comparator passed
	 * 
	 * @param <T>  The type of array to be sorted
	 * @param array    The Array to be sorted
	 * @param low    Lower bound of array
	 * @param high    Upper bound of array
	 * @param comparator Comparator for sorting order
	 */
	public static <T> void SelectionSort(T array[], int low, int high, Comparator<? super T> comparator) {
		for (int i = low; i < high; i++) {
			for (int j = i+1; j <= high; j++) {
				if (comparator.compare(array[i], array[j]) > 0) swap(array, i, j);
			}
		}
	}


	/**
	 * SelectionSort: Sorts a given arrays based on Natural Order
	 * 
	 * @param <T> Type of array to be sorted must be Comparable
	 * @param array   Array to be sorted
	 * @param low   Lower bound of the array
	 * @param high   Upper bound of the array
	 */
	public static <T extends Comparable<T>> void SelectionSort(T array[], int low, int high) {
		SelectionSort(array, low, high, Comparator.naturalOrder());
	}



	/**
	 * BubbleSort: Sorts a given array based on the Comparator passed
	 * 
	 * @param <T>  The type of array to be sorted
	 * @param array    The Array to be sorted
	 * @param low    Lower bound of array
	 * @param high    Upper bound of array
	 * @param comparator Comparator for sorting order
	 */
	public static <T> void BubbleSort(T array[], int low, int high, Comparator<? super T> comparator) {
		for (int i = low; i < high; i++) {
			for (int j = low; j < high-i; j++) {
				if (comparator.compare(array[j], array[j+1]) > 0) swap(array, j, j+1);
			}
		}
	}


	/**
	 * BubbleSort: Sorts a given array based on Natural Order
	 * 
	 * @param <T> Type of array to be sorted must be Comparable
	 * @param array   Array to be sorted
	 * @param low   Lower bound of the array
	 * @param high   Upper bound of the array
	 */
	public static <T extends Comparable<T>> void BubbleSort(T array[], int low, int high) {
		BubbleSort(array, low, high, Comparator.naturalOrder());
	}

	/**
	 * HeapSort: Sorts a given array based on Natural order
	 * TimeComplexity: O(log(n))
	 * @param <T> Type of array, must be Comparable
	 * @param array The array to be sorted
	 * @param low The lower index of the array
	 * @param high The higher index of the array.
	 */
	public static <T extends Comparable<T>> void HeapSort(T array[], int low, int high) {
		BinaryHeap<T> bh = BinaryHeap.fromArray(array);
		for (int i = high-1; i >= low; i--) {
			array[i] = bh.remove();
		}
	}


	private static <T> void swap(T a[], int i, int j) {
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
