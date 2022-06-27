package ing;

public class lv1_부족한_금액_계산하기 {
	public static void main(String[] args) {
		int price = 2500;
		int money = 1000000000;
		int count = 2500;
		System.out.println(solution(price, money, count));
	}

	public static long solution(int price, int money, int count) {
		long ans = (long) price * count * (count + 1) / 2 - money;
		return ans < 0 ? 0 : ans;
	}
}
