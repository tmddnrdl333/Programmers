package ing;

import java.util.PriorityQueue;

public class lv3_징검다리_건너기_PqVer_Fail {
	public static void main(String[] args) {
		int[] stones = { 2, 4, 5, 3, 0, 0, 0, 4, 2, 5, 1 };
		int k = 3;
		System.out.println(solution(stones, k));
	}

	public static int solution(int[] stones, int k) {
		int answer = 0;

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int s : stones) {
			pq.offer(s);
		}

		w: while (true) {
			int cur = pq.poll();
			int cnt = 0;
			for (int s : stones) {
				if (s <= cur)
					cnt++;
				else
					cnt = 0;
				if (cnt == k) {
					answer = cur;
					break w;
				}
			}

		}

		return answer;
	}
}
