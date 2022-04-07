package study.day0407_p;

import java.util.Stack;

public class lv1_크레인_인형뽑기_게임 {
	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 3 }, { 0, 2, 5, 0, 1 }, { 4, 2, 4, 4, 2 },
				{ 3, 5, 1, 3, 1 } };
		int[] moves = { 1, 5, 3, 5, 1, 2, 1, 4 };
		System.out.println(solution(board, moves));
	}

	static int solution(int[][] board, int[] moves) {
		int answer = 0;

		int H = board.length;
		Stack<Integer> s = new Stack<>();

		for (int idx : moves) {
			Point pick = null;
			for (int h = 0; h < H; h++) {
				if (board[h][idx - 1] != 0) {
					pick = new Point(h, idx - 1);
					break;
				}
			}
			if (pick == null)
				continue;

			int picked = board[pick.r][pick.c];
			board[pick.r][pick.c] = 0;
			if (!s.isEmpty() && s.peek() == picked) {
				s.pop();
				answer += 2;
				continue;
			}

			s.add(picked);
		}

		return answer;
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
