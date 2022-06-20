package study.day0616_p;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class lv2_타켓_넘버 {

	public static void main(String[] args) {
		String[] operations = { "I 16", "D 1" };
		System.out.println(Arrays.toString(solution(operations)));
	}

	public static int[] solution(String[] operations) {
		List<Integer> list = new ArrayList<>();

		for (String op : operations) {
			int type = op.charAt(0);
			int num = Integer.parseInt(op.substring(2));
			if (type == 'I') {
				list.add(num);
				Collections.sort(list);
			} else if (type == 'D') {
				if (num == 1) {
					if (list.size() != 0)
						list.remove(list.size() - 1);
				} else {
					if (list.size() != 0)
						list.remove(0);
				}
			}
		}

		int a = 0, b = 0;
		if (list.size() != 0) {
			a = list.get(list.size() - 1);
			b = list.get(0);
		}
		int[] answer = { a, b };
		return answer;
	}
}
