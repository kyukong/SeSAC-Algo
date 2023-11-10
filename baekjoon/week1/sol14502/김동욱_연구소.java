import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 김동욱_연구소 {
	static int N;
	static int M;
	static int[][] board;
	static boolean[] visited;
	static boolean[][] infected;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	static int answer;
	static int safearea;
	static ArrayList<Point> arr;
	static Queue<Point> germ;

	public static class Point implements Cloneable {
		int x;
		int y;

		public Point() {
		}

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return super.clone();
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		board = new int[N][M];
		arr = new ArrayList<>();
		germ = new LinkedList<>();
		answer = -1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int temp = sc.nextInt();
				if (temp == 0) {
					arr.add(new Point(i, j));
				}
				if (temp == 2) {
					germ.add(new Point(i, j));
				}
				board[i][j] = temp;
			}
		} // end input
		visited = new boolean[arr.size()];
		
		comb(0, 0);
		System.out.println(answer);
	}
	
	public static int[][] copy(int[][] board) {
		int[][] copyBoard = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyBoard[i][j] = board[i][j];
			}
		}
		return copyBoard;
	}
	public static boolean isValid(int x, int y) {
		if(x<0 || x>=N || y<0 || y>=M)
			return false;
		if(infected[x][y])
			return false;
		return true;
	}
	public static void comb(int idx, int count) {
		if(count==3) {
			//Point[] walls = new Point[3];
			int[][] copyBoard= copy(board);
			safearea=0;
			Queue<Point> queue = new LinkedList<>();
			infected = new boolean[N][M];
			queue.addAll(germ);
			for(Point p : queue) {
				infected[p.x][p.y]= true;
			}
			int size=0;
			for(int i=0;i<arr.size();i++) {
				if(visited[i]) {
					//walls[size++]=arr.get(i);
					Point p = arr.get(i);
					copyBoard[p.x][p.y]=1;
				}
				
			}
			while(!queue.isEmpty()) {
				Point tempPoint = queue.poll();
				for(int d=0;d<4;d++) {
					int nx = tempPoint.x+dx[d];
					int ny = tempPoint.y+dy[d];
					if(isValid(nx,ny) && copyBoard[nx][ny]!=1&& copyBoard[nx][ny]!=2) {
						queue.add(new Point(nx,ny));
						copyBoard[nx][ny]=2;
						infected[nx][ny]=true;
						
					}
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(copyBoard[i][j]==0)
						safearea++;
				}
			}
			answer= Math.max(answer, safearea);
			return;
		}
		if(idx==arr.size()) {
			return;
		}
		visited[idx]=true;
		comb(idx+1,count+1);
		visited[idx]=false;
		comb(idx+1,count);
	}

}
