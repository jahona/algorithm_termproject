import java.util.Scanner;

public class Sort {
	static int[] sort( int[] ar ){
		for (int j = 0; j < ar.length; j++) {
			for (int i = 0; i < ar.length - 1; i++) {
				if (ar[i] > ar[i + 1]) {
					int k = ar[i + 1];
					ar[i + 1] = ar[i];
					ar[i] = k;
				}
			}
		}
		return ar;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();

		
	}

}
