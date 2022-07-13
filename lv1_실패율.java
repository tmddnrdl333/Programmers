package study.day0707_p;

import java.util.Arrays;

public class lv1_실패율 {

	public static void main(String[] args) {
		int N = 5;
		int[] stages = { 2, 1, 2, 6, 2, 4, 3, 3 };
		System.out.println(Arrays.toString(solution(N, stages)));
	}

	static class Stage implements Comparable<Stage> {
		int num;
		double failRate;

		public Stage(int num, double failRate) {
			this.num = num;
			this.failRate = failRate;
		}

		@Override
		public int compareTo(Stage o) {
			return o.failRate == this.failRate ? this.num - o.num : o.failRate > this.failRate ? 1 : -1;
		}
	}

	public static int[] solution(int N, int[] stages) {
		Stage[] stageList = new Stage[N];
		int players = stages.length;
		for (int i = 0; i < N; i++) {
			int reached = 0, uncleared = 0;
			for (int j = 0; j < players; j++) {
				if (stages[j] == i + 1)
					uncleared++;
				if (stages[j] > i)
					reached++;
			}

			double failRate = 0;
			if (reached != 0)
				failRate = (double) uncleared / reached;
			stageList[i] = new Stage(i + 1, failRate);
		}

		Arrays.sort(stageList);

		int[] answer = new int[N];
		for (int i = 0; i < N; i++)
			answer[i] = stageList[i].num;
		return answer;
	}

}
