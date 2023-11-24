import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 김동욱_최소스패닝트리 {
	public static class Node implements Comparable<Node> {
		int num;
		int weight;

		public Node(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}

		@Override
		public String toString() {
			return "Node [num=" + num + ", weight=" + weight + "]";
		}
		

	}

	static int V;
	static int E;
	static LinkedList<Node>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adjList = new LinkedList[V + 1];

		for (int v = 1; v <= V; v++) {
			adjList[v] = new LinkedList<>();
		}
		for(int i=0;i<E;i++) {
			st= new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new Node(to,weight));
			adjList[to].add(new Node(from,weight));
			
		} // end input
//		for (int v = 1; v <= V; v++) {
//			for(Node n : adjList[v]) {
//				System.out.print(n+" ");
//			}
//			System.out.println();
//		}
		
		boolean[] visited = new boolean[V+1];
		long result =0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1,0));
		int vertexCount =0;
		
		while(!pq.isEmpty()) {
			Node minEdge = pq.poll();
			
			if(visited[minEdge.num]) continue;
			result+=minEdge.weight;
			visited[minEdge.num]=true;
			vertexCount++;
			if(vertexCount==V) break;
			for(Node node : adjList[minEdge.num]) {
				if(!visited[node.num])
					pq.add(node);
			}
		}
		System.out.println(result);
	}
}
