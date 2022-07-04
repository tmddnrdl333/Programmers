package study.day0630_p;

import java.util.Arrays;
import java.util.StringTokenizer;

public class lv1_신고_결과_받기 {
	public static void main(String[] args) {
		String[] id_list = { "muzi", "frodo", "apeach", "neo" };
		String[] report = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
		int k = 2;
		System.out.println(Arrays.toString(solution(id_list, report, k)));
	}

	public static int[] solution(String[] id_list, String[] report, int k) {
		int len = id_list.length;
		int[] my_id_list = new int[len];
		for (int i = 0; i < len; i++)
			my_id_list[i] = i;

		boolean[][] my_report = new boolean[len][len];
		for (String report_item : report) {
			StringTokenizer st = new StringTokenizer(report_item);
			String from = st.nextToken(), to = st.nextToken();
			int r = 0, c = 0;
			for (int i = 0; i < len; i++)
				if (from.equals(id_list[i]))
					r = i;
				else if (to.equals(id_list[i]))
					c = i;
			my_report[r][c] = true;
		}

		boolean[] reported = new boolean[len];
		for (int j = 0; j < len; j++) {
			int cnt = 0;
			for (int i = 0; i < len; i++) {
				if (my_report[i][j])
					cnt++;
			}
			if (cnt >= k)
				reported[j] = true;
		}

		int[] answer = new int[len];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (my_report[i][j] && reported[j])
					answer[i]++;
			}
		}

		return answer;
	}
}
