package study.day0616_p;

import java.util.PriorityQueue;

public class lv4_무지의_먹방_라이브 {
	public static void main(String[] args) {
		int[] food_times = { 4, 2, 3, 6, 7, 1, 5, 8 };
		long k = 27;
		System.out.println(solution(food_times, k));
	}

	public static int solution(int[] food_times, long k) {

		int len = food_times.length; // 배열 길이
		long total = 0; // 남은 음식 총 시간
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < len; i++) {
			int curTime = food_times[i];
			if (curTime > 0) {
				total += curTime;
				pq.add(curTime);
			}
		}

		// k시간 전에 다 먹을 수 있다면 그냥 -1 반환
		if (k >= total)
			return -1;

		int finalTime = 0; // 마지막으로 묶어서 제거할 수 있었던 음식 시간
		while (true) {
			int curTime = pq.peek(); // 남은 음식 중 가장 시간 적은 음식
			long minBundle = (long) pq.size() * (curTime - finalTime); // 한 번에 제거할 수 있는지 확인해보자

			// 남은 시간이 한 번에 제거할 수 있는 시간이다.
			if (k >= minBundle) {
				pq.poll();
				k -= minBundle;
				finalTime = curTime;
				// 같은 값을 갖는 애들은 모두 빼줌.
				while (!pq.isEmpty() && pq.peek() == curTime)
					pq.poll();
			} else {
				break; // 이제 묶어서는 못함.
			}
		}
		if (finalTime != 0) {
			for (int i = 0; i < len; i++) {
				food_times[i] = food_times[i] <= finalTime ? 0 : food_times[i] - finalTime;
			}
		}
//		System.out.println(Arrays.toString(food_times));

		int kind = pq.size();
		long bundle = k / kind;
		if (bundle != 0) {
			// kind*bundle만큼 다 빼줌
			for (int i = 0; i < len; i++) {
				if (food_times[i] == 0)
					continue;
				food_times[i] -= bundle;
			}
			k -= bundle * kind;
		}

		// 이제 bundle은 0임.
		// 0만 건너뛰면서 k번째 index가 답
		for (int i = 0; i < len; i++) {
			if (food_times[i] == 0)
				continue;
			if (k == 0)
				return i + 1; // i 번째가 답이다!
			k--;
		}

		return 1;
	}

}
