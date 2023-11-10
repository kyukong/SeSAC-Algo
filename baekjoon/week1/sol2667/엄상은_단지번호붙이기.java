package sol2667;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

class VPoint {
    public int x;
    public int y;

    VPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class 엄상은_단지번호붙이기 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());

        // map 초기화
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String s = bf.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(s.substring(j, j+1));
            }
        }

        // visited, 단지배열 선언
        boolean[][] visited = new boolean[n][n];
        ArrayList<Integer> village = new ArrayList<>();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                visited[i][j] = false;
            }
        }

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        // 한 단지를 그래프 완전탐색 -> 하면서 단지 내 집의 수를 배열에 저장
        // 정사각형에서 1이 남은 곳이 있는지를 확인
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (map[i][j] == 0)
                    continue;
                else if (visited[i][j])
                    continue;
                else {
                    Queue<VPoint> q = new LinkedList<>();
                    q.add(new VPoint(i, j));
                    int count = 0;
                    while (!q.isEmpty()) {
                        VPoint head = q.poll();
                        if ((!visited[head.x][head.y]) && (map[head.x][head.y]==1)) {
                            for (int m = 0; m < 4; m++) {
                                int x = head.x + dx[m];
                                int y = head.y + dy[m];
                                if ((0 <= x) && (x < n) && (0 <= y) && (y < n)) {
                                    if (!visited[x][y]) {
                                        q.add(new VPoint(x, y));
                                    }
                                }
                            }
                            visited[head.x][head.y] = true;
                            count++;
                        }
                    }
                    village.add(count);
                }
            }
        }
        bw.write(village.size() + "\n");
        for (int villageCount: village.stream().sorted().collect(Collectors.toList())){
            bw.write(villageCount + "\n");
        }
        bw.flush();
    }
}
