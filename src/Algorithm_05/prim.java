package Algorithm_05;

import java.util.Scanner;

/**
 * 정올, 그리디, 최소비용 신장트리 프림알고리즘
 * 
 * @author User
 *
 */
public class prim {

	static int cost = 0;

	public static void main(String[] args) {

		

		 int n = 10;
		 int[][] array = 
			 		 { { 0, 32, 0, 17, 0, 0, 0, 0, 0, 0 },
			 		   { 32, 0, 0, 0, 45, 0, 0, 0, 0, 0 },
			 		   { 0, 0, 0, 18, 0, 0, 5, 0, 0, 0 },
			 		   { 17, 0, 18, 0, 10, 0, 0, 3, 0, 0 },
			 		   { 0, 45, 0, 10, 0, 28, 0, 0, 25, 0 },
			 		 { 0, 0, 0, 0, 28, 0, 0, 0, 0, 6 }, 
			 		 { 0, 0, 5, 17, 0, 0, 0, 59, 0, 0 },
			 		 { 0, 0, 0, 3, 0, 0, 59, 0, 4, 0 },
			 		 { 0, 0, 0, 0, 25, 0, 0, 4, 0, 12 },
			 		 { 0, 0, 0, 0, 0, 6, 0, 0, 12, 0 }
			 		 };

		int[] visited = new int[n];
		visited[0] = 1;

		prim(array, visited, 0);

		System.out.println(cost);
	}

	
	/**
	 * 프림 알고리즘을 이용하여 처리 
	 */
	public static void prim(int[][] array, int[] visited, int depth) {

		// 모든 노드에 도달하면 종료 
		if (depth == visited.length - 1) {
			return;
		}

		int min = Integer.MAX_VALUE;
		int indexX = 0;
		int indexY = 0;

		for (int i = 0; i < visited.length; i++) {

			// 연결된 노드라면 처리 
			if (visited[i] == 1) {
				for (int j = 0; j < array.length; j++) {
					
					if (i == j)
						continue;

					if (visited[j] == 1)
						continue;

					// 연결되지 않은 노드중 가장 작은 값을 가지는 노드를 추가 
					if (array[i][j] < min) {
						min = array[i][j];
						indexX = i;
						indexY = j;
						System.out.println(i + " "+j);
					}
				}
			}
		}

		visited[indexX] = 1;
		visited[indexY] = 1;
		cost += min;

		prim(array, visited, depth + 1);
	}
}