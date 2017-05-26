package Algorithm_01;

import java.util.*;

public class SearchMain {
	private static final double MILLISEC = 1000000.00;
	private static final double MICROSEC = 1000.00;

	static int[] s = null;
	static int num, key;

	public static void main(String[] args) {
		num = 1000;
		long startTime ;
		int location=0;
		long endTime ;
		long first = 0;
		long second = 1;
		while(first != second){
		Random r = new Random();
		s = new int[num];
		for (int i = 0; i < num; i++) {
			s[i] = r.nextInt(num);
		}
		key = r.nextInt(num);

		for (int i = 0; i < num; i++) {
			System.out.print(s[i] + " ");
		}
		System.out.println("\nKey Value: " + key + "\n");
		startTime = System.nanoTime();
		
		endTime = System.nanoTime();
		

		Arrays.sort(s);
		for (int i = 0; i < num; i++) {
			System.out.print(s[i] + " ");
		}
		System.out.println("\nKey Value: " + key + "\n");

		startTime = System.nanoTime();
		location = binarySearch(num, s, key);
		endTime = System.nanoTime();
		first = startTime-endTime;
		System.out.println("[Binary Search Result]");
		System.out.println("Key value " + key + ": location " + location);
		System.out.format("Elapsed Time:  %8d nano sec.\n", endTime - startTime);
		System.out.format("Elapsed Time:  %8.3f micro sec.\n", (double) nanoSecToMicroSec(endTime - startTime));
		System.out.format("Elapsed Time:  %8.3f milli sec.\n", (double) nanoSecToMillSec(endTime - startTime));
		System.out.println();

		startTime = System.nanoTime();
		location = recursiveBinarySearch(0, num, s, key);
		endTime = System.nanoTime();
		second = startTime-endTime;
		System.out.println("[Recursive Binary Search Result]");
		System.out.println("Key value " + key + ": location " + location);
		System.out.format("Elapsed Time:  %8d nano sec.\n", endTime - startTime);
		System.out.format("Elapsed Time:  %8.3f micro sec.\n", (double) nanoSecToMicroSec(endTime - startTime));
		System.out.format("Elapsed Time:  %8.3f milli sec.\n", (double) nanoSecToMillSec(endTime - startTime));
	
		}
	}

	public static int sequentialSearch(int n, int[] array, int x) {
		int location;
		location = 1;
		while (location <= n && array[location-1] != x) { //-1 하는 이유는 배열index를 0부터 시작해야하기 때문이다 
			location++;
			if (location > n) //만약에 찾지 못하는 경우
				{ location = 0; break; }
			
		}
		return location;
	}

	public static int binarySearch(int n, int[] array, int x) {
		int location, low, high, mid;
		low = 1;
		high = n;
		location = 0;
		int count= 0;
		while( low <= high && location == 0 ) {
			mid = (int) Math.floor((low + high)/2.0);
			if( x == array[mid]) { location = mid+1; break;} 
			else if (x < array[mid]) high = mid - 1;
			else low = mid+1 ;
			count++;
		}
		System.out.println("\n" + "count :"+ count+"\n");
		return location;
	}

	public static int recursiveBinarySearch(int low, int high, int[] array, int x) {
		int mid = (low + high) / 2;
		int count = 0;
		    if(high < low) {
		        return 0;
		    } 

		    if(x==array[mid]) {
		    	count++;
		    	System.out.println("\n" + "count :"+ count+"\n");
		    	return mid+1;
		    } else if(x<array[mid]) {
		    	count++;
		        return recursiveBinarySearch( low, mid - 1, array , x );
		    } else {
		    	count++;
		    	 return recursiveBinarySearch( mid+1, high, array , x );
		    }
	}

	public static double nanoSecToMicroSec(long nanoSec) {
		return nanoSec / MICROSEC;
	}

	public static double nanoSecToMillSec(long nanoSec) {
		return nanoSec / MILLISEC;
	}
}