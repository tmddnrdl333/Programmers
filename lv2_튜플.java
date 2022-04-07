package study.day0407_p;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class lv2_튜플 {
	public static void main(String[] args) {

		String s = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
		System.out.println(Arrays.toString(solution(s)));
	}

	static int[] solution(String s) {
		Map<Integer, Integer> map = new HashMap<>();
		Set<Integer> set = new HashSet<>();
		s = s.replaceAll("\\{", "");
		StringTokenizer st = new StringTokenizer(s, "}");
		while (st.hasMoreTokens()) {
			String temp = st.nextToken();
			if (temp.charAt(0) == ',')
				temp = temp.substring(1);
			StringTokenizer ist = new StringTokenizer(temp, ",");
			while (ist.hasMoreTokens()) {
				int cur = Integer.parseInt(ist.nextToken());
				map.put(cur, map.getOrDefault(cur, 0) + 1);
				set.add(cur);
			}
		}

		int size = map.size();
		int[] answer = new int[size];
		for (int num : set) {
			answer[size - map.get(num)] = num;
		}
		return answer;
	}
}
