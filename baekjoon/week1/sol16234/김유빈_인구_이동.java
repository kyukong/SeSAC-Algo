package sol16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 김유빈_인구_이동 {

	private static int n;
	private static int min;
	private static int max;
	private static int[][] map;
	private static int[][] visited;
	private static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	private static int color = 0;
	private static List<Area> areas;

	static class Area {

		int color;
		int sums;
		int count;

		public Area(int color) {
			this.color = color;
		}

		public void add(int number) {
			sums += number;
			count++;
		}

		public int numberOfPeople() {
			return sums / count;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		min = Integer.parseInt(st.nextToken());
		max = Integer.parseInt(st.nextToken());

		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int result = 0;
		while (true) {
			visited = new int[n][n];
			areas = new ArrayList<>();
			color = 0;

			// 국경선을 공유하기 위해 동일한 color 로 표시
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (visited[i][j] != 0) {
						continue;
					}
					areas.add(new Area(++color));
					dfs(j, i);
				}
			}

			if (areas.size() == n * n) {
				break;
			}

			// 인구 이동
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = areas.get(visited[i][j] - 1).numberOfPeople();
				}
			}

			result++;
		}
		System.out.println(result);
	}

	private static void dfs(int x, int y) {
		visited[y][x] = color;
		areas.get(color - 1).add(map[y][x]);

		for (int[] direction : directions) {
			int nextX = x + direction[0];
			int nextY = y + direction[1];

			if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= n) {
				continue;
			}
			if (visited[nextY][nextX] != 0) {
				continue;
			}

			int difference = Math.abs(map[y][x] - map[nextY][nextX]);
			if (min > difference || difference > max) {
				continue;
			}

			dfs(nextX, nextY);
		}
	}
}
