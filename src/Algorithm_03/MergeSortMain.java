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

	// 출력 함수
	static void print(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	// 일반 합병정렬
	static void mergeSort(int num, int[] mainArray) {
		// 배열을 더이상 나눌 수 없을 때 예외처리
		if (num > 1) {
			int leftP = num / 2; // 정렬을 하려고 하는 좌측 배열의 끝 포인트
			int rightP = num - leftP; // 정렬을 하려고 하는 우측 배열의 시작 포인트

			int[] U = new int[leftP]; // 좌측배열생성
			int[] V = new int[rightP]; // 우측배열생성
			// 좌측배열에 값 입력
			for (int i = 0; i < leftP; i++) {
				U[i] = mainArray[i];
			}
			// 우측배열에 값 입력
			for (int i = 0; i < rightP; i++) {
				V[i] = mainArray[leftP + i];
			}
			mergeSort(leftP, U); // 왼쪽부터정렬다한다
			mergeSort(rightP, V); // 오른쪽정렬한
			merge(leftP, rightP, U, V, mainArray);// U,V는 다정렬되어 있음
		}

		else if (num == 1)
			return;
	}

	// 합병 함수
	static void merge(int leftP, int rightP, int[] leftArray, int[] rightArray, int[] mainArray) {
		int i, j, mainArrayPoint; // 좌측,우측,전체, 기준이되는 포인트를 찍어주는 변수
		i = 0;// 초기화
		j = 0;
		mainArrayPoint = 0;
		// 병합시킬배열들이 현재 각 측 포인트값보다 작을때 계속 실행
		while ((i < leftP) && (j < rightP)) {
			// 좌측이 우측보다 작을때
			if (leftArray[i] < rightArray[j]) {
				mainArray[mainArrayPoint] = leftArray[i];// 좌측포인트의 값을 메인에 넣는다
				i++;
			}
			// 우측이 좌측보다 작을때
			else {
				mainArray[mainArrayPoint] = rightArray[j];// 우측포인트의 값을 메인에 넣는다
				j++;
			}
			mainArrayPoint++; // 원래 배열의 인덱스를 증가
		}
		// 위가 다돌고도 우측배열의 값들이 남아있는 경우 순차적으로 넣는다.
		while (j < rightArray.length) {
			mainArray[mainArrayPoint] = rightArray[j];
			j++;
			mainArrayPoint++;
		}
		// 마찬가지로 좌측배열의 값들이 남아있는 경우 순차적으로 넣는다.
		while (i < leftArray.length) {
			mainArray[mainArrayPoint] = leftArray[i];
			i++;
			mainArrayPoint++;
		}
	}

	// 공간복잡도를 줄이기위한 합병 정렬
	public static void inPlaceMergeSort(int low, int high) {
		int mid;// 중간값 설정
		// 더이상 나눌필요가 없는 경우 예외처리
		if (low < high) {
			mid = (low + high) / 2; // 중간값 설정
			inPlaceMergeSort(low, mid); // 좌측배열 분리
			inPlaceMergeSort(mid + 1, high); // 우측배열 분리
			inPlaceMerge(low, mid, high);// 나눈 배열 병합
		}
	}

	public static void inPlaceMerge(int low, int mid, int high) {
		int i, j, k;

		i = low;// 좌측배열의 기준값이 되는 변수
		j = mid + 1;// 우측배열의 기준값이 되는 변수
		k = low;// 전체배열의 기준값이 되는 변수
		// 전과 마찬가지로 각 기준이되는 포인트 값들이 전체 크기를 넘기지않을 때 까지 반복
		while (i <= mid && j <= high) {
			if (s2[i] < s2[j]) { // 좌측이 작은 경우
				tempArray[k] = s2[i]; // 임시배열에 좌측값 넣기
				i++; // 값을 사용(대입)했으므로 좌측포인트 값 증가
			} else {// 우측이 작은 경우
				tempArray[k] = s2[j];// 임시배열에 우측값 넣기
				j++;// 값을 사용(대입)했으므로 우측포인트 값 증가
			}
			k++;// 전체배열중 기준이 되는 포인트까지 정렬을 했으므로 다음 포인트를 하기위해 증가
		}
		// 먼저 좌측 값이 다 끝난경우
		if (i > mid) {
			// 남은 값들은 임시배열에 다 넣는다.
			for (int a = 0; a <= high - j; a++)
				tempArray[k + a] = s2[j + a];
		}
		// 먼저 우측 값이 다 끝난경우
		else {
			// 남은 값들은 임시배열에 다 넣는다
			for (int a = 0; a <= mid - i; a++)
				tempArray[k + a] = s2[i + a];
		}
		// 해당 임시배열을 다시 원래 기본 배열에 다시 대입
		for (int a = 0; a <= high; a++) {
			s2[a] = tempArray[a];
		}
	}
}