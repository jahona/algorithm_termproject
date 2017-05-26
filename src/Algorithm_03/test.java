package Algorithm_03;

public class test {
	static int[] ar = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
	static int count = 0;
	static int num = ar.length;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(search(ar, 0, num, 90));
	}

	static void print(int[] ar) {
		for (int i = 0; i < ar.length; i++)
			System.out.print(ar[i] + " ");
		System.out.println();
	}

	static int search(int[] array, int low, int high, int x) {
		int mid1 = (low + high)/3;
		int mid2 = mid1*2;
		int count = 0;
		    if(high < low) {
		        return -1;
		    } 

		    if(x==array[mid1]) {
		    	count++;
		    	System.out.println("\n" + "count :"+ count+"\n");
		    	return mid1+1;
		    } else if(x<array[mid1]) {
		    	count++;
		        return search( array, low, mid1 - 1, x);
		    } else if(x<array[mid2] ){
		    	count++;
		    	 return search( array,  mid1+1, mid2, x);
		    } else {
		    	count++;
		    	return search( array, mid2+1, high, x);
		    }
		    
	
	}

}
