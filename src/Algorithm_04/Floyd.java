package Algorithm_04;

public class Floyd {
	static int[][] w = { { 0, 1, 100, 1, 5 }, { 9, 0, 3, 2, 100 }, { 100, 100, 0, 4, 100 }, { 100, 100, 2, 0, 3 },
			{ 3, 100, 100, 100, 0 } };

	static int numOfVertex = w.length;
	static int[][] d = new int[numOfVertex][numOfVertex];
	static int[][] p = new int[numOfVertex][numOfVertex];

	public static void main(String[] args) {
		System.out.println("Matrx W:");
		printMatrix(w);

		floyd(numOfVertex);

		System.out.println("\nMatrx D:");
		printMatrix(d);
		System.out.println("\nMatrx P:");
		printMatrix(p);
		System.out.println();

		System.out.print("From v1 to v2: v1");
		path(0, 1);
		System.out.println(" v2 -> length: " + d[0][1]);

		System.out.print("From v1 to v3: v1");
		path(0, 2);
		System.out.println(" v3 -> length: " + d[0][2]);

		System.out.print("From v1 to v4: v1");
		path(0, 1);
		System.out.println(" v4 -> length: " + d[0][3]);

		System.out.print("From v1 to v5: v1");
		path(0, 4);
		System.out.println(" v5 -> length: " + d[0][4]);

		System.out.print("From v2 to v1: v2");
		path(1, 0);
		System.out.println(" v1 -> length: " + d[1][0]);

		System.out.print("From v5 to v3: v5");
		path(4, 2);
		System.out.println(" v3 -> length: " + d[4][2]);

		System.out.print("From v2 to v4: v2");
		path(1, 3);
		System.out.println(" v4 -> length: " + d[1][3]);
	}
	//반복을 통해서 d값 수정 
	public static void floyd(int n) {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				p[i][j] = -1;
		array2DCopy(w, d); // D=W
		// Writes Codes...
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					//k를 거치는 경우가 최소 경로일 조건
					if ((d[i][k] + d[k][j]) < d[i][j]) {
						p[i][j] = k; //Vi에서 Vj로 가는데 Vk를 지난다는 정보 저장
						d[i][j] = d[i][k] + d[k][j]; //k를 지나서가는 경로의 수를 더함
					}
				}
			}
			System.out.println("\nMatrx D: k=" + k);
			printMatrix(d);
		}

	}
	//최단경로 출력 함수
	public static void path(int q, int r) {
		//-1이 아닌경우 == 갈 수 있는 길이 있는 경우
		if (p[q][r] != -1) {
			path(q, p[q][r]);//저장해놓은 p 값을 활용해서 최단거리 경로 재귀적으로 추출
			//재귀적으로 출력을 할 경우 시작경로를 출력할 수 는 없다.
			System.out.print(" v" + (p[q][r]+1));
		}
	}

	// sourceMatrix 값을 destMatrix로 넘기는 거 for문 두번돌리면됨
	public static void array2DCopy(int[][] sourceMatrix, int[][] destMatrix) {
		// Writes Codes...
		for (int i = 0; i < numOfVertex; i++) {
			for (int j = 0; j < numOfVertex; j++) {
				destMatrix[i][j] = sourceMatrix[i][j];
			}
		}
	}

	// 2차행렬 출력 함수
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.printf("%4d", matrix[i][j]);
			}
			System.out.println();
		}
	}
}
