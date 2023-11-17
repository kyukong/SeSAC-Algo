import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] board;
	static long[][] d;

	public static void 김동욱_점프(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		d = new long[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(board[i]));
//		}

		d[0][0] = 1;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				
				if(board[i][j]<=0) 
					continue;
				
					
				
				int row = board[i][j]+j;
				int col = board[i][j]+i;
				
				if(row>=0 && row<N) {
					d[i][row]+= d[i][j];
				}
				if(col>=0 && col <N) {
					d[col][j]+= d[i][j];
				}
				
			}
		}
		
		System.out.println(d[N-1][N-1]);
	}
}
