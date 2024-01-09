package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 김동욱_가장가까운세사람의심리적거리 {
	static StringBuilder sb;
	static boolean[] visited;
	static String[] people;
	static int T;
	static int N;
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			answer = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			people = new String[N];
			visited = new boolean[N];
			for (int i = 0; i < N; i++) {
				people[i] = st.nextToken();
			}
			if(N>32) {
				sb.append(0+"\n");
				continue;
			} 
			comb(0, 0);
			sb.append(answer+"\n");
		}
		System.out.println(sb);
	}

	public static void comb(int idx, int count) {
		if (count == 3) {
			ArrayList<String> result = new ArrayList<>();
			for(int i=0;i<N;i++) {
				if(visited[i]) {
					result.add(people[i]);
				}
			}
			answer = Math.min(answer, dist(result.get(0),result.get(1),result.get(2)));
			if(answer==0) {
				return;
			}
			return;
		}
		if (idx == N)
			return;

		visited[idx] = true;
		comb(idx + 1, count + 1);
		visited[idx] = false;
		comb(idx + 1, count);
	}

	public static int dist(String str1, String str2, String str3) {
		int sum = 0;
		sum += getDiff(str1, str2);
		sum += getDiff(str1, str3);
		sum += getDiff(str2, str3);
		return sum;
	}

	public static int getDiff(String str1, String str2) {
		int diff = 0;
		for (int i = 0; i < 4; i++) {
			if (str1.charAt(i) != str2.charAt(i))
				diff++;
		}
		return diff;
	}
}

