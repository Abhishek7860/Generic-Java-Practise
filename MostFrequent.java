/**
 *
 * This class calculates the most frequently occuring element in
 * both O(n) and O(nlogn) times
 **/

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

public class MostFrequent {
	public static void main(String args[]) {
		int n, nlognFreq, nFreq;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter n value: ");
		n = in.nextInt(); //take n value as input i.e., size of the array
		int arr[] = new int[n];
		Random randomGenerator = new Random();
		for (int i = 0; i < n; i++) {
			arr[i] = randomGenerator.nextInt(n / 2); //takes random elements in the range of 0-n/2
		}
		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println();
		Timer timer = new Timer();
		timer.start();
		nlognFreq = mostFreq(arr);
		timer.end();
		System.out.println("max frequency element in O(nlogn) time: " + nlognFreq);
		System.out.println(timer);
		Timer timer1 = new Timer();
		timer1.start();
		nFreq = mostFrequent(arr);
		timer1.end();
		System.out.println("max frequency element in O(n) time: " + nFreq);
		System.out.println(timer1);
		in.close();
	}

	/**
	 * this method calculates most frequent element in O(nlogn) time
	 * */
	public static int mostFreq(int[] arr) {
		int counter = 0;
		if (arr == null || arr.length == 0)
			return 0;
		Arrays.sort(arr);
		int prev = arr[0];
		int res[] = new int[arr.length - 1];
		// System.out.println(arr[arr.length-1]);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == prev) {
				counter++;
				res[arr[i]] = counter;
			} else {
				counter = 0;
				counter++;
				prev = arr[i];
			}
		}
		int max = 0, j;
		int result = 0;
		for (j = 0; j < res.length; j++) {
			if (max < res[j]) {
				max = res[j];
				result = j;
			}
		}
		return result;
	}

	/**
	 * this method calculates most frequent element in O(n)time
	 * */
	public static int mostFrequent(int[] arr) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (hm.containsKey(arr[i])) {
				int term = hm.get(arr[i]);
				term = term + 1;
				hm.put(arr[i], term);

			} else
				hm.put(arr[i], 0);

		}
		int max = 0;
		for (int j = 0; j < hm.size(); j++) {
			if (max < hm.get(arr[j])) {
				max = hm.get(arr[j]);
			}
		}
		return max;
	}
}