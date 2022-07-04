package study.day0630_p;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class lv2_주차_요금_계산 {
	public static void main(String[] args) {
		int[] fees = { 180, 5000, 10, 600 };
		String[] records = { "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN",
				"18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT" };
		System.out.println(Arrays.toString(solution(fees, records)));
	}

	static class Car implements Comparable<Car> {
		String num;
		int time;
		boolean IO;

		public Car(String num, int time, boolean iO) {
			this.num = num;
			this.time = time;
			IO = iO;
		}

		@Override
		public String toString() {
			return "Car [num=" + num + ", time=" + time + ", IO=" + IO + "]";
		}

		@Override
		public int compareTo(Car o) {
			return this.num.compareTo(o.num);
		}

	}

	public static int[] solution(int[] fees, String[] records) {

		Set<String> set = new HashSet<>();
		for (String record : records) {
			StringTokenizer st = new StringTokenizer(record);
			st.nextToken();
			String num = st.nextToken();
			set.add(num);
		}

		int len = set.size();
		Car[] cars = new Car[len];
		int i = 0;
		for (String num : set)
			cars[i++] = new Car(num, 0, false);
		Arrays.sort(cars);

		Map<String, Integer> map = new HashMap<>();
		i = 0;
		for (Car car : cars)
			map.put(car.num, i++);

		for (String record : records) {
			StringTokenizer st = new StringTokenizer(record, ":| ");
			int h = Integer.parseInt(st.nextToken()), min = Integer.parseInt(st.nextToken());
			int time = h * 60 + min;
			String num = st.nextToken();
			boolean IO = st.nextToken().equals("IN");
			cars[map.get(num)].IO = IO;
			cars[map.get(num)].time += time * (IO ? -1 : 1);
		}

		System.out.println(Arrays.toString(cars)); //

		for (Car car : cars)
			if (car.IO) {
				cars[map.get(car.num)].time += 23 * 60 + 59;
			}

		System.out.println(Arrays.toString(cars)); //

		int[] answer = new int[len];

		for (int c = 0; c < len; c++) {
			int cur = cars[c].time;
			if (cur <= fees[0]) {
				answer[c] = fees[1];
				continue;
			}
			answer[c] = fees[1] + (cur - fees[0] + fees[2] - 1) / fees[2] * fees[3];
		}

		return answer;
	}
}
