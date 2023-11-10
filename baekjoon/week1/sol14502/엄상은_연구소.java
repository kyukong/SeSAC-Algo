package sol14502;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class 엄상은_연구소 {
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
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] virus = new int [n][m];
        ArrayList<Point> target = new ArrayList<>();

        for (int i = 0; i < n; i++){
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++){
                virus[i][j] = Integer.parseInt(st.nextToken());
                if (virus[i][j] == 0) {
                    target.add(new Point(i, j));
                }
            }
        }

        Stack<Point> s = new Stack<>();
        int [][] visited = new int [n][m];
        int count = 0;
        int max_count = 0;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        // 벽 쌓기 k < j < i
        Point wall1;
        Point wall2;
        Point wall3;
        for (int i = 2; i < target.size(); i++){
            for (int j = 1; j < i; j++){
                for (int k = 0; k <j; k++){
                    // i, j, k 결정
                    wall1 = target.get(i);
                    wall2 = target.get(j);
                    wall3 = target.get(k);

                    virus[wall1.x][wall1.y] = 1;
                    virus[wall2.x][wall2.y] = 1;
                    virus[wall3.x][wall3.y] = 1;

                    // virus 퍼뜨리고 안전구역 검색 - dfs
                    for (int x = 0; x < n; x++){
                        for (int y = 0; y < m; y++) {
                            if (virus[x][y] == 1)
                                visited[x][y] = 1;
                            else if (virus[x][y] == 2) { // 시작 노드 설정
                                s.add(new Point(x, y));
                                while (!s.isEmpty()) {
                                    Point top = s.pop();
                                    if (visited[top.x][top.y] == 0){
                                        for (int move = 0; move < 4; move++){
                                            int newX = top.x + dx[move];
                                            int newY = top.y + dy[move];
                                            if ((0 <= newX) && (newX < n) && (0 <= newY) && (newY < m)) {
                                                if ((visited[newX][newY] == 0) && (virus[newX][newY] != 1))  {
                                                    s.add(new Point(newX, newY));
                                                }
                                            }
                                        }
                                        visited[top.x][top.y] = 2;
                                    }
                                }
                            }

                        }
                    }
                    // visited 출력
                    for (int x = 0; x < n; x++){
                        for (int y = 0; y < m; y++){
                            if (visited[x][y] == 0)
                                count++;
                        }
                    }
                    if (count > max_count)
                        max_count = count;

                    count = 0;
                    visited = new int [n][m];

                    // 벽 해제
                    virus[wall1.x][wall1.y] = 0;
                    virus[wall2.x][wall2.y] = 0;
                    virus[wall3.x][wall3.y] = 0;
                }
            }
        }
        System.out.println(max_count);
    }
}