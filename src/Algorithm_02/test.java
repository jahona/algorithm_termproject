package Algorithm_02;

import java.util.Random;

public class test {
	// HOMEWORK용 함수
	static void HW(int[] a) {
		int[] first = new int[a.length / 2];// 정렬된 배열의 작은값부터 저장할 배열
		int[] second = new int[a.length / 2 + 1];// 정렬된 배열의 그 다음값 부터 저장할 배열

		int sum_first = 0; // 변수 초기화
		int sum_second = 0;

		mergesort(a); // 정렬
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		// 정렬된 배열을 순서대로 first[]에 삽입
		for (int i = 0; i < a.length / 2; i++) {
			first[i] = a[i];
		}
		// 정렬된 배열을 순서대로 second[]에 삽입
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
		// first[]출력 및 sum_first에 더하기
		System.out.print("\nfirst : ");
		for (int i = 0; i < first.length; i++) {
			System.out.print(first[i] + " ");
			sum_first += first[i];
		}
		// second[]출력 및 sum_second에 더하기
		System.out.println();
		System.out.print("second : ");
		for (int i = 0; i < second.length; i++) {
			System.out.print(second[i] + " ");
			sum_second += second[i];
		}
		System.out.println("\nsum_second - sum_first = MAX?");
		System.out.println(sum_second + " - " + sum_first + " = " + (sum_second - sum_first));
	}

	// merge 함수 수도코드의 index의 크기를 나타내는 파라미터는 불필요하다고 생각되어 제거하였습니다.
	public static void merge(int[] u, int[] v, int[] s) {
		// 각 왼쪽/오른쪽/메인 배열의 포인터(c에서 포인터x) 변수
		int i = 0;
		int j = 0;
		int k = 0;

		while (i < u.length) {// 비교할 때 중간 값을 넘어버리면 다음으로 넘어가겠다는 의미
			if (j < v.length) {
				if (u[i] < v[j]) { // 크기 비교후 작을 경우 정렬시킬 배열에 넣겠다는 의미
					s[k] = u[i];
					i++;
				} else {
					s[k] = v[j]; // 그렇지 않을 경우에는 반대 편 것을 넣겠다는 의미
					j++;
				}
				k++;
			} else {
				// 오른쪽 비교가 끝이나면 남아있는 왼쪽은 이미 정렬이 되있으므로 왼쪽 것을 집어넣겠다는 의미
				while (i < u.length) {
					s[k] = u[i];
					i++;
					k++;
				}
			}
		}
		// 남는 것이 있으면 커서 남아 있는 것이므로 그냥 원래 배열에다가 밀어넣으면 됨
		while (j < v.length) {
			s[k] = v[j];
			j++;
			k++;
		}
	}

	public static void mergesort(int[] s) {
		int n = s.length;
		// 배열의 크기가 1보다 작아지면 더이상 쪼갤필요가 없어짐을 의미
		if (n > 1) {

			int[] U = new int[n / 2];// 왼쪽 배열 쪼개기
			int[] V = new int[n - n / 2];// 오른쪽 배열 쪼개기

			for (int i = 0; i < n / 2; i++) { // 왼쪽배열에 차례로 집어넣기
				U[i] = s[i];
			}
			for (int i = 0; i < n - n / 2; i++) {// 오른쪽배열에 차례로 집어넣기
				V[i] = s[i + n / 2];
			}
			// 정렬 분할 정복 시작
			mergesort(U);
			mergesort(V);
			merge(U, V, s);// 병합
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] s = null;
		int num = 15;
		s = new int[num];

		Random r = new Random(); // 객체생성d

		for (int i = 0; i < num; i++) // 숫자 6개를 뽑기위한 for문
		{
			s[i] = r.nextInt(99) + 1; // 1~10숫자중 랜덤으로 하나를 뽑아 a[0]~a[5]에 저장
			for (int j = 0; j < i; j++) // 중복제거를 위한 for문
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
