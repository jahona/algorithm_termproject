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
	//�ݺ��� ���ؼ� d�� ���� 
	public static void floyd(int n) {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				p[i][j] = -1;
		array2DCopy(w, d); // D=W
		// Writes Codes...
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					//k�� ��ġ�� ��찡 �ּ� ����� ����
					if ((d[i][k] + d[k][j]) < d[i][j]) {
						p[i][j] = k; //Vi���� Vj�� ���µ� Vk�� �����ٴ� ���� ����
						d[i][j] = d[i][k] + d[k][j]; //k�� ���������� ����� ���� ����
					}
				}
			}
			System.out.println("\nMatrx D: k=" + k);
			printMatrix(d);
		}

	}
	//�ִܰ�� ��� �Լ�
	public static void path(int q, int r) {
		//-1�� �ƴѰ�� == �� �� �ִ� ���� �ִ� ���
		if (p[q][r] != -1) {
			path(q, p[q][r]);//�����س��� p ���� Ȱ���ؼ� �ִܰŸ� ��� ��������� ����
			//��������� ����� �� ��� ���۰�θ� ����� �� �� ����.
			System.out.print(" v" + (p[q][r]+1));
		}
	}

	// sourceMatrix ���� destMatrix�� �ѱ�� �� for�� �ι��������
	public static void array2DCopy(int[][] sourceMatrix, int[][] destMatrix) {
		// Writes Codes...
		for (int i = 0; i < numOfVertex; i++) {
			for (int j = 0; j < numOfVertex; j++) {
				destMatrix[i][j] = sourceMatrix[i][j];
			}
		}
	}

	// 2����� ��� �Լ�
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.printf("%4d", matrix[i][j]);
			}
			System.out.println();
		}
	}
}
