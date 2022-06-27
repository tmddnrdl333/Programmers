package ing;

public class lv1_최소직사각형 {

	public static void main(String[] args) {
		int[][] sizes = { { 14, 4 }, { 19, 6 }, { 6, 16 }, { 18, 7 }, { 7, 11 } };
		System.out.println(solution(sizes));
	}

	public static int solution(int[][] sizes) {

		int longest1 = 0, longest2 = 0;
		for (int[] arr : sizes) {
			int a = Math.max(arr[0], arr[1]); // 큰
			int b = arr[0] + arr[1] - a; // 작은
			longest1 = longest1 < a ? a : longest1;
			longest2 = longest2 < b ? b : longest2;
		}

		int answer = longest1 * longest2;
		return answer;
	}
}
