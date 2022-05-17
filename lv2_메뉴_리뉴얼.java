package study.day0517_p;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lv2_메뉴_리뉴얼 {
	public static String[] solution(String[] orders, int[] course) {
		// 최종 정답 담을 리스트
		List<String> answer = new ArrayList<>();

		for (int cnt : course) { // 코스 최소개수별 정답에 넣기
			Map<String, Integer> map = new HashMap<>(); // 코스 최소개수별 중복되는 조합을 카운팅할 맵
			List<String> res = new ArrayList<>(); // 코스 최소개수별 가장 많이 주문된 조합만을 저장할 리스트
			int max = 2; // 2개 이상이어야 res에 저장

			for (String order : orders) { // 각 주문별로 검사

				// 각 주문을 우선 오름차순으로 정렬
				O = order.length();
				if (O < cnt)
					continue; // 이번 주문은 너무 짧아서 안봐도 되면 스킵
				char[] curOrder = new char[O];
				for (int i = 0; i < O; i++)
					curOrder[i] = order.charAt(i);
				Arrays.sort(curOrder);

				arr = new int[O]; // 각 주문에서 선택할 놈만 1, 나머지 0
				for (int i = 0; i < cnt; i++)
					arr[O - 1 - i] = 1;
				// next permutation
				do {
					StringBuilder sb = new StringBuilder(); // 이번 조합을 str으로 나타내기
					for (int i = 0; i < O; i++)
						if (arr[i] == 1)
							sb.append(curOrder[i]);
					String curStr = sb.toString();
					// map에 +해서 넣어주기
					int curCnt = map.getOrDefault(curStr, 0);
					map.put(curStr, ++curCnt);
					// 최대를 갱신하면 res를 비우고 넣기, 최대와 같다면 그냥 res에 넣기
					if (curCnt > max) {
						max = curCnt;
						res.clear();
						res.add(curStr);
					} else if (curCnt == max)
						res.add(curStr);
				} while (np());
			}
			answer.addAll(res);
		}

		int ansSize = answer.size();
		String[] answerStr = new String[ansSize];
		for (int i = 0; i < ansSize; i++)
			answerStr[i] = answer.get(i);
		Arrays.sort(answerStr);
		return answerStr;
	}

	static int O;
	static int[] arr;

	public static boolean np() {
		// 1. 교환위치 찾기
		int i = O - 1;
		while (i > 0 && arr[i - 1] >= arr[i])
			--i;

		if (i == 0)
			return false;

		// 2. 교환위치와 교환할 큰 값 위치 찾기
		int j = O - 1;
		while (arr[i - 1] >= arr[j])
			--j;

		// 3. 교환하기
		swap(i - 1, j);

		// 4. 꼭대기 뒤로 오름차순 정렬
		int k = O - 1;
		while (i < k)
			swap(i++, k--);

		return true;
	}

	public static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void main(String[] args) {
		String[] orders = { "XYZ", "XWY", "WXA" };
		int[] course = { 2, 3, 4 };
		System.out.println(Arrays.toString(solution(orders, course)));

	}
}
