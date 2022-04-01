package ps_Programmers;

public class lv1_숫자_문자열과_영단어 {
	public static void main(String[] args) {
		String s = "2three45six";
		System.out.println(solution(s));
	}

	public static int solution(String s) {
		String[] strs = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
		StringBuilder sb = new StringBuilder();
		int answer = 0;
		int len = s.length();
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (c >= '0' && c <= '9') {
				sb.append(c - '0');
			} else {
				String sub = s.substring(i, i + 5 > len ? len : i + 5);
				for (int j = 0; j < 10; j++)
					if (sub.contains(strs[j])) {
						sb.append(j);
						i += strs[j].length() - 1;
						break;
					}
			}
		}
		answer = Integer.parseInt(sb.toString());
		return answer;
	}
}