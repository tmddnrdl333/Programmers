package ing;

public class lv3_징검다리_건너기_이분탐색 {
	public static void main(String[] args) {
		int[] stones = { 12415, 4, 100, 200, 150, 200, 4, 2000, 2444, 1 };
		int k = 1;
		System.out.println(solution(stones, k));
	}

	public static int solution(int[] stones, int k) {
		int answer = 0;

		int max = 0;
		for (int st : stones)
			max = st > max ? st : max;

		int s = 0, e = max * 2;
		while (s < e) {
			int m = (s + e + 1) / 2;
			int cnt = 0, cmax = 0;
			for (int st : stones) {
				if (st <= m) {
					cnt++;
					cmax = cnt > cmax ? cnt : cmax;
				} else {
					cnt = 0;
				}
			}
			if (cmax < k) {
				s = m;
			} else {
				answer = m;
				e = m - 1;
			}
		}

		return answer;
	}
}
