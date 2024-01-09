package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김동욱_게리맨더링 {
	static ArrayList<Integer>[] adjList;
	static int answer;
	static int[] people;
	static boolean[] visited;
	
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		people = new int[N];
		adjList = new ArrayList[N];
		answer = Integer.MAX_VALUE;
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<Integer>();

		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			while (st.hasMoreTokens()) {
				adjList[i].add(Integer.parseInt(st.nextToken()) - 1);
			}
		}
		// end input
		for (int i = 1; i <= N / 2; i++) {
			comb(0, 0, i);
		}
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else
			System.out.println(answer);
	}

	public static void comb(int idx, int count, int r) {
		if (count == r) {
			ArrayList<Integer> list1 = new ArrayList<>();
			ArrayList<Integer> list2 = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					list1.add(i);

				} else {
					list2.add(i);

				}
			}

			// 검증

			if (bfs(list1) && bfs(list2)) {
				int tot1 = 0;
				int tot2 = 0;
				for (int i = 0; i < N; i++) {
					if (visited[i])
						tot1 += people[i];
					else
						tot2 += people[i];
				}
				answer = Math.min(answer, Math.abs(tot1 - tot2));
			}
			return;
		}

		if (idx == N)
			return;

		visited[idx] = true;
		comb(idx + 1, count + 1, r);
		visited[idx] = false;
		comb(idx + 1, count, r);

	}

	public static boolean bfs(ArrayList<Integer> list) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N];
		queue.add(list.get(0));
		visited[list.get(0)] = true;
		int count = 1;
		while (!queue.isEmpty()) {
			int temp = queue.poll();
			for (int i : adjList[temp]) {
				if (list.contains(i) && !visited[i]) {
					queue.add(i);
					visited[i] = true;
					count++;
				}
			}
		}
		if (count == list.size()) {
			return true;
		}
		return false;
	}

}
