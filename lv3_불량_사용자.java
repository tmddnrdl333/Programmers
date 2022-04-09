package ing;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class lv3_불량_사용자 {
	public static void main(String[] args) {
		String[] user_id = { "frodo", "fradi", "crodo", "abc123", "frodoc" };
		String[] banned_id = { "fr*d*", "*rodo", "******", "******" };
		System.out.println(solution(user_id, banned_id));
	}

	static public int solution(String[] user_id, String[] banned_id) {
		uSize = user_id.length;
		bSize = banned_id.length;
		ubM = new boolean[uSize][bSize];

		for (int bi = 0; bi < bSize; bi++) {
			int s = 0;
			int blen = banned_id[bi].length();
			String banr = banned_id[bi].replaceAll("\\*", ",*,");
			StringTokenizer st = new StringTokenizer(banr, ",");
			while (st.hasMoreTokens()) {
				String temp1 = st.nextToken();
				int t1len = temp1.length();

				for (int ui = 0; ui < uSize; ui++) {
					// user_id[i]에 대해...
					if (ubM[ui][bi])
						continue;
					String user = user_id[ui];
					if (user.length() != blen) {
						ubM[ui][bi] = true;
						continue;
					}

					String temp2 = user.substring(s, s + t1len);
					if (temp1.equals(temp2)) {
					} else if (temp1.equals("*") && temp2.length() == 1) {
					} else {
						ubM[ui][bi] = true;
					}
				}
				s += t1len;
			}
		}

//		for (int i = 0; i < uSize; i++) {
//			for (int j = 0; j < bSize; j++)
//				System.out.print(ubM[i][j] ? "/ " : "0 ");
//			System.out.println();
//		}
//		System.out.println();

		// false인 것에 대해서만 조합을 짜면 됨
		pick(0);

		int answer = rescnt;
		return answer;
	}

	static boolean[][] ubM;
	static int uSize, bSize;
	static Set<Integer> set = new HashSet<>();
	static int state = 0;
	static int rescnt;

	public static void pick(int cnt) {
		if (cnt == bSize) {
			if (!set.contains(state)) {
				rescnt++;
				set.add(state);
			}
			return;
		}
		for (int i = 0; i < uSize; i++) {
			if (!ubM[i][cnt] && (state & 1 << i) == 0) {
				state |= 1 << i;
				pick(cnt + 1);
				state &= ~(1 << i);
			}
		}
	}
}
