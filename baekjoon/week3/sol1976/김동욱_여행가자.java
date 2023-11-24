import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 김동욱_여행가자 {
	static int N;
	static int M;

	static boolean[][] visited;
	static int[] schedule;
	static int[] nodes;
	static int[] heads;
	static boolean flag;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N= Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		flag = false;
		
		schedule = new int[M];
		nodes = new int[N+1];
		heads = new int[N+1];
		visited= new boolean[N+1][N+1];
		for(int i=1;i<=N;i++) {
			nodes[i]=i;
			heads[i]=i;
		}
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp==1 && !visited[i][j]&& !visited[j][i]) {
					union(i,j);
					visited[i][j]=true;
					visited[j][i]=true;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			schedule[i]= Integer.parseInt(st.nextToken());
		} // end input
		
		//System.out.println(Arrays.toString(heads));
		//System.out.println(Arrays.toString(nodes));
		
		int firstNodeHead = find(schedule[0]);
		
//		for(int i=1;i<M;i++) {
//			if(find(schedule[i])!= firstNodeHead ) {
//				if(schedule[i] == heads[schedule[i]]) {
//					
//					firstNodeHead= schedule[i];
//				}
//				else {
//				flag= true;
//				break;
//				}
//			}
//		}
		for(int i=1;i<M;i++) {
			if(firstNodeHead!= find(schedule[i])) {
				flag= true;
				break;
			}
		}
		if(flag) {
			System.out.println("NO");
		}
		else
			System.out.println("YES");
//		for(int i=0;i<N;i++) {
//			System.out.println(Arrays.toString(board[i]));
//		}
//		System.out.println(Arrays.toString(schedule));
		
		
	}
	
	public static void union(int x, int y) {
		x= find(x);
		y = find(y);
		int min = x>y? y:x;
		int max = x>y? x:y;
		heads[max]=min;
	}
	public static int find(int x) {
		if(x==heads[x])
			return x;
		else {
			int temp =find(heads[x]);
			heads[x]=temp;
			return temp;
		}
	}
}
