package sol14940;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class MapPoint {
    public int x;
    public int y;

    MapPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class 엄상은_쉬운_최단거리 {
    public static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new int [n][m];

        // bfs에서 이용할 자료구조 선언
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<MapPoint> q = new LinkedList<>();
        int[][] visited = new int[n][m];

        // map 입력값 받기
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    int startX = i;
                    int startY = j;
                    q.add(new MapPoint(startX, startY));
                }
            }
        }

        // 1) map에서 bfs를 이용해 최단 거리 계산 (시작지점: startX, startY)
        while (!q.isEmpty()){
            MapPoint head = q.poll();
            for (int i = 0; i < 4; i++){
                int x = head.x + dx[i];
                int y = head.y + dy[i];
                if ((0 <= x) && (x < n) && (0 <= y) && (y < m)) {
                    if ((visited[x][y] == 0) && (map[x][y] == 1)) {
                        q.add(new MapPoint(x, y));
                        visited[x][y] = visited[head.x][head.y] + 1;
                    }
                }
            }
        }

        // 2) 후에 map 전체 탐색 후 1이라면 -1로 바꿔주기
        // 3) 바로 출력
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if ((visited[i][j] == 0) && (map[i][j] == 1)) {
                    visited[i][j] = -1;
                }
                bw.write(visited[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
    }
}
