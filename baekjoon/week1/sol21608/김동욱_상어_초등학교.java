package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_21608 {

	public static class Point implements Comparable<Point> {
		int x;
		int y;
		int count;
		int emptyCount;
		public Point() {
		}

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Point(int x, int y,int count, int emptyCount) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.emptyCount= emptyCount;
		}
		
		@Override
		public int compareTo(Point o) {
			if(o.count == this.count) {
				if(o.emptyCount == this.emptyCount) {
					if (o.x == this.x)
						return this.y - o.y;
					return this.x - o.x;
				}
				return this.emptyCount - o.emptyCount;
			}
			return this.count - o.count;
		}

		@Override
		public String toString() {
			return "[ " + this.x + "," + this.y + " ]";
		}

	}

	static int N;
	static int[] students;
	static int[][] board;
	static int[][] likes;
	static int answer;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] likeBoard;
	static int[][] emptyBoard;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];

		students = new int[N * N];
		likes = new int[N * N + 1][4];
		answer = 0;
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int stNum = Integer.parseInt(st.nextToken());
			students[i] = stNum;
			likes[stNum][0] = Integer.parseInt(st.nextToken());
			likes[stNum][1] = Integer.parseInt(st.nextToken());
			likes[stNum][2] = Integer.parseInt(st.nextToken());
			likes[stNum][3] = Integer.parseInt(st.nextToken());
		} // end input
		PriorityQueue<Point> pq = new PriorityQueue<>();

		// 반복문 students
		// 비어있는 칸 탐색
		// 첫번째 칸 고정
		if (N % 2 == 0) {
			board[1][1] = students[0];
		} else {
			board[N / 2][N / 2] = students[0];
		}
		for (int i = 1; i < N * N; i++) {
			// System.out.println(students[i] + "번째 차례");
			likeBoard = new int[N][N];
			emptyBoard = new int[N][N];
			searchLike(students[i]);
			// print(likeBoard);
			pq = searchBiggestEmpty();
			// print(emptyBoard);
			placeStudent(pq, students[i]);
			// print(board);
		}
		countPoint();
		System.out.println(answer);
	}

	private static void placeStudent(PriorityQueue<Point> pq, int stNum) {
		Point p = pq.poll();
		board[p.x][p.y] = stNum;

	}

	public static void print(int[][] b) {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(b[i]));
		}
		System.out.println();
	}

	private static void searchLike(int curStudent) {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 0) {
					int count = 0;
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if (isValid(nx, ny)) {
							for (int k = 0; k < 4; k++) {
								if (board[nx][ny] == likes[curStudent][k]) {

									count++;
								}
							}
						}
					}

					likeBoard[i][j] = count;
				}
			}
		}

	}

	public static int returnBiggestValue(int[][] b) {
		int m = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (b[i][j] > m) {
					m = b[i][j];
				}
			}
		}
		return m;
	}

	public static ArrayList<Point> searchBigValue(int max, int[][] b) {
		int m = 0;
		ArrayList<Point> p = new ArrayList<Point>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (b[i][j] == max) {
					p.add(new Point(i, j));
				}
			}
		}
		return p;
	}

	public static PriorityQueue<Point> searchBiggestEmpty() {

		int m = returnBiggestValue(likeBoard);
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		ArrayList<Point> p = searchBigValue(m, likeBoard);
		if (p.size() <= 1) {
			pq.add(p.get(0));
			return pq;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (likeBoard[i][j] == m) {
					int count = 0;
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if (isValid(nx, ny) && board[nx][ny] == 0) {
							count++;
						}
					}
					emptyBoard[i][j] = count;
				}
			}
		}
		int s = returnBiggestValue(emptyBoard);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (emptyBoard[i][j] == s) {
					pq.add(new Point(i, j));
				}
			}
		}

		return pq;
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		return true;
	}

	public static void countPoint() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int studentNum = board[i][j];
				int count = 0;
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (isValid(nx, ny)) {
						for (int k = 0; k < 4; k++) {
							if (board[nx][ny] == likes[studentNum][k]) {
								count++;
							}
						}
					}
				}
				answer += convertPoint(count);
			}
		}
	}

	public static int convertPoint(int count) {
		int r = count;
		switch (count) {
		case 2:
			r = 10;
			break;
		case 3:
			r = 100;
			break;
		case 4:
			r = 1000;
			break;
		}
		return r;
	}

}
