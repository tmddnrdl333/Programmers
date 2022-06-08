package study.day0602_p;

import java.util.Arrays;

public class lv3_합승_택시_요금_floyd {

	public static void main(String[] args) {
		int[][] fares = { { 5, 7, 9 }, { 4, 6, 4 }, { 3, 6, 1 }, { 3, 2, 3 }, { 2, 1, 6 } };
		System.out.println(solution(7, 3, 4, 1, fares));
	}

	static final int INF = 9000001; // 그냥 올리다보니까 효율성테스트 점수가 점점 올라가더니 통과됐음..

	public static int solution(int n, int s, int a, int b, int[][] fares) {

		// floyd-warshall ~
		int[][] adj = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(adj[i], INF);
			adj[i][i] = 0;
		}
		for (int[] arr : fares) {
			adj[arr[0] - 1][arr[1] - 1] = arr[2];
			adj[arr[1] - 1][arr[0] - 1] = arr[2];
		}

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				if (i == k)
					continue;
				for (int j = 0; j < n; j++) {
					if (i == j || k == j)
						continue;
					if (adj[i][j] > adj[i][k] + adj[k][j]) {
						adj[i][j] = adj[i][k] + adj[k][j];
					}
				}
			}
		}
		// ~ f-w

//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(adj[i][j] + "\t");
//			}
//			System.out.println();
//		}

		// 경유지를 어디로 할지
		int minv = -1, mindist = INF;
		for (int i = 0; i < n; i++) {
			int temp = adj[s - 1][i] + adj[i][a - 1] + adj[i][b - 1];
			if (temp < mindist) {
				mindist = temp;
				minv = i;
			}
		}
//		System.out.println(minv + " " + mindist);

		return mindist;
	}
}
