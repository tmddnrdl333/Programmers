package study.day0721_p;

public class lv2_문자열_압축 {
	public static void main(String[] args) {

		System.out.println(solution("aaaaaaaaaaa"));
		System.out.println(solution("ababcdcdababcdcd"));
		System.out.println(solution("abcabcdede"));
		System.out.println(solution("abcabcabcabcdededededede"));
		System.out.println(solution("xababcdcdababcdcd"));
	}

	public static int solution(String s) {
		int len = s.length();
		int min = len; // 그냥 두면 길이
		for (int i = 1; i <= len / 2; i++) {
			int k = len / i;
			String prev = s.substring(0, i);
			int cnt = 0;
			int i_len = 0;
			for (int j = 1; j < k; j++) {
				String cur = s.substring(j * i, (j + 1) * i);
				if (prev.equals(cur)) {
					cnt++;
				} else {
					if (cnt == 0)
						i_len += i;
					else
						i_len += (i + (int) Math.log10(cnt + 1) + 1);
					cnt = 0;
					prev = cur;
				}
			}
			if (cnt == 0)
				i_len += i;
			else
				i_len += (i + (int) Math.log10(cnt + 1) + 1);
			i_len += (len - k * i);
			min = min > i_len ? i_len : min;
		}
		return min;
	}
}
