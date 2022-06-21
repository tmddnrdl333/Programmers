package study.day0616_p;

public class lv2_타겟_넘버 {

	public static void main(String[] args) {
		int[] numbers = { 4, 1, 2, 1 };
		int target = 4;
		System.out.println(solution(numbers, target));
	}

	static int len = 0, res = 0;

	public static int solution(int[] numbers, int target) {
		len = numbers.length;
		dfs(0, 0, numbers, target);
		int answer = res;
		return answer;
	}

	public static void dfs(int cnt, int val, int[] numbers, int target) {
		if (cnt == len) {
			if (val == target)
				res++;
			return;
		}
		dfs(cnt + 1, val + numbers[cnt], numbers, target);
		dfs(cnt + 1, val - numbers[cnt], numbers, target);
	}
}
