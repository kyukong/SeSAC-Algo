import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김동욱_토마토 {
	public static class Point {
		int x;
		int y;

		public Point() {
		}

		public Point(int x, int y) {
			this.x = x;
			this.y = y;

		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

	}

	static int M; // 가로
	static int N; // 세로
	static int answer;
	static int[][] board;
	static boolean[][] visited;
	static Queue<Point> queue;
	static int day;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new boolean[N][M];
		day = 1;
		answer = 0;
		queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}

		} // end input

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1) {
					queue.add(new Point(i, j));
					visited[i][j] = true;
				}
			}
		}
		bfs();
		// 검증
		//print();
		if(!isRiped()) {
			System.out.println(-1);
		}
		else {
			System.out.println(day-2);
		}
		

	}

	private static void bfs() {

		while (!queue.isEmpty()) {
			
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				Point tempPoint = queue.poll();

				for (int d = 0; d < 4; d++) {
					int nx = tempPoint.x + dx[d];
					int ny = tempPoint.y + dy[d];
					if (isValid(nx, ny)) {
						if (board[nx][ny] != -1) {
							board[nx][ny] = day;
							visited[nx][ny] = true;
							queue.add(new Point(nx, ny));
						}
					}
				}
			}
			day++;
		}

	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M)
			return false;
		if (visited[x][y])
			return false;
		return true;
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
	}

	public static boolean isRiped() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

}