package study.day0630_p;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lv3_셔틀버스 {

	public static void main(String[] args) {
		System.out.println("@1@");
		System.out.println(solution(1, 1, 5, new String[] { "08:00", "08:01", "08:02", "08:03" }));
		System.out.println("@2@");
		System.out.println(solution(2, 10, 2, new String[] { "09:10", "09:09", "08:00" }));
		System.out.println("@3@");
		System.out.println(solution(2, 1, 2, new String[] { "09:00", "09:00", "09:00", "09:00" }));
		System.out.println("@4@");
		System.out.println(solution(1, 1, 5, new String[] { "00:01", "00:01", "00:01", "00:01", "00:01" }));
		System.out.println("@5@");
		System.out.println(solution(1, 1, 15, new String[] { "23:59" }));
		System.out.println("@6@");
		System.out.println(solution(10, 60, 45, new String[] { "23:59", "23:59", "23:59", "23:59", "23:59", "23:59",
				"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59" }));
	}

	static class Shuttle {
		int time;
		List<Integer> crew;

		public Shuttle(int time) {
			this.time = time;
			crew = new ArrayList<>();
		}

		@Override
		public String toString() {
			return "Shuttle [time=" + time + ", crew=" + crew + "]";
		}
	}

	public static String solution(int n, int t, int m, String[] timetable) {

		// 셔틀 도착 시간
		Shuttle[] shuttles = new Shuttle[n];
		for (int i = 0; i < n; i++)
			shuttles[i] = new Shuttle(9 * 60 + t * i);

		// 크루 도착 시간 (정렬)
		int len = timetable.length;
		int[] crew = new int[len];
		for (int i = 0; i < len; i++) {
			int h = Integer.parseInt(timetable[i].substring(0, 2));
			int min = Integer.parseInt(timetable[i].substring(3, 5));
			int que = h * 60 + min;
			crew[i] = que;
		}
		Arrays.sort(crew);

		// 콘이 없을 때 타는 방법대로 태우기
		int c = 0;
		for (int i = 0; i < n; i++) {
			Shuttle cur_s = shuttles[i];
			int s_time = cur_s.time;

			while (c < len && crew[c] <= s_time && cur_s.crew.size() != m) {
				cur_s.crew.add(crew[c++]);
			}
		}

		// 콘을 마지막 차부터 타보기
		int ans_time = 0;
		Shuttle last_s = shuttles[n - 1];
		List<Integer> s_crew = last_s.crew;
		if (s_crew.size() < m) { // 마지막 셔틀 자리 남았으면 그냥 그시간에 가기
			ans_time = last_s.time;
		} else { // 마지막 셔틀도 꽉참
			ans_time = s_crew.get(m - 1) - 1;
		}

		int ans_h = ans_time / 60, ans_min = ans_time % 60;

		StringBuilder sb = new StringBuilder();
		sb.append(ans_h < 10 ? "0" + ans_h : ans_h).append(":").append(ans_min < 10 ? "0" + ans_min : ans_min);
		String answer = sb.toString();
		return answer;
	}
}
