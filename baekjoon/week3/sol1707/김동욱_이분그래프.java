import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김동욱_이분그래프 {
    static int K;
    static int V, E;
    static ArrayList<Integer>[] adjList;
    static int[] visited; // 0 ,1 , 2
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        K = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            adjList = new ArrayList[V + 1];
            visited = new int[V + 1];

            for (int i = 1; i <= V; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adjList[u].add(v);
                adjList[v].add(u);
            }

            boolean flag = true;

            for (int i = 1; i <= V; i++) {
                if (visited[i] == 0) {
                    if (!bfs(i)) {
                        flag = false;
                        break;
                    }
                }
            }

            if (flag) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.println(sb);
    }

    private static boolean bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = 1; 

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : adjList[u]) {
                if (visited[v] == 0) {
                   
                    visited[v] = (visited[u] == 1) ? 2 : 1;
                    queue.add(v);
                } else if (visited[u] == visited[v]) {
                    return false; 
                }
            }
        }

        return true;
    }
}
