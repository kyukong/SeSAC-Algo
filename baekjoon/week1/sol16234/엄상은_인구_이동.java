package sol16234;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class CPoint {
    public int x;
    public int y;

    CPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class 엄상은_인구_이동 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        // countries 입력 받기
        int[][] countries = new int[n][n];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++){
                countries[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        // 인구이동이 가능한지 확인
        // 인구이동 실행
        int days = 0;
        boolean [][] visited = new boolean [n][n];
        Queue<CPoint> q = new LinkedList<>();
        ArrayList<CPoint> points = new ArrayList<>();

        int sum = 0;
        int count = 0;
        boolean changed = false;
        while(true) {
            changed = false;
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (!visited[i][j]) {
                        q.add(new CPoint(i, j));
                        sum = 0;
                        count = 0;
                        while (!q.isEmpty()) {
                            CPoint head = q.poll();
                            if (!visited[head.x][head.y]) {
                                for (int m = 0; m < 4; m++){
                                    int x = head.x + dx[m];
                                    int y = head.y + dy[m];
                                    if ((0 <= x) && (x < n) && (0 <= y) && (y < n)) {
                                        if ((l <= Math.abs(countries[x][y] - countries[head.x][head.y]))
                                            && (Math.abs(countries[x][y] - countries[head.x][head.y]) <= r)) {
                                            if (!visited[x][y]) {
                                                q.add(new CPoint(x, y));
                                            }
                                        }
                                    }
                                }
                                visited[head.x][head.y] = true;
                                count++;
                                sum += countries[head.x][head.y];
                                points.add(new CPoint(head.x, head.y));
                            }
                        }
                        int avg = sum / count;
                        if (count > 1) {
                            changed = true;
                            for (CPoint p : points) {
                                countries[p.x][p.y] = avg;
                            }
                        }
                        points.clear();
                    }
                }
            }
            visited = new boolean [n][n];
            if (!changed)
                break;
            days++;
        }
        System.out.println(days);
    }
}
