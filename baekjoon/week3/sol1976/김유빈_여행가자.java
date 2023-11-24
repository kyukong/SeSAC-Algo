import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int m;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        // parent 초기화
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        // 같은 그룹에 있는 노드 조회
        int node, next, connect;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            node = find(i);

            for (int j = 1; j <= n; j++) {
                connect = Integer.parseInt(st.nextToken());
                if (connect == 0 || i == j) {
                    continue;
                }
                next = find(j);
                if (node != next) {
                    parents[next] = node;
                }
            }
        }

        // 여행 계획 지점이 같은 그룹인지 판별
        st = new StringTokenizer(br.readLine());
        int group = 1;
        for (int i = 0; i < m; i++) {
            node = find(Integer.parseInt(st.nextToken()));
            if (i == 0) {
                group = node;
                continue;
            }
            if (node != group) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static int find(int node) {
        if (node == parents[node]) {
            return node;
        }
        return parents[node] = find(parents[node]);
    }
}
