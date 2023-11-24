import java.util.*;
import java.io.*;

public class 김유빈_최소스패닝트리 {

    private static int v;
    private static int e;
    private static PriorityQueue<Node> edges = new PriorityQueue<>();
    private static int[] parents;
    private static int result = 0;

    private static class Node implements Comparable<Node> {

        int start;
        int end;
        int weight;

        public Node(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            if (weight > other.weight) {
                return 1;
            } else if (weight < other.weight) {
                return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        // 부모 배열 초기화
        parents = new int[v + 1];

        // 간선 정보 저장
        int start, end, weight;
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            edges.offer(new Node(start, end, weight));
        }

        // 최소 가중치 간선 선택
        int count = 0;
        while (true) {
            Node node = edges.poll();
            if (node == null || count >= v - 1) {
                break;
            }

            // 간선 추가 (parent 기록)
            int parent1 = parents[node.start];
            int parent2 = parents[node.end];

            if (parent1 == 0 && parent2 == 0) {
                parents[node.start] = node.start;
                parents[node.end] = node.start;
            } else if (parent1 == 0) {
                parents[node.start] = node.end;
            } else if (parent2 == 0) {
                parents[node.end] = node.start;
            } else {
                int root1 = find(node.start);
                int root2 = find(node.end);

                if (root1 ==  root2) {
                    continue;
                }
                parents[root2] = root1;
            }

            result += node.weight;
            count++;
        }

        System.out.println(result);
    }

    private static int find(int node) {
        if (node == parents[node]) {
            return node;
        }
        return find(parents[node]);
    }
}
