package study.day0721_p;

import java.util.Arrays;

public class lv3_자물쇠와_열쇠 {
	public static void main(String[] args) {
		int[][] key = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int[][] lock = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		System.out.println(solution(key, lock));
	}

	static int k_len, l_len;
	static int blank;

	public static boolean solution(int[][] key, int[][] lock) {

		k_len = key.length;
		l_len = lock.length;

		for (int i = 0; i < l_len; i++)
			for (int j = 0; j < l_len; j++)
				if (lock[i][j] == 0)
					blank++;

		int cnt = 4;

		while (true) {
			if (test(key, lock))
				return true;
			else {
				key = rotate(key);
				cnt--;
			}
			if (cnt == 0)
				return false;
		}

	}

	public static void print(int[][] what) {
		for (int i = 0; i < k_len; i++)
			System.out.println(Arrays.toString(what[i]));
	}

	public static boolean test(int[][] key, int[][] lock) {

		int start = 1 - k_len; // l_len-1 + k_len-1 까지 가면 됨
		int end = l_len + k_len - 2; // 상동
		for (int i = start; i <= end; i++) {
			out: for (int j = start; j <= end; j++) {
				// 한 케이스 시작
				int fill = 0;
				for (int r = 0; r < k_len; r++) {
					for (int c = 0; c < k_len; c++) {
						if (i + r < 0 || i + r >= l_len || j + c < 0 || j + c >= l_len)
							continue;
						if (key[r][c] == 1 && lock[i + r][j + c] == 0)
							fill++;
						else if (key[r][c] + lock[i + r][j + c] != 1)
							continue out;
					}
				}
				if (fill == blank)
					return true;
			}
		}
		return false;
	}

	public static int[][] rotate(int[][] oldKey) {
		int[][] newKey = new int[k_len][k_len];
		for (int i = 0; i < k_len; i++) {
			for (int j = 0; j < k_len; j++) {
				newKey[i][j] = oldKey[j][k_len - 1 - i];
			}
		}
		return newKey;
	}

}
