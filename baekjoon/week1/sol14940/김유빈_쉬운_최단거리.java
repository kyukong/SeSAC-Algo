package sol14940;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김유빈_쉬운_최단거리 {

    private static int n;
    private static int m;
    private static int[][] map;
    private static int[][] result;
    private static int[][] visited;
    private static int startX;
    private static int startY;
    private static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        result = new int[n][m];
        visited  = new int[n][m];

        // 배열 초기화
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 2) {
                    startX = j;
                    startY = i;
                }

                result[i][j] = -1;
                map[i][j] = num;

                if (num == 0) {
                    result[i][j] = 0;
                }
            }
        }

        result[startY][startX] = 0;
        bfs();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(startX, startY));
        visited[startY][startX] = 1;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int[] direction : directions) {
                int nextX = node.x + direction[0];
                int nextY = node.y + direction[1];

                if (nextX < 0 || nextY < 0 || nextX >= m || nextY >= n) {
                    continue;
                }
                if (map[nextY][nextX] != 1) {
                    continue;
                }
                if (visited[nextY][nextX] == 1) {
                    continue;
                }

                result[nextY][nextX] = result[node.y][node.x] + 1;
                queue.offer(new Node(nextX, nextY));
                visited[nextY][nextX] = 1;
            }
        }
    }

    private static class Node {

        int x;
        int y;

        public Node(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
}
