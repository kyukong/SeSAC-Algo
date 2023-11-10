package sol1260;

import java.util.*;
import java.util.stream.Collectors;

public class 엄상은_DFS와_BFS {
    public static ArrayList<Integer>[] graph;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int v = sc.nextInt();

        // graph 선언
        graph = new ArrayList[n+1];
        for (int i = 0; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        // graph 초기화
        for (int i = 0; i < m; i++){
            int x1 = sc.nextInt();
            int x2 = sc.nextInt();
            graph[x1].add(x2);
            graph[x2].add(x1);
        }

        // dfs
        dfs(graph, v);

        // bfs
        bfs(graph, v);

    }

    private static void dfs(ArrayList<Integer>[] graph, int v) {
        // 0) stack, visited 선언
        Stack<Integer> s = new Stack<>();
        boolean visited[] = new boolean[graph.length];
        for (boolean b: visited){
            b = false;
        }

        // 1) v에서 시작 -> v를 stack에 넣음
        s.add(v);

        // 2-1) 반복문: stack이 빌 때까지
        // 2-2) stack에서 pop하고 그 값을 visited로 바꿈
        // 2-3) 그 노드의 인접 노드를 (!visited일 경우에만) 스택에 넣음
        while (!s.isEmpty()) {
            int top = s.pop();

            if (!visited[top]) {
                visited[top] = true;
                for (int item : graph[top].stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList())) {
                    if (!visited[item])
                        s.add(item);
                }
                System.out.print(top + " ");
            }
        }
        System.out.println();
    }

    private static void bfs(ArrayList<Integer>[] graph, int v) {
        // 0) queue, visited 선언
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        for (boolean b: visited){
            b = false;
        }

        // 1) v에서 시작 -> v를 queue에 넣음
        q.add(v);

        // 2-1) 반복문: queue가 빌 때까지
        // 2-2) queue에서 poll하고 그 값을 visited로 바꿈
        // 2-3) 그 노드의 인접 노드를 (!visited일 경우에만) queue가에 넣음
        while (!q.isEmpty()) {
            int head = q.poll();
            if (!visited[head]) {
                visited[head] = true;
                for (int item: graph[head].stream().sorted().collect(Collectors.toList())) {
                    if (!visited[item])
                        q.add(item);
                }
                System.out.print(head + " ");
            }
        }
        System.out.println();
    }
}
