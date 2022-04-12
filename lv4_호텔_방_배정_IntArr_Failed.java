package ing;

import java.util.Arrays;

public class lv4_호텔_방_배정_IntArr_Failed {
	public static void main(String[] args) {
		long k = 10;
		long[] room_number = { 1, 3, 1, 1, 1, 1 };
		System.out.println(Arrays.toString(solution(k, room_number)));
	}

	public static long[] solution(long k, long[] room_number) {
		int N = room_number.length;
		long[] answer = new long[N];
		int b = (int) (k / 3200) + 1;
		int[][] v = new int[b][100];

		int idx = 0;
		for (long rn : room_number) {
			long cur = rn;
			int i1 = (int) (rn / 3200);
			int temp = (int) (rn % 3200);
			int i2 = temp / 32;
			int bit = temp % 32;
			if ((v[i1][i2] & 1 << bit) == 0) {
				v[i1][i2] |= 1 << bit;
				answer[idx++] = cur;
			} else {
				while ((v[i1][i2] & 1 << bit) != 0) {
					cur++;
					if (bit < 31)
						bit++;
					else {
						bit = 0;
						if (i2 < 3199)
							i2++;
						else {
							i2 = 0;
							i1++;
						}
					}
				}
				v[i1][i2] |= 1 << bit;
				answer[idx++] = cur;
			}
		}

		return answer;
	}
}
