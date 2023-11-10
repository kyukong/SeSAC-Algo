package sol1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 김유빈_DFS와_BFS {

    private static final Map<Integer, Set<Integer>> nodes = new HashMap<>();
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        // 빈 연결 리스트 생성
        for (int i = 0; i < n; i++) {
            nodes.put(i + 1, new TreeSet<>());
        }

        // 입력받은 노드들 저장
        int num1, num2;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            num1 = Integer.parseInt(st.nextToken());
            num2 = Integer.parseInt(st.nextToken());

            nodes.get(num1).add(num2);
            nodes.get(num2).add(num1);
        }

        dfs(start, new int[n]);
        sb.append("\n");
        bfs(start, new int[n]);

        System.out.println(sb);
    }

    private static void dfs(int node, int[] visited) {
        if (visited[node - 1] == 1) {
            return;
        }

        visited[node - 1] = 1;
        sb.append(node);
        sb.append(" ");

        for (int next : nodes.get(node)) {
            dfs(next, visited);
        }
    }

    private static void bfs(int node, int[] visited) {
        List<Integer> queue = new ArrayList<>();
        queue.add(node);
        visited[node - 1] = 1;

        while (!queue.isEmpty()) {
            int now = queue.remove(queue.size() - 1);
            sb.append(now);
            sb.append(" ");

            for (int next : nodes.get(now)) {
                if (visited[next - 1] == 1) {
                    continue;
                }
                queue.add(0, next);
                visited[next - 1] = 1;
            }
        }
    }
}
