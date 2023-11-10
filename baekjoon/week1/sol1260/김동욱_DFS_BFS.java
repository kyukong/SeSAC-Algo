import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김동욱_BFS와_DFS {
	static int N;
	static int M;
	static int V;
	static int[][] adj;
	static boolean[] visited;
	static boolean[] visited1;
	static Queue<Integer> queue;
	static StringBuilder sb;
	static StringBuilder sb1;
	public static void dfs(int x) {
		if(visited[x]) {return;}
		
		visited[x]=true;
		sb.append(x+" ");
		for(int i=0;i<adj[x].length;i++) {
			if(adj[x][i]==1)
			dfs(i);
		}
		
	}
	  public static void bfs(int x) {
	        queue.add(x);
	        visited1[x] = true;
	        while (!queue.isEmpty()) {
	            int temp = queue.poll();
	           // System.out.println(temp);
	            sb1.append(temp+" ");
	            for (int i = 1; i < N + 1; i++) {
	                if (!visited1[i] && adj[temp][i] == 1) {
	                    visited1[i] = true;
	                    queue.add(i);
	                }
	            }
	        }
	    }

		
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		V= Integer.parseInt(st.nextToken());
		adj = new int[N+1][N+1];
		visited= new boolean[N+1];
		visited1= new boolean[N+1];
		queue = new LinkedList<Integer>();
		sb= new StringBuilder();
		sb1= new StringBuilder();
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
				int from  = Integer.parseInt(st.nextToken());
				int to  = Integer.parseInt(st.nextToken());
				
				adj[from][to]=1;
				adj[to][from]=1;
		}// end input
//		for(int i=0;i<N+1;i++) {
//			for(int j=0;j<N+1;j++) {
//				System.out.printf("%d ",adj[i][j]);
//			}
//			System.out.println();
//		}
		dfs(V);
		bfs(V);
		//sb.delete(2*N-1,2*N);
		System.out.println(sb);
		//sb1.delete(2*N-1,2*N);
		System.out.println(sb1);
	}

}
