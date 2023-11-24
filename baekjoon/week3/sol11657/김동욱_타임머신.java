import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 김동욱_타임머신 {
	public static class Point {
		int from;
		int to;
		int edge;

		public Point() {
		}

		public Point(int from, int to, int edge) {
			
			this.from = from;
			this.to = to;
			this.edge = edge;
		}

		@Override
		public String toString() {
			return "Point [from=" + from + ", to=" + to + ", edge=" + edge + "]";
		}

		

		
		
	}

	static int N;
	static int M;
	static long[] d;
	static ArrayList<Point> edges;
	static boolean isCycle;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		d= new long[N+1];
		edges = new ArrayList<>();
		isCycle= false;
		sb = new StringBuilder();
		for(int i=0;i<=N;i++) {
			d[i] = Long.MAX_VALUE;
		}
		d[1]=0;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			edges.add(new Point(from,to,val));
			
		}
		
		
		for(int i=1;i<N+1;i++) { //N-1 번 반복
			//System.out.println(i+1 +" 번째 반복");
			for(int j=0;j<M;j++) {
				Point p = edges.get(j);
				if(d[p.from]!=Long.MAX_VALUE && d[p.from]+p.edge<d[p.to]) {
					d[p.to]= d[p.from]+p.edge;
					if(i==N) {
						isCycle = true;
					}
				}
				
			}
		}
		//System.out.println(Arrays.toString(d));
		if(isCycle) {
			sb.append(-1);
		}
		else {
			for(int i=1;i<=N;i++) {
				if(i==1) continue;
				if(d[i]==Long.MAX_VALUE) {
					sb.append(-1+"\n");
					continue;
				}
				sb.append(d[i]+"\n");
			}
		}
		System.out.println(sb);
	}
}
