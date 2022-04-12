package ing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class lv4_호텔_방_배정_Map {
	public static void main(String[] args) {
		long k = 10;
		long[] room_number = { 1, 3, 1, 1, 1, 1, 2, 2 };
		System.out.println(Arrays.toString(solution(k, room_number)));
	}

	static Map<Long, Long> map = new HashMap<>();

	public static long[] solution(long k, long[] room_number) {
		int N = room_number.length;
		long[] answer = new long[N];

		for (int i = 0; i < N; i++) {
			answer[i] = find(room_number[i]);
//			System.out.println(room_number[i]);
//			System.out.println(map);
//			System.out.println(Arrays.toString(answer));
//			System.out.println("==============");
		}

		return answer;
	}

	public static long find(long rn) {
		if (map.containsKey(rn)) {
			long ret = find(map.get(rn));
			map.put(rn, ret);
			return ret;
		}
		map.put(rn, map.getOrDefault(rn, rn + 1));
		return rn;
	}
}
