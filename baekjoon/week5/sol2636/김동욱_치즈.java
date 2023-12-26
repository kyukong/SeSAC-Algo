package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김동욱_치즈 {
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

	static int N;
	static int M;
	static int[][] board;
	static boolean[][] visited;
	static int[][] check;
	static int[][] checkBoard;
	static int preCheeze;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new boolean[N][M];
		checkBoard = new int[N][M];
		preCheeze = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// iteration 시작
		int time = 0;
		while (true) {
			
			int temp = countCheeze();
			if(temp==0) {
				break;
			}
			preCheeze = countCheeze();
			visited = new boolean[N][M];
			checkBoard = new int[N][M];
			bfs(new Point(0, 0));
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (checkBoard[i][j] == 1) {
						board[i][j] = 0;
					}
				}
			}
			time++;
			//print(board);
		}
System.out.println(time);
System.out.println(preCheeze);
	}

	public static int countCheeze() {
		int a = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1) {
					a++;
				}
			}
		}
		return a;
	}

	public static void bfs(Point p) {

		Queue<Point> queue = new LinkedList<>();
		queue.add(p);
		visited[p.x][p.y] = true;

		while (!queue.isEmpty()) {
			Point tempPoint = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nx = tempPoint.x + dx[d];
				int ny = tempPoint.y + dy[d];
				if (isValid(nx, ny)) {
					if (board[nx][ny] == 0) {
						Point p1 = new Point(nx, ny);
						queue.add(p1);
						visited[nx][ny] = true;
					} else if (board[nx][ny] == 1) { // 공기와 맣닿은 치즈인 경우
						checkBoard[nx][ny] = 1;
					}
				}
			}
		}
	}

	private static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M)
			return false;
		if (visited[x][y])
			return false;
		return true;
	}

	public static void print(int[][] board) {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
	}
}
