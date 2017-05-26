package Algorithm_04;

public class Test {
	static int[][] w = { { 0, 4, 100, 100, 100, 10, 100 }, { 3, 0, 100, 18, 100, 100, 100 },
			{ 100, 6, 0, 100, 100, 100, 100 }, { 100, 5, 15, 0, 2, 19, 5 }, { 100, 100, 12, 1, 0, 100, 100 },
			{ 100, 100, 100, 100, 100, 0, 10 }, { 100, 100, 100, 8, 100, 100, 0 } };

	static int numOfVertex = w.length;
	static int[][] d = new int[numOfVertex][numOfVertex];
	static int[][] p = new int[numOfVertex][numOfVertex];

	public static void main(String[] args) {
		System.out.println("Matrx W:");
		printMatrix(w);

		floyd(numOfVertex);

		System.out.println();

		System.out.print("From v7 to v3: v7");
		path(6, 2);
		System.out.println(" v3 -> length: " + d[6][2]);
	}

	// 반복을 통해서 d값 수정
	public static void floyd(int n) {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				p[i][j] = -1;
		array2DCopy(w, d); // D=W
		// Writes Codes...
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if ((d[i][k] + d[k][j]) < d[i][j]) {
						p[i][j] = k;
						d[i][j] = d[i][k] + d[k][j];
					}
				}
			}
			System.out.println("\nMatrx D: k=" + k);
			printMatrix(d);
			System.out.println("\n k=" + k);
			printMatrix(p);
		}
	}

	//
	public static void path(int q, int r) {
		if (p[q][r] != -1) {
			path(q, p[q][r]);
			System.out.print(" v" + (p[q][r] + 1));
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
