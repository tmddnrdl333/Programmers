package study.day0707_p;

public class lv2_멀쩡한_사각형 {

	public static void main(String[] args) {
		int w = 8, h = 12;
		System.out.println(solution(w, h));
	}

	public static long solution(int w, int h) {
		long answer = (long) w * h;

		long a = w > h ? w : h; // 큰
		long b = w + h - a; // 작은

		long minus = 0;
		for (int i = 0; i < a; i++) {
			long temp = i * b / a;
			minus++;
			if ((i + 1) * b % a == 0) {
				answer -= minus * a / (i + 1);
				break;
			}

			if ((i + 1) * b / a > temp) {
				minus++;
			}
		}
		return answer;
	}
}
