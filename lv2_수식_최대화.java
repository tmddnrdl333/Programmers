package ps_Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lv2_수식_최대화 {
	public static void main(String[] args) {
		String str = "100-200*300-500+20";
		System.out.println(solution(str));
	}

	static public long solution(String expression) {
		long answer = 0;
		//
		List<Long> nums = new ArrayList<>();
		List<Integer> ops = new ArrayList<>();
		int size = expression.length();
		int start = 0;
		int end = 0;
		for (int i = 0; i <= size; i++) {
			if (i == size || (int) expression.charAt(i) < '0') {
				String curNum = expression.substring(start, end);
				nums.add(Long.parseLong(curNum));
				start += curNum.length() + 1;
				if (i == size)
					break;
				char curOp = expression.charAt(i);
				int Op = 0;
				if (curOp == '-')
					Op = 1;
				else if (curOp == '*')
					Op = 2;
				ops.add(Op);
			}
			end++;
		}

		// 연산자 우선순위는 3! 으로 6가지 경우
		// +(0) -(1) *(2)
		// 012 021 102 120 201 210
		int[] opseq = new int[3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == j)
					continue;
				for (int k = 0; k < 3; k++) {
					if (i == k || j == k)
						continue;
					opseq[0] = i;
					opseq[1] = j;
					opseq[2] = k;

					// 행동

					// 임시 리스트
					List<Long> tNum = new ArrayList<>();
					List<Integer> tOp = new ArrayList<>();
					for (long c : nums)
						tNum.add(c);
					for (int c : ops)
						tOp.add(c);

					for (int seq = 0; seq < 3; seq++) {
						for (int o = 0; o < tOp.size(); o++) {
							if (tOp.get(o) == opseq[seq]) {
								long a = tNum.get(o);
								long b = tNum.get(o + 1);
								long res = 0;
								if (opseq[seq] == 0)
									res = a + b;
								else if (opseq[seq] == 1)
									res = a - b;
								else
									res = a * b;
								tNum.remove(o + 1);
								tNum.remove(o);
								tNum.add(o, res);
								tOp.remove(o);
								o--;
							}
						}
					}
					long tempAns = Math.abs(tNum.get(0));

					answer = tempAns > answer ? tempAns : answer;
					// 행동
				}
			}
		}

		//
		return answer;
	}
}
