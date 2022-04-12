package ing;

import java.util.Arrays;

public class lv4_호텔_방_배정_ByteArr_Failed {
	public static void main(String[] args) {
		long k = 10;
		long[] room_number = { 1, 3, 1, 1, 1, 1 };
		System.out.println(Arrays.toString(solution(k, room_number)));
	}

	public static long[] solution(long k, long[] room_number) {
		int N = room_number.length;
		long[] answer = new long[N];
		int b = (int) (k / 8000) + 1;
		byte[][] v = new byte[b][1000];

		int idx = 0;
		for (long rn : room_number) {
			long cur = rn;
			int i1 = (int) (rn / 8000);
			int temp = (int) (rn % 8000);
			int i2 = temp / 8;
			int bit = temp % 8;
			if ((v[i1][i2] & 1 << bit) == 0) {
				v[i1][i2] |= 1 << bit;
				answer[idx++] = cur;
			} else {
				while ((v[i1][i2] & 1 << bit) != 0) {
					cur++;
					if (bit < 7)
						bit++;
					else {
						bit = 0;
						if (i2 < 7999)
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
