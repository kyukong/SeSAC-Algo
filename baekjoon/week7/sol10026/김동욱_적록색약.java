import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 김동욱_적록색약 {
	static int N;
	static char[][] board;
	static char[][] boardBlind;
	static boolean[][] visited;
	static boolean[][] visitedBlind;
	static int answer=0;
	static int answerBlind=0;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static boolean isValid(int x, int y,boolean[][] visited) {
		if(x<0 || x >=N || y<0 || y>=N)
			return false;
		if(visited[x][y])
			return false;
		return true;
	}
	
	public static int bfs(Point p,char[][] board, char color,boolean[][] visited) {
		Queue<Point> queue = new LinkedList<>();
		
		queue.add(p);
		visited[p.x][p.y]=true;
		while(!queue.isEmpty()) {
			Point tempPoll = queue.poll();
			for(int d=0;d<4;d++) {
				int nx = tempPoll.x+dx[d];
				int ny = tempPoll.y+dy[d];
				if(isValid(nx,ny,visited) && board[nx][ny]==color) {
					queue.add(new Point(nx,ny));
					
					visited[nx][ny]=true;
				}
			}
		}
		return 1;
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board= new char [N][N];
		boardBlind = new char[N][N];
		visited = new boolean[N][N];
		visitedBlind = new boolean[N][N];
		for(int i=0;i<N;i++) {
			String st = br.readLine();
			for(int j=0;j<N;j++) {
				board[i][j]= st.charAt(j);
			}
		} // end input
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				boardBlind[i][j]=board[i][j];
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(boardBlind[i][j]=='G')
					boardBlind[i][j]='R';
			}
		} // 보드 색맹화
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j] && board[i][j]=='R') {
					answer+=bfs(new Point(i,j),board,'R',visited);
				}
				else if(!visited[i][j] && board[i][j]=='G') {
					answer+=bfs(new Point(i,j),board,'G',visited);
				}
				else if(!visited[i][j] && board[i][j]=='B') {
					answer+=bfs(new Point(i,j),board,'B',visited);
				}
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visitedBlind[i][j] && boardBlind[i][j]=='R') {
					answerBlind+=bfs(new Point(i,j),boardBlind,'R',visitedBlind);
				}
				else if(!visitedBlind[i][j] && boardBlind[i][j]=='B') {
					answerBlind+=bfs(new Point(i,j),boardBlind,'B',visitedBlind);
				}
			}
		}
		System.out.println(answer +" "+ answerBlind);
	}

}