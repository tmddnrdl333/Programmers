package ps_Programmers;

public class lv1_키패드_누르기 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] { 1, 3, 4, 5 }, "right"));
	}

	public static String solution(int[] numbers, String hand) {
		StringBuilder answer = new StringBuilder();

		// 오른손잡이 여부
		boolean isRight = false;
		if (hand.charAt(0) == 'r')
			isRight = true;

		int[] l = { 0, 3 };
		int[] r = { 2, 3 };

		int len = numbers.length;
		for (int i = 0; i < len; i++) {
			switch (numbers[i]) {
			// left
			case 1:
				answer.append('L');
				l[0] = 0;
				l[1] = 0;
				break;
			case 4:
				answer.append('L');
				l[0] = 0;
				l[1] = 1;
				break;
			case 7:
				answer.append('L');
				l[0] = 0;
				l[1] = 2;
				break;
			// right
			case 3:
				answer.append('R');
				r[0] = 2;
				r[1] = 0;
				break;
			case 6:
				answer.append('R');
				r[0] = 2;
				r[1] = 1;
				break;
			case 9:
				answer.append('R');
				r[0] = 2;
				r[1] = 2;
				break;
			// 2 5 8 0
			default:
				int[] target = new int[2];
				target[0] = 1;
				target[1] = numbers[i] == 0 ? 3 : numbers[i] / 4;

				int ld = Math.abs(target[1] - l[1]) + Math.abs(1 - l[0]);
				int rd = Math.abs(target[1] - r[1]) + Math.abs(1 - r[0]);

				boolean useRight = true;
				if (ld == rd) {
					// 손잡이에 따라 다름
					if (isRight) {
					} else {
						useRight = false;
					}
				} else if (ld < rd) {
					useRight = false;
				}

				if (useRight) {
					answer.append("R");
					r[0] = 1;
					r[1] = target[1];
				} else {
					answer.append("L");
					l[0] = 1;
					l[1] = target[1];
				}
				break;
			}
		}

		return answer.toString();
	}
}
