package Algorithm_01;
import java.util.*;

public class FibonacciMain {
	private static final double MILLISEC = 1000000.00;
	private static final double MICROSEC = 1000.00;
	
	public static void main(String[] args) {
		long num =  46;
		  
		long startTime = System.nanoTime(); 
		long result = iterativeFibonacci(num);
		long endTime = System.nanoTime();
		System.out.println("[Iterative Fibonacci]");
		System.out.println("Num " + num + " : Fibonacci Number " + result);
		System.out.format("Elapsed Time:  %8d nano sec.\n", endTime - startTime);
		System.out.format("Elapsed Time:  %8.3f micro sec.\n", (double)nanoSecToMicroSec(endTime - startTime));
		System.out.format("Elapsed Time:  %8.3f milli sec.\n", (double)nanoSecToMillSec(endTime - startTime));
		
		startTime = System.nanoTime(); 
		result = recursiveFibonacci(num);		
		endTime = System.nanoTime();
		System.out.println("[Recursive Fibonacci]");
		
		System.out.println("Num " + num + " : Fibonacci Number " + result);
		System.out.format("Elapsed Time:  %8d nano sec.\n", endTime - startTime);
		System.out.format("Elapsed Time:  %8.3f micro sec.\n", (double)nanoSecToMicroSec(endTime - startTime));
		System.out.format("Elapsed Time:  %8.3f milli sec.\n", (double)nanoSecToMillSec(endTime - startTime));
	}
	
	public static long iterativeFibonacci(long num) {
		int i;
		int[] f = new int[(int)num+1];
		f[0] = 0;
		if(num > 0) {
			f[1] = 1;
			for( i=2; i<=num; i++ )
				f[i] = f[i-1] +f[i-2];
		}
		return f[(int )num];
	}
	
	public static long recursiveFibonacci(long num) {
		if ( num <= 1 ) return num; //더이상 반복할 필요가 없는 경우
		//그외 경우 계속 반복
		else return(recursiveFibonacci(num-2) + recursiveFibonacci(num-1));
	}

	public static double nanoSecToMicroSec(long nanoSec) {
		return nanoSec / MICROSEC;
	}
	
	public static double nanoSecToMillSec(long nanoSec) {
		return nanoSec / MILLISEC;
	}
}