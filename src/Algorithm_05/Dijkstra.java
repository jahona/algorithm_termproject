package Algorithm_05;

class Edge {
	int start;
	int end;

	public Edge(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public String toString() {
		return "(v" + (start + 1) + ", v" + (end + 1) + ")";
	}
}

public class Dijkstra {
	static int[][] w = { { 0, 1, 100, 1, 5 }, { 9, 0, 3, 2, 100 }, { 100, 100, 0, 4, 100 }, { 100, 100, 2, 0, 3 },
			{ 3, 100, 100, 100, 0 } };

	static int[] touch = new int[w.length];
	static int[] length = new int[w.length];

	public static void main(String[] args) {
		int numOfVertex = w.length;
		System.out.println("Matrx W:");
		printMatrix(w);

		Edge[] f = null;
		f = dijkstra(numOfVertex);

		System.out.println();
		for (int i = 0; i < numOfVertex * (numOfVertex - 1) / 2; i++) {
			if (f[i] == null)
				break;
			System.out.println(f[i].toString());
		}
	}

	public static Edge[] dijkstra(int n) {
		Edge[] f = new Edge[n * (n - 1) / 2];
		int vnear = 0;
		for (int i = 0; i < n; i++) {
			touch[i] = 0;
			length[i] = w[0][i];
		}

		for (int dest = 1; dest < n; dest++) {
			int min = 10000;
			for (int i = 1; i < n; i++) {
				if (0 < length[i] && length[i] < min) { // length[i]를 검사하여
					min = length[i]; // Y에 가장 가까이 있는 노드
					vnear = i; // vnear를 찾는다.
				}
			}
			Edge e = new Edge(touch[vnear],vnear); //touch[near]와vnear가 정점인 edge e
			f[dest - 1] = e; //찾아낸 e를 f배열에 차례로 넣는다.

			for (int i = 1; i < n; i++) {
				if (length[vnear] + w[vnear][i] < length[i]) {
					length[i] = length[vnear] + w[vnear][i]; // Y에 속하지 않는
					touch[i] = vnear; // 조건에 맞는 노드에 대해 length[i] 및
				} // touch[i]를 갱신한다.
				length[vnear] = -1; // vnear가 인덱스인 노드를 Y에 추가한다.
			}
		}
		return f;
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.printf("%4d", matrix[i][j]);
			}
			System.out.println();
		}
	}
}
