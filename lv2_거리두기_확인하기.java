package ps_Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lv2_거리두기_확인하기 {
	public static void main(String[] args) {
		String[][] places = { { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
				{ "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" }, { "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
				{ "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" }, { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" } };

		System.out.println(Arrays.toString(solution(places)));
	}

	static public int[] solution(String[][] places) {
		int[] answer = new int[5];

		int[][] map = new int[5][5];
		// i 번째 대기실에 대하여
		for (int i = 0; i < 5; i++) {

			List<int[]> list = new ArrayList<>();

			// j 행 k 열
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					char cur = places[i][j].charAt(k);
					int num = 0;
					if (cur == 'P') {
						num = 1;
						list.add(new int[] { j, k });
					} else if (cur == 'O')
						num = 0;
					else
						num = -1;
					map[j][k] = num;
				}
			}

			for (int j = 0; j < 5; j++) {
				System.out.println(Arrays.toString(map[j]));
			}
			System.out.println();

			// 이번 대기실 된다면 answer에 1 저장, 아니면 0 저장

			boolean isSafe = true;
			int size = list.size();
			outer: for (int j = 0; j < size - 1; j++) {
				for (int k = j + 1; k < size; k++) {
					int dis = Math.abs(list.get(j)[0] - list.get(k)[0]) + Math.abs(list.get(j)[1] - list.get(k)[1]);
					if (dis > 2) // 맨허튼 거리가 2 넘으면 괜찮
						continue;
					else if (dis < 2) {
						isSafe = false;
						break outer;
					} else {
						// 같은 행/열이고 2거리인 경우
						if (list.get(j)[0] == list.get(k)[0] || list.get(j)[1] == list.get(k)[1]) {
							int r = (list.get(j)[0] + list.get(k)[0]) / 2;
							int c = (list.get(j)[1] + list.get(k)[1]) / 2;
							if (map[r][c] != -1)
								isSafe = false;
						}
						// 대각인 경우
						else {
							int r1 = list.get(j)[0];
							int c1 = list.get(k)[1];
							int r2 = list.get(k)[0];
							int c2 = list.get(j)[1];
							if (map[r1][c1] != -1 || map[r2][c2] != -1)
								isSafe = false;
						}
					}
				}
			}
			if (isSafe)
				answer[i] = 1;
		}

		return answer;
	}
}
