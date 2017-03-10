import java.util.*;

public class program2Driver {
	// temp array for merge sorting
	public static int[] tempNums = new int[500];
	// initial array to hold the first set of numbers
	public static int[] quickNums = new int[500];
	// second array to hold the second set of numbers
	public static int[] mergeNums = new int[500];
	// holder variable for temp random nums
	public static int randomNum;

	public static void main(String args[]) {
		// vars to hold the start and stop time for timing sorting
		long startTime, endTime, totalTime = 0;
		// vars to hold array range
		int startRange = 0;
		int endRange = 500;

		// generate initial set of numbers in mergeSort array
		randomNums();
		bufferOutput();

		// compare arrays after filling the mergeNums array with random number
		// list
		System.out.println("First Stage: Generate the Numbers for the mergeNums number list");
		System.out.println("First Array(MergeSort): " + Arrays.toString(mergeNums));
		System.out.println("Second Array(QuickSort): " + Arrays.toString(quickNums));
		bufferOutput();

		// duplicate the contents from mergeNums to quickNums
		Copy(mergeNums, quickNums, startRange, endRange);
		System.out.println("Second Stage: Copying the number list from the mergeNums list to quickNums list");
		System.out.println("First Array(MergeSort): " + Arrays.toString(mergeNums));
		System.out.println("Second Array(QuickSort): " + Arrays.toString(quickNums));
		bufferOutput();

		// Start MergeSort Run
		startTime = System.nanoTime();
		MergeSort(mergeNums, tempNums, startRange, endRange);
		endTime = System.nanoTime();
		totalTime = (endTime - startTime);
		System.out.println("Third Stage: MergeSorting the MergeSort Array");
		System.out.println("Final Sorted MergeSort List: " + Arrays.toString(mergeNums));
		System.out.println("Total MergeSort Algorithm Runtime: " + endTime + " //NanoSeconds");
		bufferOutput();

		// QuickSort NonSense Below Here....
		startTime = System.nanoTime();
		QuickSort(quickNums, startRange, (quickNums.length - 1));
		endTime = System.nanoTime();
		totalTime = (endTime - startTime);
		System.out.println("Fourth Stage: QuickSorting the QuickSort Array");
		System.out.println("Final Sorted QuickSort List: " + Arrays.toString(quickNums));
		System.out.println("Total QuickSort Algorithm Runtime: " + endTime + " //NanoSeconds");
		bufferOutput();

	}

	/**
	 * Method to Generate Random Numbers and push into temporary array
	 */
	public static void randomNums() {
		for (int i = 0; i < mergeNums.length; i++) {
			randomNum = 1 + (int) (Math.random() * 100);
			mergeNums[i] = randomNum;
		}

		// verify number locations in mergeSort Array
		System.out.println("Initial List of Numbers w/ index Reference");
		System.out.println(" - - - - - - - - - - - - - - - - - - - - -");
		for (int i = 0; i < mergeNums.length; i++) {
			System.out.println("i @" + i + ":" + mergeNums[i]);
		}
		System.out.println(" - - - - - - - - - - - - - - - - - - - - -");
	}

	/*
	 * Method to buffer console output
	 */
	public static void bufferOutput() {
		for (int i = 0; i < 3; i++) {
			System.out.println(" ");
		}
	}

	/*
	 * Method to copy array from array1 to array2 from range x to y
	 */
	public static void Copy(int[] from, int[] to, int x, int y) {
		for (int i = x; i < y; i++) {
			to[i] = from[i];
		}
	}

	/*
	 * MergeSort Method
	 */
	public static void MergeSort(int[] numArray, int[] tempArray, int x, int y) {
		if (y - x < 2)
			return;
		int m = ((x + y) / 2);
		MergeSort(numArray, tempArray, x, m);
		MergeSort(numArray, tempArray, m, y);
		Remerge(numArray, tempArray, x, m, y);
		Copy(tempArray, numArray, x, y);
	}

	/*
	 * Method to ReMerge the MergeSorting
	 */
	public static void Remerge(int[] numArray, int[] tempArray, int x, int m, int y) {
		int i = x;
		int j = m;
		for (int k = x; k < y; k++) {
			if (i < m && (j >= y || numArray[i] <= numArray[j])) {
				tempArray[k] = numArray[i];
				i++;
			} else {
				tempArray[k] = numArray[j];
				j++;
			}
		}
	}

	public static void QuickSort(int[] numArray, int left, int right) {
		int pivotIndex = (left + (right - left) / 2);
		int pivotValue = numArray[pivotIndex];

		int x = left, y = right;
		int temp;
		
		while(x<=y){
			while(numArray[x] < pivotValue){
				x++;
			}
			while(numArray[y] > pivotValue){
				y--;
			}
			if (x <= y){
				temp = numArray[x];
				numArray[x] = numArray[y];
				numArray[y] = temp;
				x++;
				y--;
			}
		}
		
		if(left< y){
			QuickSort(numArray, left, y);
		}
		if(x < right){
			QuickSort(numArray, x, right);
		}

	}
		
	// end of class
}
