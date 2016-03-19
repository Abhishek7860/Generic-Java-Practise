/*
 * class to implement the traditional computing
 * 
 */

import java.util.*;
import java.lang.Comparable;

public class QuickSort {
	//to implement the quicksort
	<T extends Comparable<? super T>> void quicksortry(T[] A, int start, int end) {

		int i = start, j = end;
		int temp1 = (start + end) / 2;
		T pivot = A[temp1];
		while (i <= j) {
			while (A[i].compareTo(pivot) < 0) {
				i++;
			}

			while (A[j].compareTo(pivot) > 0) {
				j--;
			}

			if (i <= j) {
				T temp = A[i];
				A[i] = A[j];
				A[j] = temp;
				i++;
				j--;
			}
		}

		// recursively sort two sub parts
		if (start < j)
			quicksortry(A, start, j);

		if (end > i)
			quicksortry(A, i, end);
	}

	static <T extends Comparable<? super T>> void quicksort(T[] A, int length) {
		int low = 0;
		int high = length;
		QuickSort sort = new QuickSort();
		sort.quicksortry(A, low, high);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n = 1000000;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		Integer[] A = new Integer[n];
		for (int i = 0; i < n; i++) {
			A[i] = new Integer(i);
		}
		Shuffle.shuffle(A, 0, n - 1);
	// to print the elements before sorting(shuffled elements)
		//Shuffle.printArray(A, 0, n - 1, "Before: ");
		Timer timer = new Timer();
		timer.start();
		quicksort(A, n - 1);
		timer.end();
		// to print the elements after sorting
		//Shuffle.printArray(A, 0, n - 1, "After: ");
		System.out.println(timer);

	}

}
