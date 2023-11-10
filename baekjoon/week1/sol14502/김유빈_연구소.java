package sol14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 김유빈_연구소 {

	private static int n;
	private static int m;
	private static int[][] map;
	private static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	private static int result = 0;
	private static int dangerousAreas = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];

		// 0. 입력받은 값으로 초기화
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int number = Integer.parseInt(st.nextToken());
				if (number != 0) {
					dangerousAreas++;
				}
				map[i][j] = number;
			}
		}
		dangerousAreas += 3;

		// 1. 벽 세우기 (랜덤 위치에 1 배치, 총 3개)
		dfs(3);

		System.out.println(result);
	}

	private static void dfs(int count) {
		if (count == 0) {
			bfs(dangerousAreas);
			return;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 0) {
					continue;
				}
				map[i][j] = 3;
				dfs(count - 1);
				map[i][j] = 0;
			}
		}
	}

	private static void bfs(int danger) {
		// 2. 바이러스 이동 (2 의 상하좌우에 2 저장)
		int[][] newMap = new int[n][m];
		for (int i = 0; i < n; i++) {
			newMap[i] = map[i].clone();
		}

		List<Node> queue = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 2) {
					queue.add(new Node(j, i));
				}
			}
		}

		// bfs
		while (!queue.isEmpty()) {
			Node node = queue.remove(queue.size() - 1);

			for (int[] direction : directions) {
				int nextX = node.x + direction[0];
				int nextY = node.y + direction[1];

				if (nextX < 0 || nextY < 0 || nextX >= m || nextY >= n) {
					continue;
				}
				if (newMap[nextY][nextX] != 0) {
					continue;
				}
				newMap[nextY][nextX] = 2;
				queue.add(0, new Node(nextX, nextY));
				danger++;
			}
		}
		result = Math.max(result, n * m - danger);
	}

	private static class Node {

		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
