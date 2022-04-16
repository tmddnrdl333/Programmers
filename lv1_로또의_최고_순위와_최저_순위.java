package study.day0414_p;

import java.util.HashSet;
import java.util.Set;

public class lv1_로또의_최고_순위와_최저_순위 {
	public static void main(String[] args) {
		int[] lottos = { 0, 0, 0, 0, 0, 0 };
		int[] win_nums = { 38, 19, 20, 40, 15, 25 };
		System.out.println(solution(lottos, win_nums));
	}

	public static int[] solution(int[] lottos, int[] win_nums) {
		int[] answer = new int[2];
		Set<Integer> wset = new HashSet<>();
		for (int w : win_nums)
			wset.add(w);
		wset.add(0);
		int max = 0, min = 0;
		for (int l : lottos) {
			if (wset.contains(l)) {
				max++;
				if (l != 0)
					min++;
			}
		}
		max = 7 - max;
		min = 7 - min;
		answer[0] = max > 5 ? 6 : max;
		answer[1] = min > 5 ? 6 : min;
		return answer;
	}
}
