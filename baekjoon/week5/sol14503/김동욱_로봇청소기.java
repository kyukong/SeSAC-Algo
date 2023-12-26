package algo;

import java.util.Arrays;
import java.util.Scanner;

public class 김동욱_로봇청소기 {

	public static class Robot extends Point {
		int dir;

		public Robot() {
		}

		public Robot(int x, int y, int dir) {
			super(x, y);
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "[ " + this.x + "," + this.y + " dir:" + this.dir + " ]";
		}
	}

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
			return "[ " + this.x + "," + this.y + " ]";
		}
	}

	static int N;
	static int M;
	static int[][] board;
	static int answer;
	// 북 동 남 서
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static Robot robot;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		robot = new Robot(sc.nextInt(), sc.nextInt(), sc.nextInt());
		answer = 0;
		// 0 : 청소 X 칸 , 1: 벽 , 2: 청소된 칸
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		// simulation
		while (true) {
			if (robot.x == -1 && robot.y == -1)
				break;
			clean();
			boolean isSpace = checkSpaces();
			if (isSpace) {
				allCleaned();
			} else {
				robot.dir = turn(robot.dir);
				moveForward();
			}
		}
		System.out.println(answer);
	}

	public static void moveForward() {
		int nx = robot.x + dx[robot.dir];
		int ny = robot.y + dy[robot.dir];
		if (isValid(nx, ny)) {
			if (board[nx][ny] == 0) {
				robot.x = nx;
				robot.y = ny;
			}
		}

	}

	public static void allCleaned() {
		int nx = robot.x + dx[swap(robot.dir)];
		int ny = robot.y + dy[swap(robot.dir)];
		if (isValid(nx, ny)) {
			if (board[nx][ny] == 1) {
				robot.x = -1;
				robot.y = -1;
				return;
			}
			robot.x = nx;
			robot.y = ny;
		}

	}

	public static int swap(int dir) {
		int result = 0;
		switch (dir) {
		case 0:
			result = 2;
			break;
		case 1:
			result = 3;
			break;
		case 2:
			result = 0;
			break;
		case 3:
			result = 1;
			break;
		}
		return result;
	}

	public static int turn(int dir) {
		int result = 0;
		switch (dir) {
		case 0:
			result = 3;
			break;
		case 1:
			result = 0;
			break;
		case 2:
			result = 1;
			break;
		case 3:
			result = 2;
			break;
		}
		return result;
	}

	public static boolean checkSpaces() {
		for (int d = 0; d < 4; d++) {
			int nx = robot.x + dx[d];
			int ny = robot.y + dy[d];
			if (isValid(nx, ny)) {
				if (board[nx][ny] == 0)
					return false;
			}
		}
		return true;
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M)
			return false;

		return true;
	}

	public static void clean() {
		//System.out.println(" Robot 위치 : "+ robot);
		if (board[robot.x][robot.y] == 0) {
			board[robot.x][robot.y] = 2;
			answer++;
		}

	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
	}
}
