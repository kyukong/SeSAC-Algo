import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;
	static int[] weight;
	static int[] profit;
	static int[][] board;
	static int answer;
	public static void 김동욱_평범한배낭(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new int[N+1][K+1];
		answer = Integer.MIN_VALUE;
		weight= new int[N+1];
		profit = new int[N+1];
		for(int i=1;i<=N;i++) {
			st= new StringTokenizer(br.readLine());
			weight[i]= Integer.parseInt(st.nextToken());
			profit[i]= Integer.parseInt(st.nextToken());
		}
		
		for(int i=1;i<=N;i++) {
			for(int w=1;w<=K;w++) {
				int preVal = board[i-1][w];
				int curValIndex= w-weight[i];
				if(curValIndex<0) {
					board[i][w] = preVal;
					continue;
				}
				board[i][w] = Math.max(preVal, board[i-1][curValIndex]+profit[i]);
			}
		}
		System.out.println(board[N][K]);

	}

}
