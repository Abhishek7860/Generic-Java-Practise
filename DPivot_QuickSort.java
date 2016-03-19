/*
 * class to implement the dual pivot quick sort
 * 
 */
import java.lang.Comparable;

public class DPivot_QuickSort {

	<T extends Comparable<? super T>> void quicksortry(T[] A, int start, int end) {

		if (end <= start)
			return;
		int piv1 = start;
		int piv2 = end;
		T p = A[piv1];
		T r = A[piv2];
		//compare the two pivots to place them in array
		if (A[piv1].compareTo(A[piv2]) > 0) {
			T temp = A[piv1];
			A[piv1] = A[piv2];
			A[piv2] = temp;

			p = A[piv1];
			r = A[piv2];
			// check condition when two pivot elements are equal
		} else if (A[piv1] == A[piv2]) {
			while (piv1 == piv2 && p.compareTo(r) < 0) {
				piv1++;
				p = A[piv1];
			}
		}

		int i = piv1 + 1;
		int lt = piv1 + 1;
		int gt = piv2 - 1;
		while (i <= gt) {

			if (A[i].compareTo(p) < 0) {
				sort(A, i++, lt++);
			} else if (r.compareTo(A[i]) < 0) {
				sort(A, i, gt--);
			} else {
				i++;
			}

		}
		//sort the subsets 
		sort(A, piv1, --lt);
		sort(A, piv2, ++gt);

		quicksortry(A, piv1, lt - 1);
		quicksortry(A, lt + 1, gt - 1);
		quicksortry(A, gt + 1, piv2);
	}

	/*
	 * to implement the quick sort
	 */
	static <T extends Comparable<? super T>> void quicksort(T[] A, int length) {
		int pivot1 = 0;
		int pivot2 = length;
		// System.out.println(pivot2);
		DPivot_QuickSort sort = new DPivot_QuickSort();
		sort.quicksortry(A, pivot1, pivot2);
	}
	/*
	 * to swap the elements
	 */
	static <T extends Comparable<? super T>> void sort(T[] A, int p, int q) {
		T temp = A[p];
		A[p] = A[q];
		A[q] = temp;
		//return;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n = 1000000;
		if(args.length > 0) { n = Integer.parseInt(args[0]); }
		Integer[] A = new Integer[n];
		for (int i = 0; i < n; i++) {
			A[i] = new Integer(i+1);
		}
		Shuffle.shuffle(A, 0, n-1);
	//to print the elements before sorting(shuffled elements)
		//Shuffle.printArray(A, 0, n-1, "Before: ");
		Timer timer = new Timer();
		timer.start();
		quicksort(A,n-1);
		timer.end();
		//System.out.println(timer);
		//to print the elements before sorting
		//Shuffle.printArray(A, 0, n-1, "After: ");
		System.out.println(timer);
	}

}