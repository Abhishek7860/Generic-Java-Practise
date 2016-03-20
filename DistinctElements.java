import java.util.HashSet;
import java.util.Random;
import java.util.Timer;

/**
 * this class check all elements and all distinct elements are placed in the
 * starting of the array
 * 
 * 
 */
public class DistinctElements {

	public int distinct(Integer A[], int count) {
		HashSet hs = new HashSet();
		int temp = 0;
		int store = 0;
		for (int i = 0; i < count - 1; i++) {
			if (!(hs.contains(A[i]))) {
				hs.add(A[i]);
				store = A[temp];
				A[temp] = A[i];
				A[i] = store;
				temp = temp + 1;
			}

		}
		return hs.size();
	}

	public static void main(String args[]) {
		DistinctElements t = new DistinctElements();

		int n = 10;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		Integer[] A = new Integer[n + n]; // Integer[] tmp = new Integer[n];
		Random randomGenerator = new Random();
		for (int i = 0; i < 2 * n; i++) {
			A[i] = randomGenerator.nextInt(n); // takes random elements in the
												// range of 0-n/2
		}
		Shuffle.shuffle(A, 0, 2 * n - 1);
		Shuffle.printArray(A, 0, 2 * n - 1, "Before: ");
		int tr = t.distinct(A, 2 * n);
		Shuffle.printArray(A, 0, 2 * n - 1, "After: ");
	}
}
