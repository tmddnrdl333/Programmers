package ps_Programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class lv3_보석_쇼핑 {
	public static void main(String[] args) {
		String[] gems = { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" };
		System.out.println(Arrays.toString(solution(gems)));
	}

	public static int[] solution(String[] gems) {
		int[] answer = { 1, gems.length };

		// 개수 체크용 set
		Set<String> set = new HashSet<>();
		int len = gems.length;
		for (int i = 0; i < len; i++)
			set.add(gems[i]);
		int size = set.size();

		Map<String, Integer> map = new HashMap<>();

		// 양 끝 포인터
		int left = 0;
		int right = size;
		int[] cnt = new int[size];
		for (int i = 0; i < size; i++) {
			map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
		}
		while (true) {
			if (right == len && map.size() != size)
				break;
			if (map.size() == size) {
				if (answer[1] - answer[0] > right - left - 1) {
					answer[0] = left + 1;
					answer[1] = right;
				}
				map.put(gems[left], map.get(gems[left]) - 1);
				if (map.get(gems[left]) == 0)
					map.remove(gems[left]);
				left++;
			} else {
				map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
				right++;
			}
		}
		return answer;
	}
}
