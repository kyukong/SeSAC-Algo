package sol5427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 엄상은_불 {
    private static class Point {
        public int x;
        public int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int t = Integer.parseInt(st.nextToken());

        while (t-- > 0) {
            st = new StringTokenizer(bf.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            char[][] building = new char[h][w];
            Point start = new Point(-1, -1);
            for (int i = 0; i < h; i++){
                String rl = bf.readLine();
                for (int j = 0; j < w; j++){
                    building[i][j] = rl.charAt(j);
                    if (building[i][j] == '@') {
                        start = new Point(i, j);
                    }
                }
            }

            // bfs를 이용해 상근이가 이동할 수 있도록 하기
            bfs(building, start);



            // 다 이동한 후에 테두리 부분에 숫자가 있으면 최솟값 출력


        }
    }
    public static void bfs(char[][] building, Point start) {
        // visited에서 상근이 거리는 숫자로 카운팅
        // 불은 마이너스
        // 어떤 부분에 상근이가 먼저 도착하면 +일 것
        // 불이 먼저 도착하면 -일 것임
        // 상근이는 이미 -인 곳에 갈 수는 없음
        int h = building.length;
        int w = building[0].length;
        int[][] fire = new int[h][w];
        int[][] dog = new int[h][w];

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Point> q = new LinkedList<>();

        // 상근이 이동
        q.add(start);
        while (!q.isEmpty()) {
            Point head = q.poll();
            if ((building[head.x][head.y] == '.') || (building[head.x][head.y] == '@')) {
                for (int m = 0; m < 4; m++) {
                    int x = head.x + dx[m];
                    int y = head.y + dy[m];
                    if ((0 > x) || (x >= h) || (0 > y) || (y >= w))
                        continue;
                    if (((building[x][y] == '.') || (building[x][y] == '@')) && (dog[x][y] == 0)) {
                        q.add(new Point(x, y));
                        dog[x][y] = dog[head.x][head.y] + 1;
                    }
                }
            }
        }

        // 불 이동
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++){
                if (building[i][j] != '*')
                    continue;
                q.add(new Point(i, j));
            }
        }

        while (!q.isEmpty()) {
            Point head = q.poll();
            for (int m = 0; m < 4; m++){
                int x = head.x + dx[m];
                int y = head.y + dy[m];
                if ((0 > x) || (x >= h) || (0 > y) || (y >= w))
                    continue;
                if (((building[x][y] == '.') || (building[x][y] == '@')) && (fire[x][y] == 0)) {
                    q.add(new Point(x, y));
                    fire[x][y] = fire[head.x][head.y] + 1;
                }
            }
        }

        int min = 10000000;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++){
                if ((i == 0) || (i == (h-1)) || (j == 0) || (j == (w-1))) {
                    if (((fire[i][j] - dog[i][j] > 0 || (fire[i][j] == 0))) && (dog[i][j] > 0)) {
                        min = min > (dog[i][j] + 1) ? (dog[i][j] + 1) : min;
                    }
                    if (building[i][j] == '@')
                        min = 1;
                }
            }
        }
        if (min == 10000000)
            System.out.println("IMPOSSIBLE");
        else
            System.out.println(min);
    }
}
