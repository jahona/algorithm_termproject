package Algorithm_03;

import java.util.*;

public class MergeSortMain {

	static int[] s1 = null;
	static int[] s2 = null;
	static int num;
	static int[] tempArray = null; // additional array for inPlaceMergeSort

	public static void main(String[] args) {
		num = 100;

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
		System.out.print("sorted s1 : ");
		print(s1);

		startTime = System.nanoTime();
		inPlaceMergeSort(0, num - 1);
		endTime = System.nanoTime();
		System.out.println("[In-place MergeSort Result]");
		System.out.println("Elapsed Time: " + (endTime - startTime) + "nano sec.");
		System.out.print("sorted s2 : ");
		print(s2);

		/*
		 * for (int i = 0; i < num; i++) { System.out.print(s[i] + " "); }
		 * System.out.println();
		 */
	}

	// ��� �Լ�
	static void print(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	// �Ϲ� �պ�����
	static void mergeSort(int num, int[] mainArray) {
		// �迭�� ���̻� ���� �� ���� �� ����ó��
		if (num > 1) {
			int leftP = num / 2; // ������ �Ϸ��� �ϴ� ���� �迭�� �� ����Ʈ
			int rightP = num - leftP; // ������ �Ϸ��� �ϴ� ���� �迭�� ���� ����Ʈ

			int[] U = new int[leftP]; // �����迭����
			int[] V = new int[rightP]; // �����迭����
			// �����迭�� �� �Է�
			for (int i = 0; i < leftP; i++) {
				U[i] = mainArray[i];
			}
			// �����迭�� �� �Է�
			for (int i = 0; i < rightP; i++) {
				V[i] = mainArray[leftP + i];
			}
			mergeSort(leftP, U); // ���ʺ������Ĵ��Ѵ�
			mergeSort(rightP, V); // ������������
			merge(leftP, rightP, U, V, mainArray);// U,V�� �����ĵǾ� ����
		}

		else if (num == 1)
			return;
	}

	// �պ� �Լ�
	static void merge(int leftP, int rightP, int[] leftArray, int[] rightArray, int[] mainArray) {
		int i, j, mainArrayPoint; // ����,����,��ü, �����̵Ǵ� ����Ʈ�� ����ִ� ����
		i = 0;// �ʱ�ȭ
		j = 0;
		mainArrayPoint = 0;
		// ���ս�ų�迭���� ���� �� �� ����Ʈ������ ������ ��� ����
		while ((i < leftP) && (j < rightP)) {
			// ������ �������� ������
			if (leftArray[i] < rightArray[j]) {
				mainArray[mainArrayPoint] = leftArray[i];// ��������Ʈ�� ���� ���ο� �ִ´�
				i++;
			}
			// ������ �������� ������
			else {
				mainArray[mainArrayPoint] = rightArray[j];// ��������Ʈ�� ���� ���ο� �ִ´�
				j++;
			}
			mainArrayPoint++; // ���� �迭�� �ε����� ����
		}
		// ���� �ٵ��� �����迭�� ������ �����ִ� ��� ���������� �ִ´�.
		while (j < rightArray.length) {
			mainArray[mainArrayPoint] = rightArray[j];
			j++;
			mainArrayPoint++;
		}
		// ���������� �����迭�� ������ �����ִ� ��� ���������� �ִ´�.
		while (i < leftArray.length) {
			mainArray[mainArrayPoint] = leftArray[i];
			i++;
			mainArrayPoint++;
		}
	}

	// �������⵵�� ���̱����� �պ� ����
	public static void inPlaceMergeSort(int low, int high) {
		int mid;// �߰��� ����
		// ���̻� �����ʿ䰡 ���� ��� ����ó��
		if (low < high) {
			mid = (low + high) / 2; // �߰��� ����
			inPlaceMergeSort(low, mid); // �����迭 �и�
			inPlaceMergeSort(mid + 1, high); // �����迭 �и�
			inPlaceMerge(low, mid, high);// ���� �迭 ����
		}
	}

	public static void inPlaceMerge(int low, int mid, int high) {
		int i, j, k;

		i = low;// �����迭�� ���ذ��� �Ǵ� ����
		j = mid + 1;// �����迭�� ���ذ��� �Ǵ� ����
		k = low;// ��ü�迭�� ���ذ��� �Ǵ� ����
		// ���� ���������� �� �����̵Ǵ� ����Ʈ ������ ��ü ũ�⸦ �ѱ������� �� ���� �ݺ�
		while (i <= mid && j <= high) {
			if (s2[i] < s2[j]) { // ������ ���� ���
				tempArray[k] = s2[i]; // �ӽù迭�� ������ �ֱ�
				i++; // ���� ���(����)�����Ƿ� ��������Ʈ �� ����
			} else {// ������ ���� ���
				tempArray[k] = s2[j];// �ӽù迭�� ������ �ֱ�
				j++;// ���� ���(����)�����Ƿ� ��������Ʈ �� ����
			}
			k++;// ��ü�迭�� ������ �Ǵ� ����Ʈ���� ������ �����Ƿ� ���� ����Ʈ�� �ϱ����� ����
		}
		// ���� ���� ���� �� �������
		if (i > mid) {
			// ���� ������ �ӽù迭�� �� �ִ´�.
			for (int a = 0; a <= high - j; a++)
				tempArray[k + a] = s2[j + a];
		}
		// ���� ���� ���� �� �������
		else {
			// ���� ������ �ӽù迭�� �� �ִ´�
			for (int a = 0; a <= mid - i; a++)
				tempArray[k + a] = s2[i + a];
		}
		// �ش� �ӽù迭�� �ٽ� ���� �⺻ �迭�� �ٽ� ����
		for (int a = 0; a <= high; a++) {
			s2[a] = tempArray[a];
		}
	}
}