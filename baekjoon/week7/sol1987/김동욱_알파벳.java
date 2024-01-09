import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김동욱_알파벳 {
	static int R;
	static int C;
	static char[][] board;
	static boolean[] alphabet;
	static boolean[][] visited;
	static int[] dx= {0,0,1,-1};
	static int[] dy= {1,-1,0,0};
	static boolean flag = false;
	static int max=-1;
	public static boolean isValid(int x, int y) {
		if(x<0 || x>=R || y<0 || y>=C)
			return false;
	
		return true;
	}
	
	public static void dfs(int x, int y, int count) {
		if(!isValid(x,y) || alphabet[Character.getNumericValue(board[x][y])-10]) {
			//System.out.println("End "+count);
			max = Math.max(count,max);
			return;
		}
		else {
			boolean flag = false;
			alphabet[Character.getNumericValue(board[x][y])-10]=true;// alphabet 방문 처리
			
		for(int d=0;d<4;d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			
			if(isValid(nx,ny) && !alphabet[Character.getNumericValue(board[nx][ny])-10]) {
		
			dfs(nx,ny,count+1);
			alphabet[Character.getNumericValue(board[nx][ny])-10]=false;
			
			}
		}
		if(!flag) {
			max = Math.max(count,max);
			return;
		}
		}
		
		return;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String temp="";
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		
		alphabet = new boolean[26];
		flag= false;
		for(int i=0;i<R;i++) {
			temp= br.readLine();
			for(int j=0;j<C;j++) {
				board[i][j] = temp.charAt(j);
			}
		}// end input
		dfs(0,0,1);

		System.out.println(max);
	}

}