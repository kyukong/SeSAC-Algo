import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김동욱_인구이동 {
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
			return "[ " + this.x + " , " + this.y + " ]";
		}
	}

	static int N;
	static int L, R;
	static int[][] board;
	static int[][] borders;
	
	static boolean[][] moveVisit;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		board = new int[N][N];
		borders = new int[N][N];

		answer = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// end input
		// 국경선 열기
			while(true) {
			openBorder();
			
			if(isOver()) break;
			// 연합을 이루는 각 칸 인구 수 이동
			moveVisit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (borders[i][j] != 0 && !moveVisit[i][j]) {
						move(i, j);
					}
				}
			}
			for(int i=0;i<N;i++) {
				Arrays.toString(moveVisit[i]);
			}
			
			// 연합 해체 후 국경선 닫기
			borders = new int[N][N];
			answer++;
			}
			System.out.println(answer);
	}

	private static void openBorder() {
		boolean[][] visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j]) {
					visited[i][j]=true;
					for(int d=0;d<4;d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if(isValid(nx,ny,visited)) {
							int abs = Math.abs(board[i][j] - board[nx][ny]);

							if (abs >= L && abs <= R) {

								Point pointDir = returnPossibleDir(d);
								borders[i][j] += pointDir.x;
								borders[nx][ny] += pointDir.y;
								
							}
						}
					}
				}
			}
		}
		

	}

	private static void move(int i, int j) {

		Queue<Point> queue = new LinkedList<>();
		ArrayList<Point> company = new ArrayList<>();
		queue.add(new Point(i, j));
		company.add(new Point(i,j));
		moveVisit[i][j] = true;
		int sum = board[i][j];
		int countries = 1;
		while (!queue.isEmpty()) {
			
			Point tempPoint = queue.poll();
			
			String dir = toBinary(Integer.toBinaryString(borders[tempPoint.x][tempPoint.y]));
			
			for (int d = 0; d < 4; d++) {
				int nx = tempPoint.x + dx[d];
				int ny = tempPoint.y + dy[d];
				if (isValid(nx, ny, moveVisit) && dir.charAt(d) == '1') {
					sum += board[nx][ny];
					countries++;
					moveVisit[nx][ny] = true;
					Point p = new Point(nx, ny);
					queue.add(p);
					company.add(p);
				}
			}
		}
		
		int divide = sum / countries;
		for(Point p : company) {
			board[p.x][p.y]= divide;
		}
	}
	public static String toBinary(String t) {
		char[] bin = new char[4];
		int ind =3;
		Arrays.fill(bin, '0');
		for(int i=t.length()-1;i>=0;i--) {
			bin[ind--]= t.charAt(i);
		}
		String temp="";
		for(char c: bin) {
			temp+=c;
		}
		return temp;
	}
	public static boolean isOver() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += borders[i][j];
			}
		}
		
		if (sum == 0)
			return true;
		 
		return false;
	}

	public static Point returnPossibleDir(int d) {
		Point n = new Point();
		switch (d) {
		case 0:
			n.x = 8;
			n.y = 4;
			break;
		case 1:
			n.x = 4;
			n.y = 8;
			break;
		case 2:
			n.x = 2;
			n.y = 1;
			break;
		case 3:
			n.x = 1;
			n.y = 2;
			break;
		}
		return n;
	}

	private static boolean isValid(int x, int y, boolean[][] visited) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		if (visited[x][y])
			return false;
		return true;
	}

}
