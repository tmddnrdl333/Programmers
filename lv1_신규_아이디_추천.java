package study.day0517_p;

import java.util.LinkedList;
import java.util.List;

public class lv1_신규_아이디_추천 {
	public static String solution(String new_id) {

		// 1
		String answer = new_id.toLowerCase();

		List<Character> list = new LinkedList<>();

		// 2, 3
		int len = answer.length();
		boolean comma = false;
		for (int i = 0; i < len; i++) {
			int cur = (int) answer.charAt(i);
			if (cur == 46) {
				if (!comma) {
					list.add((char) cur);
					comma = true;
				}
			} else if (cur >= 48 && cur <= 57 || cur >= 97 && cur <= 122 || cur == 95 || cur == 45) {
				comma = false;
				list.add((char) cur);
			}
		}

		// 4
		if (list.get(0) == '.')
			list.remove(0);
		if (!list.isEmpty() && list.get(list.size() - 1) == '.')
			list.remove(list.size() - 1);

		// 5
		if (list.isEmpty())
			list.add('a');

		// 6
		if (list.size() > 15) {
			List<Character> nl = new LinkedList<>();
			for (int i = 0; i < 15; i++)
				nl.add(list.get(i));
			list = nl;
			if (list.get(14) == '.')
				list.remove(14);
		}

		// 7
		while (list.size() <= 2)
			list.add(list.get(list.size() - 1));

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
		System.out.println(solution("z-+.^."));
		System.out.println(solution("=.="));
		System.out.println(solution("123_.def"));
		System.out.println(solution("abcdefghijklmn.p"));
	}
}
