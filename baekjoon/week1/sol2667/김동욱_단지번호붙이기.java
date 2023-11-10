package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2667 {
	public static class Point {
		int x;
		int y;

		public Point() {
		}

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static boolean[][] visited;
	static int[][] board;
	static int answer;
	static StringBuilder sb;
	static ArrayList<Integer> al;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		visited = new boolean[N][N];
		al = new ArrayList<>();
		answer = 0;
		sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = temp.charAt(j) - '0';
			}
		} // end input

//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(board[i]));
//		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1 && !visited[i][j]) {
					bfs(new Point(i, j));
					answer++;
				}
			}
		}
		System.out.println(answer);
		Collections.sort(al);
		for(int a: al) {
			System.out.println(a);
		}
	}

	private static void bfs(Point point) {
		Queue<Point> queue = new LinkedList<>();
		visited[point.x][point.y] = true;
		queue.add(point);
		int count=1;
		while (!queue.isEmpty()) {
			Point tP = queue.poll();
			for(int d=0;d<4;d++) {
				int nx = tP.x+dx[d];
				int ny = tP.y+dy[d];
				if(isValid(nx,ny)) {
					if(board[nx][ny]==1) {
						visited[nx][ny] =true;
						queue.add(new Point(nx,ny));
						count++;
					}
				}
			}
		}
		al.add(count);
	}

	private static boolean isValid(int x, int y) {
		if(x<0 || x>=N || y<0 || y>=N)
			return false;
		if(visited[x][y])
			return false;
		return true;
	}
}
