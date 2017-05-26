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
	//���� �� �����ִ� �Լ� 
	public static int minimum(int i, int k) {
		return (i >= k ? k : i);
	}
	//����Լ������� ������ ���װ�� ���ϴ� �Լ�
	public static int binomial1(int n, int k) {
		if (k == 0 || n == k)
			return 1;
		else
			//���� ���ϱ� �� ��
			return (binomial1(n - 1, k - 1) + binomial1(n - 1, k));
	}
	//�ݺ��Լ������� ������ ���װ�� ���ϴ� �Լ�
	public static int binomial2(int n, int k) {
		int i, j;
		//���װ�� �߿��� 5C0�̶� ���� �����Ƿ� �迭 ũ�⸦ �Ѱ� �������Ѽ� ����
		//���� 2���� ���� �� �迭�� ������ �̿��ؾ��ϹǷ�  �� �ุ �� ����� 
		//�ݺ��̵� ������ ���� ����(����)�� ��� �ٲٴ� ������ ����
		int[][] B = new int[2][k+1];
		int tmp = 0;//���� ������ �ٲٱ� ���� ���� ����
		//�ϴ� n���� �ݺ��ؾ���
		for (i = 0; i <= n; i++) {
			//��귮�� ���̱����� i/2�� k���߿� �ּҰ���ŭ �� �ݺ��ϱ����
			for (j = 0; j <= minimum(i/2,k); j++) {
				if (j == 0|| j==i ) { //0��° ����°���� ������ ���� 1
					B[tmp][j] = 1;
				} 
				else //������ ���� ǥ���ϱ����� 3�׿����� �̿�
					// �׸��� ������ �����ٰ� ������ j-1���� j��°���� ���� ����
					B[tmp][j] = B[(tmp > 0) ? 0 : 1][j-1] + B[(tmp > 0) ? 0 : 1][j];
				//k���� i-j�� ���� ���� �� �����ٰ� ������ �̹� ����� ���� ���� 
				if( i-j <= k)
					B[tmp][i-j] = B[tmp][j];
			}
			//���� ����� ���ؼ� ��ٲ� �����
			tmp = (tmp > 0) ? 0 : 1;
		}
		//���� ������ �ݺ����� ���� �� �ٲ�Ƿ� �ٽ� �ٲ㼭 ����
		return B[(tmp > 0) ? 0 : 1][k];
	}
}
