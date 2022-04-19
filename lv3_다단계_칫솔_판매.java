package study.day0414_p;

import java.util.Arrays;

public class lv3_다단계_칫솔_판매 {
	public static void main(String[] args) {
		String[] enroll = { "john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young" };
		String[] referral = { "-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward" };
		String[] seller = { "young", "john", "tod", "emily", "mary" };
		int[] amount = { 12, 4, 2, 5, 10 };
		System.out.println(Arrays.toString(solution(enroll, referral, seller, amount)));
	}

	static int[] er;
	static int[] s;
	static int[] answer;

	public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

		int eSize = enroll.length;
		int sSize = seller.length;
		answer = new int[eSize];

		// 누가 누구에게 줘야 하는지.
		er = new int[eSize];
		outer: for (int i = 0; i < eSize; i++) {
			if (referral[i].equals("-")) {
				er[i] = -1; // center 밑
				continue;
			}
			for (int j = 0; j < i; j++) {
				if (referral[i].equals(enroll[j])) {
					er[i] = j;
					continue outer;
				}
			}
		}

		// 파는 사람에 대한 정보 정리
		s = new int[sSize];
		outer: for (int i = 0; i < sSize; i++) {
			for (int j = 0; j < eSize; j++) {
				if (seller[i].equals(enroll[j])) {
					s[i] = j;
					continue outer;
				}
			}
		}

		for (int i = 0; i < sSize; i++) {
			dfs(amount[i] * 100, s[i]);
		}

		return answer;
	}

	public static void dfs(int money, int i) {
		if (money / 10 == 0) {
			answer[i] += money;
			return;
		}
		answer[i] += money - money / 10;
		if (er[i] == -1)
			return;
		dfs(money / 10, er[i]);
	}

}
