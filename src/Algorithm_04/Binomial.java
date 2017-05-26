package Algorithm_04;

import java.util.*;

public class Binomial {
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		int n, k;
		long startTime = 0, endTime = 0, result, result2;
		System.out.print("enter the n : ");
		n = input.nextInt();
		System.out.print("enter the k : ");
		k = input.nextInt();

		startTime = System.nanoTime();
		result = binomial1(n, k); // binomial1
		endTime = System.nanoTime();
		System.out.println("\n[binomial 1]\nElapsed Time : " + (endTime - startTime) + " nanotime\tresult : " + result);

		startTime = System.nanoTime();
		result = binomial2(n, k); // binomial2
		endTime = System.nanoTime();
		System.out.println("\n[binomial 2]\nElapsed Time : " + (endTime - startTime) + " nanotime\tresult : " + result);
	}
	//작은 값 구해주는 함수 
	public static int minimum(int i, int k) {
		return (i >= k ? k : i);
	}
	//재귀함수식으로 구현한 이항계수 구하는 함수
	public static int binomial1(int n, int k) {
		if (k == 0 || n == k)
			return 1;
		else
			//전값 더하기 현 값
			return (binomial1(n - 1, k - 1) + binomial1(n - 1, k));
	}
	//반복함수식으로 구현한 이항계수 구하는 함수
	public static int binomial2(int n, int k) {
		int i, j;
		//이항계수 중에는 5C0이란 값도 있으므로 배열 크기를 한개 증가시켜서 만듬
		//행이 2개인 것은 전 배열의 값들을 이용해야하므로  한 행만 더 만들고 
		//반복이될 때마다 행의 순서(값들)를 계속 바꾸는 식으로 구현
		int[][] B = new int[2][k+1];
		int tmp = 0;//행의 순서를 바꾸기 위해 만든 변수
		//일단 n번은 반복해야함
		for (i = 0; i <= n; i++) {
			//계산량을 줄이기위해 i/2와 k값중에 최소값만큼 더 반복하기로함
			for (j = 0; j <= minimum(i/2,k); j++) {
				if (j == 0|| j==i ) { //0번째 끝번째항은 무조건 값이 1
					B[tmp][j] = 1;
				} 
				else //전행의 값을 표현하기위해 3항연산자 이용
					// 그리고 현행의 값에다가 전행의 j-1번재 j번째값의 합을 넣음
					B[tmp][j] = B[(tmp > 0) ? 0 : 1][j-1] + B[(tmp > 0) ? 0 : 1][j];
				//k보다 i-j가 같은 경우는 그 값에다가 위에서 이미 계산한 값을 넣음 
				if( i-j <= k)
					B[tmp][i-j] = B[tmp][j];
			}
			//다음 계산을 위해서 행바꿈 재실행
			tmp = (tmp > 0) ? 0 : 1;
		}
		//위의 마지막 반복으로 행이 또 바뀌므로 다시 바꿔서 리턴
		return B[(tmp > 0) ? 0 : 1][k];
	}
}
