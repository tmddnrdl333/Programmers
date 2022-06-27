package ing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class lv2_전력망을_둘로_나누기 {

	public static void main(String[] args) {
		int n = 7;
		int[][] wires = { { 1, 2 }, { 2, 7 }, { 3, 7 }, { 3, 4 }, { 4, 5 }, { 6, 7 } };
		System.out.println(solution(n, wires));
	}

	static int N;
	static Map<Integer, List<Integer>> map = new HashMap<>();

	public static int solution(int n, int[][] wires) {
		N = n;
		for (int[] connect : wires) {
			int a = connect[0], b = connect[1];
			List<Integer> alist = map.getOrDefault(a, new ArrayList<>());
			alist.add(b);
			map.put(a, alist);
			List<Integer> blist = map.getOrDefault(b, new ArrayList<>());
			blist.add(a);
			map.put(b, blist);
		}

		int minGap = Integer.MAX_VALUE;
		for (int[] disconnect : wires) {
			int group = BFS(disconnect);
			int curGap = Math.abs(2 * group - n);
			minGap = curGap < minGap ? curGap : minGap;
		}

		return minGap;
	}

	public static int BFS(int[] disconnect) {
		boolean[] vis = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		vis[1] = true;
		int cnt = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();
			cnt++;
			int sameIdx = cur == disconnect[0] ? 0 : cur == disconnect[1] ? 1 : -1;
			List<Integer> curList = map.get(cur);
			for (int child : curList) {
				if (sameIdx != -1 && child == disconnect[1 - sameIdx])
					continue;
				if (vis[child])
					continue;
				q.add(child);
				vis[child] = true;
			}
		}
		return cnt;
	}
}
