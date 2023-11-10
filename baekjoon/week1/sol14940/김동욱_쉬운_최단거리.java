import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김동욱_쉬운_최단거리 {
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
			return "[ " + this.x + " , " + this.y + " ] ";
		}
	}

	static int N, M;
	static int[][] board;
	static int[][] answerBoard;
	static boolean[][] visited;
	static Point start;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		answerBoard = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 2)
					start = new Point(i, j);

				board[i][j] = input;
			}
		} // end input
		bfs();
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(board[i][j]==1 && !visited[i][j]) {
					System.out.print(-1+" ");
					continue;
				}
				System.out.print(answerBoard[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		visited[start.x][start.y] = true;
		queue.add(start);
		int dist = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				Point tempPoint = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nx = tempPoint.x + dx[d];
					int ny = tempPoint.y + dy[d];
					if (isValid(nx, ny) && board[nx][ny]!=0) {
						answerBoard[nx][ny] = dist;
						visited[nx][ny] = true;
						queue.add(new Point(nx, ny));
					}
				}
			}
			dist++;
		}
	}

	private static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M)
			return false;
		if (visited[x][y])
			return false;
		return true;
	}
}
