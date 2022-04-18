package study.day0414_p;

import java.util.Arrays;

public class lv2_행렬_테두리_회전하기 {
	public static void main(String[] args) {
		int rows = 6;
		int columns = 6;
		int[][] queries = { { 2, 2, 5, 4 }, { 3, 3, 6, 6 }, { 5, 1, 6, 3 } };
		System.out.println(Arrays.toString(solution(rows, columns, queries)));
	}

	static int[][] map;

	public static int[] solution(int rows, int columns, int[][] queries) {
		int size = queries.length;
		int[] answer = new int[size];

		map = new int[rows][columns];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				map[i][j] = i * columns + j + 1;

		int i = 0;
		for (int[] arr : queries) {
			int r1 = arr[0] - 1, c1 = arr[1] - 1;
			int r2 = arr[2] - 1, c2 = arr[3] - 1;
			answer[i++] = rotate(r1, c1, r2, c2);
		}

		return answer;
	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static int rotate(int r1, int c1, int r2, int c2) {
		int size = 2 * (r2 - r1 + c2 - c1);
		int[] temp = new int[size];
		int d = 0, nr = r1, nc = c1, min = Integer.MAX_VALUE;
		for (int i = 0; i < size; i++) {
			temp[i] = map[nr][nc];
			min = temp[i] < min ? temp[i] : min;
			nr += dr[d];
			nc += dc[d];
			int flag = 0;
			if (nr == r1 || nr == r2)
				flag++;
			if (nc == c1 || nc == c2)
				flag++;
			if (flag == 2)
				d++;
		}
		d = 0;
		for (int i = 0; i < size; i++) {
			nr += dr[d];
			nc += dc[d];
			map[nr][nc] = temp[i];
			int flag = 0;
			if (nr == r1 || nr == r2)
				flag++;
			if (nc == c1 || nc == c2)
				flag++;
			if (flag == 2)
				d++;
		}
		return min;
	}
}
