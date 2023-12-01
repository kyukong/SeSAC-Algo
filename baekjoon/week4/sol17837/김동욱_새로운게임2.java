package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김동욱_새로운게임2 {
	public static class Point {
		int num;
		int x;
		int y;
		int dir;

		public Point() {
		}

		public Point(int num, int x, int y, int dir) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Point [num=" + num + ", x=" + x + ", y=" + y + ", dir=" + dir + "]";
		}

	}

	static Point[] curPosBoard;
	static Deque[][] board;
	static int[][] colors;
	static int N, K;
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int[] dy = { 0, 1, -1, 0, 0 };
	static int answer = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		curPosBoard = new Point[K + 1];
		board = new Deque[N][N];
		colors = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				colors[i][j] = Integer.parseInt(st.nextToken());
				board[i][j] = new ArrayDeque<Integer>();
			}
		}

		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			Point p = new Point(i, row, col, dir);
			curPosBoard[i] = p;
			board[p.x][p.y].addLast(i);
		}
		// end input
		// moveChess(curPosBoard[1]);
		for (int t = 1; t <=1000; t++) {
			System.out.println("Turn :"+ t +"=========================");
			for (int i = 1; i <= K; i++) {
				moveChess(curPosBoard[i]);
				System.out.println("Print : =========================");
				print();
				//System.out.println(Arrays.toString(curPosBoard));
				if (isEnd()) {
				 System.out.println(t);
					
					System.exit(1);
				}

			}
		}
		System.out.println(-1);
//		System.out.println("Print : =========================");
//		print();
//		System.out.println(Arrays.toString(curPosBoard));
	}

	public static boolean isEnd() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j].size() >= 4)
					return true;
			}
		}
		return false;
	}

	public static void moveChess(Point p) {
		// 움직이려는 세트 구성 밑에서부터 구성
		Point nowP = new Point(p.num, p.x, p.y, p.dir);
		Deque<Integer> mQueue = new ArrayDeque<>();
		Deque<Integer> stayQueue = new ArrayDeque<>();
		boolean flag = false;
		Iterator iterator = board[p.x][p.y].iterator();

		while (iterator.hasNext()) {
			int curPoint = (int) iterator.next();
			if (flag) {
				mQueue.addLast(curPoint);
				continue;
			}
			if (p.num == curPoint) {
				mQueue.addLast(curPoint);
				flag = true;
				continue;
			}
			stayQueue.add(curPoint);

		}
		//System.out.println("mQueue :"+mQueue);
		//System.out.println("stayQueue :"+stayQueue);
		int dir = nowP.dir;
		System.out.println(nowP);
		int nx = nowP.x + dx[dir];
		int ny = nowP.y + dy[dir];
		// 벗어나거나 파란색인 경우
		if ((nx < 0 || nx >= N || ny < 0 || ny >= N) || (colors[nx][ny] == 2)) {
			nowP.dir = convert(nowP.dir);
			
			int nextX = nowP.x + dx[nowP.dir];
			int nextY = nowP.y + dy[nowP.dir];
			if(nextX<0 || nextX>=N || nextY<0 || nextY>=N) {
				curPosBoard[p.num] = nowP;
				return;
			}
			if (colors[nextX][nextY] != 2) {
				// 흰색, 빨강 또 따져
				if (colors[nextX][nextY] == 1) {
					System.out.println("파랑-> 빨강 하");
					while (!mQueue.isEmpty()) {
						board[nextX][nextY].addLast(mQueue.pollLast());
					}
					swapQueue(nowP.x,nowP.y, stayQueue);
					nowP.x = nextX;
					nowP.y = nextY;
					//board[nowP.x][nowP.x] = stayQueue;
				} else if (colors[nextX][nextY] == 0) {
					while (!mQueue.isEmpty()) {
						board[nextX][nextY].addLast(mQueue.pollFirst());
					}
					swapQueue(nowP.x,nowP.y, stayQueue);
					nowP.x = nextX;
					nowP.y = nextY;
					//board[nowP.x][nowP.x] = stayQueue;
				}
			}
			curPosBoard[p.num] = nowP;
			return;
		}
		// 흰색인 경우
		else if (colors[nx][ny] == 0) {
			Iterator iter = mQueue.iterator();
			while (iter.hasNext()) {
				int moveNum = (int) iter.next();
				curPosBoard[moveNum] = new Point(moveNum, nx, ny, curPosBoard[moveNum].dir);
			}
			while (!mQueue.isEmpty()) {
				board[nx][ny].addLast(mQueue.pollFirst());
			}
			swapQueue(nowP.x,nowP.y, stayQueue);
			nowP.x = nx;
			nowP.y = ny;
			//board[nowP.x][nowP.x] = stayQueue;
			curPosBoard[p.num] = nowP;
			return;
		} else if (colors[nx][ny] == 1) {
			Iterator iter = mQueue.iterator();
			while (iter.hasNext()) {
				int moveNum = (int) iter.next();
				curPosBoard[moveNum] = new Point(moveNum, nx, ny, curPosBoard[moveNum].dir);
			}
			while (!mQueue.isEmpty()) {
				board[nx][ny].addLast(mQueue.pollLast());
			}
			swapQueue(nowP.x,nowP.y, stayQueue);
			nowP.x = nx;
			nowP.y = ny;
			curPosBoard[p.num] = nowP;
			return;
		}

	}
	public static void swapQueue(int x, int y, Deque<Integer> stayQueue){
		while(!board[x][y].isEmpty()) {
			board[x][y].poll();
		}
		while(!stayQueue.isEmpty()) {
			board[x][y].addLast(stayQueue.pollFirst());
		}
	}
	public static int convert(int d) {
		int res = -1;
		switch (d) {
		case 1:
			res = 2;
			break;
		case 2:
			res = 1;
			break;
		case 3:
			res = 4;
			break;
		case 4:
			res = 3;
			break;
		}
		return res;
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
}
