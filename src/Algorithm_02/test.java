package Algorithm_02;

import java.util.Random;

public class test {
	// HOMEWORK�� �Լ�
	static void HW(int[] a) {
		int[] first = new int[a.length / 2];// ���ĵ� �迭�� ���������� ������ �迭
		int[] second = new int[a.length / 2 + 1];// ���ĵ� �迭�� �� ������ ���� ������ �迭

		int sum_first = 0; // ���� �ʱ�ȭ
		int sum_second = 0;

		mergesort(a); // ����
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		// ���ĵ� �迭�� ������� first[]�� ����
		for (int i = 0; i < a.length / 2; i++) {
			first[i] = a[i];
		}
		// ���ĵ� �迭�� ������� second[]�� ����
		if(a.length %2 == 1) {
			for (int i = 0; i < a.length / 2 + 1; i++) {
			second[i] = a[a.length / 2 + i];
			}
		}
		else{
			for (int i = 0; i < a.length / 2 + 1; i++) {
				second[i] = a[a.length / 2 + i-1];
				}
		}
		// first[]��� �� sum_first�� ���ϱ�
		System.out.print("\nfirst : ");
		for (int i = 0; i < first.length; i++) {
			System.out.print(first[i] + " ");
			sum_first += first[i];
		}
		// second[]��� �� sum_second�� ���ϱ�
		System.out.println();
		System.out.print("second : ");
		for (int i = 0; i < second.length; i++) {
			System.out.print(second[i] + " ");
			sum_second += second[i];
		}
		System.out.println("\nsum_second - sum_first = MAX?");
		System.out.println(sum_second + " - " + sum_first + " = " + (sum_second - sum_first));
	}

	// merge �Լ� �����ڵ��� index�� ũ�⸦ ��Ÿ���� �Ķ���ʹ� ���ʿ��ϴٰ� �����Ǿ� �����Ͽ����ϴ�.
	public static void merge(int[] u, int[] v, int[] s) {
		// �� ����/������/���� �迭�� ������(c���� ������x) ����
		int i = 0;
		int j = 0;
		int k = 0;

		while (i < u.length) {// ���� �� �߰� ���� �Ѿ������ �������� �Ѿ�ڴٴ� �ǹ�
			if (j < v.length) {
				if (u[i] < v[j]) { // ũ�� ���� ���� ��� ���Ľ�ų �迭�� �ְڴٴ� �ǹ�
					s[k] = u[i];
					i++;
				} else {
					s[k] = v[j]; // �׷��� ���� ��쿡�� �ݴ� �� ���� �ְڴٴ� �ǹ�
					j++;
				}
				k++;
			} else {
				// ������ �񱳰� ���̳��� �����ִ� ������ �̹� ������ �������Ƿ� ���� ���� ����ְڴٴ� �ǹ�
				while (i < u.length) {
					s[k] = u[i];
					i++;
					k++;
				}
			}
		}
		// ���� ���� ������ Ŀ�� ���� �ִ� ���̹Ƿ� �׳� ���� �迭���ٰ� �о������ ��
		while (j < v.length) {
			s[k] = v[j];
			j++;
			k++;
		}
	}

	public static void mergesort(int[] s) {
		int n = s.length;
		// �迭�� ũ�Ⱑ 1���� �۾����� ���̻� �ɰ��ʿ䰡 �������� �ǹ�
		if (n > 1) {

			int[] U = new int[n / 2];// ���� �迭 �ɰ���
			int[] V = new int[n - n / 2];// ������ �迭 �ɰ���

			for (int i = 0; i < n / 2; i++) { // ���ʹ迭�� ���ʷ� ����ֱ�
				U[i] = s[i];
			}
			for (int i = 0; i < n - n / 2; i++) {// �����ʹ迭�� ���ʷ� ����ֱ�
				V[i] = s[i + n / 2];
			}
			// ���� ���� ���� ����
			mergesort(U);
			mergesort(V);
			merge(U, V, s);// ����
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] s = null;
		int num = 15;
		s = new int[num];

		Random r = new Random(); // ��ü����d

		for (int i = 0; i < num; i++) // ���� 6���� �̱����� for��
		{
			s[i] = r.nextInt(99) + 1; // 1~10������ �������� �ϳ��� �̾� a[0]~a[5]�� ����
			for (int j = 0; j < i; j++) // �ߺ����Ÿ� ���� for��
			{
				if (s[i] == s[j]) {
					i--;
				}
			}
		}

		for (int i = 0; i < s.length; i++)
			System.out.print(s[i] + " ");
		System.out.println();
		HW(s);

	}

}
