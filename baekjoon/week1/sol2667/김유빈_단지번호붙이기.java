package sol2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 김유빈_단지번호붙이기 {

	private static int n;
	private static int[][] map;
	private static int[][] visited;
	private static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	private static int count;
	private static List<Integer> results = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		visited = new int[n][n];

		String row;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			row = st.nextToken();
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(String.valueOf(row.charAt(j)));
			}
		}

		// dfs
		int color = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j] == 1 || map[i][j] == 0) {
					continue;
				}
				count = 0;
				dfs(j, i, ++color);
				results.add(count);
			}
		}

		// sort
		Collections.sort(results);

		// print
		System.out.println(color);
		for (int result : results) {
			System.out.println(result);
		}
	}

	private static void dfs(int x, int y, int color) {
		if (x < 0 || y < 0 || x >= n || y >= n) {
			return;
		}
		if (map[y][x] == 0 || visited[y][x] == 1) {
			return;
		}

		map[y][x] = color;
		visited[y][x] = 1;
		count++;

		for (int[] direction : directions) {
			dfs(x + direction[0], y + direction[1], color);
		}
	}
}
