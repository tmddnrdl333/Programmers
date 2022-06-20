package study.day0616_p;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class lv3_베스트앨범 {

	public static void main(String[] args) {
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };
		System.out.println(Arrays.toString(solution(genres, plays)));
	}

	// 곡 번호와 재생 횟수
	// (pq에 들어가면) 재생횟수 높은게 우선, 같을 시 곡 번호 낮은게 우선.
	static class IdxAndCnt implements Comparable<IdxAndCnt> {
		int idx, cnt;

		public IdxAndCnt(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(IdxAndCnt o) {
			return this.cnt == o.cnt ? this.idx - o.idx : o.cnt - this.cnt;
		}
	}

	// 같은 장르의 곡들에 대한 정보
	// 총 재생 횟수, (곡 번호와 재생 횟수) 목록(pq)
	// (pq에 들어가면) 총 재생횟수 높은 게 우선
	static class Songs implements Comparable<Songs> {
		int totalCnt;
		PriorityQueue<IdxAndCnt> playListPQ;

		public Songs(int totalCnt, int idx, int cnt) {
			this.totalCnt = totalCnt;
			this.playListPQ = new PriorityQueue<>();
			this.playListPQ.add(new IdxAndCnt(idx, cnt));
		}

		@Override
		public int compareTo(Songs o) {
			return o.totalCnt - this.totalCnt;
		}
	}

	public static int[] solution(String[] genres, int[] plays) {
		// 장르별로 분류
		Map<String, Songs> genreMap = new HashMap<>();
		int len = plays.length;
		for (int i = 0; i < len; i++) {
			String curGenre = genres[i]; // 이번 장르
			int curPlay = plays[i]; // 이번 재생횟수

			Songs curAlbum = genreMap.get(curGenre); // 이 장르 정보가 이미 있는지

			if (curAlbum == null) { // 없었다면 새로 만들기
				genreMap.put(curGenre, new Songs(curPlay, i, curPlay));
			} else { // 있었다면 총재생추가, 목록에도 추가
				curAlbum.totalCnt += curPlay;
				curAlbum.playListPQ.add(new IdxAndCnt(i, curPlay));
				genreMap.put(curGenre, curAlbum);
			}
		}

		PriorityQueue<Songs> genrePQ = new PriorityQueue<>();
		for (String genre : genreMap.keySet())
			genrePQ.add(genreMap.get(genre));

		List<Integer> bestAlbum = new ArrayList<>();
		while (!genrePQ.isEmpty()) {
			Songs cur = genrePQ.poll();
			int i = 0;
			while (!cur.playListPQ.isEmpty() && i != 2) {
				bestAlbum.add(cur.playListPQ.poll().idx);
				i++;
			}

		}

		int size = bestAlbum.size();
		int[] answer = new int[size];
		for (int i = 0; i < size; i++)
			answer[i] = bestAlbum.get(i);
		return answer;
	}
}
