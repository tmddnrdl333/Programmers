public class lv1_성격유형검사 {

	public static void main(String[] args) {
		String[] survey = { "AN", "CF", "MJ", "RT", "NA" };
		int[] choices = { 5, 3, 2, 7, 5 };
		System.out.println(solution(survey, choices));
	}

	public static String solution(String[] survey, int[] choices) {
		int[] type = new int[8]; // RT CF JM AN
		int length = choices.length;
		for (int i = 0; i < length; i++) {
			int point = choices[i] - 4;
			if (point == 0)
				continue;
			char selected = '0';
			int index = 0;
			if (point < 0)
				selected = survey[i].charAt(0);
			else
				selected = survey[i].charAt(1);
			switch (selected) {
			case 'R':
				index = 0;
				break;
			case 'T':
				index = 1;
				break;
			case 'C':
				index = 2;
				break;
			case 'F':
				index = 3;
				break;
			case 'J':
				index = 4;
				break;
			case 'M':
				index = 5;
				break;
			case 'A':
				index = 6;
				break;
			case 'N':
				index = 7;
				break;
			}
			type[index] += Math.abs(point);
		}

		StringBuilder answer = new StringBuilder();
		if (type[0] >= type[1])
			answer.append("R");
		else
			answer.append("T");
		if (type[2] >= type[3])
			answer.append("C");
		else
			answer.append("F");
		if (type[4] >= type[5])
			answer.append("J");
		else
			answer.append("M");
		if (type[6] >= type[7])
			answer.append("A");
		else
			answer.append("N");
		return answer.toString();

	}
}
