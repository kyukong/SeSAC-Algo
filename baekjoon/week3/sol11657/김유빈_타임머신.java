import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int m;
    private static int[][] edges;
    private static long[] distances;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        distances = new long[n + 1];

        // 배열 초기화
        for (int i = 2; i < n + 1; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        edges = new int[m][3];

        // 버스 정보 저장
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        // System.out.println(Arrays.deepToString(edges));

        if (!bellmanFord()) {
            System.out.println(-1);
            return;
        }

        for (int i = 2; i < n + 1; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                sb.append(-1).append("\n");
            } else {
                sb.append(distances[i]).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean bellmanFord() {
        // 거리 구하기
        for (int i = 1; i <= n - 1; i++) {
            for (int[] edge : edges) {
                if (distances[edge[0]] == Integer.MAX_VALUE) {
                    continue;
                }
                if (distances[edge[1]] > distances[edge[0]] + edge[2]) {
                    distances[edge[1]] = distances[edge[0]] + edge[2];
                }
            }
        }

        // 음수 가중치 확인
        for (int[] edge : edges) {
            if (distances[edge[0]] == Integer.MAX_VALUE) {
                continue;
            }
            if (distances[edge[1]] > distances[edge[0]] + edge[2]) {
                return false;
            }
        }
        return true;
    }
}
