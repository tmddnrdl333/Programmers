package Programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class lv3_디스크_컨트롤러 {
	public static void main(String[] args) {
		int[][] jobs = { { 0, 3 }, { 1, 9 }, { 2, 6 } };
		System.out.println(solution(jobs));
	}

	static class Job {
		int request; // 요청 시점
		int cost; // 소요 시간

		public Job(int request, int cost) {
			this.request = request;
			this.cost = cost;
		}
	}

	public static int solution(int[][] jobs) {
		// Job 클래스로 배열 생성
		int jobCnt = jobs.length;
		Job[] jobArr = new Job[jobCnt];

		// jobArr에 입력되는 작업 넣기
		for (int i = 0; i < jobCnt; i++)
			jobArr[i] = new Job(jobs[i][0], jobs[i][1]);

		// 요청 시점이 이른 순으로 정렬
		Arrays.sort(jobArr, (o1, o2) -> (o1.request - o2.request));

		// 소요 시간이 짧은 순으로 정렬
		PriorityQueue<Job> pq = new PriorityQueue<>((o1, o2) -> (o1.cost - o2.cost));

		int t = jobArr[0].request; // 현재 시간
		int j = 0; // jobArr의 인덱스 (다음으로 할당할 가능성이 있는 시간의 작업을 가리키는 인덱스)
		int ing = 0; // 현재 진행 중인 작업의 남은 시간
		int sum = 0; // 총 합 (나누기 작업 개수하면 결과)

		while (true) {

			// j가 끝까지 가지 않았고 && 다음으로 할 작업이 시간이 되었다면,
			// pq에 우선순위로(소요 시간 짧은 순으로) 정렬되게 넣어준다.
			while (j != jobCnt && jobArr[j].request == t)
				pq.add(jobArr[j++]);

			// 현재 진행 중 작업이 없고, pq에 다음으로 할당할 작업이 있다면,
			// pq에서 꺼내서 작업을 시작해준다.
			if (ing == 0 && !pq.isEmpty()) {
				Job n_job = pq.poll();
				ing = n_job.cost;
				sum += (t + ing - n_job.request);
			}

			// 탈출 조건
			// 요청할 작업이 없으며, 진행 중인 작업도 없으면 탈출
			if (j == jobCnt && ing == 0)
				break;

			// 다음으로...
			// 시간++, ing가 있다면 --
			t++;
			if (ing != 0)
				ing--;
		}

//		System.out.println(sum);

		int answer = sum / jobCnt;
		return answer;
	}
}
