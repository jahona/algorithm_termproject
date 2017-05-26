package Algorithm_99;

import java.util.*;

public class MergeSortMain {

	static int[] s1 = null;
	static int[] s2 = null;
	static int num;
	static int[] tempArray = null; // additional array for inPlaceMergeSort

	public static void main(String[] args) {
		num = 10;

		Random r = new Random(System.currentTimeMillis());
		s1 = new int[num]; // Initialize the array s1
		s2 = new int[num]; // Initialize the array s2
		tempArray = new int[num]; // Initialize the additional array

		for (int i = 0; i < num; i++) {
			s1[i] = s2[i] = r.nextInt(num);
		}

		System.out.println("s1");
		for (int i = 0; i < num; i++) {
			System.out.print(s1[i] + " ");
		}
		System.out.println();

		System.out.println("s2");
		for (int i = 0; i < num; i++) {
			System.out.print(s2[i] + " ");
		}
		System.out.println();

		long startTime = 0, endTime = 0;
		int location = -1;

		startTime = System.nanoTime();
		mergeSort(num, s1);
		endTime = System.nanoTime();
		System.out.println("[MergeSort Result]");
		System.out.println("Elapsed Time: " + (endTime - startTime) + "nano sec.");
		System.out.println();

		startTime = System.nanoTime();
		inPlaceMergeSort(0, num - 1);
		endTime = System.nanoTime();
		System.out.println("[In-place MergeSort Result]");
		System.out.println("Elapsed Time: " + (endTime - startTime) + "nano sec.");
		System.out.println();

		/*
		 * for (int i = 0; i < num; i++) { System.out.print(s[i] + " "); }
		 * System.out.println();
		 */
	}

	public static void mergeSort(int n, int[] s) { // mergeSort [Algorithm 2.2]
		if (n > 1) {
			int h = n/2 , m = n - h;
			int[] U = new int[h];
			int[] V = new int[m];
			for(int i=0; i<h; i++){
				U[i]=s[i];
			}
			int a=0;
			for(int j=h-1; j<n-1; j++,a++){
				V[a]=s[j];
			}
			
			mergeSort(h,U); //왼쪽부터정렬다한다
			mergeSort(m,V); //오른쪽정렬한다

			merge(h,m,U,V,s);//U,V는 다정렬되어 있음
		}

	}

	public static void merge(int h, int m, int[] u, int[] v, int[] s) { // merge
																		// [Algorithm
																		// 2.3]
		int i, j, k;
		i = 0;
		j = 0;
		k = 0;
		while (i <= h && j <= m) {
			
			if (u[i] < v[j]) {
				s[k] = u[i];
				i++;
			} else {
				s[k] = v[j];
				j++;
			}
			k++; // k는 원래 배열의 인덱스
		}
		if (i > h) {
			while (j > (h + m)) {
				s[k] = v[j];
				j++;
				k++;
			}
		} else {
			while (i > m) {
				s[k] = u[j];
				i++;
				k++;
			}
		}
	}

	public static void inPlaceMergeSort(int low, int high) { // mergesort2
																// [Algorithm
																// 2.4]
		// inPlaceMerge(low, mid, high)
	}

	public static void inPlaceMerge(int low, int mid, int high) { // merge2
																	// [Algorithm
																	// 2.5]

	}
}