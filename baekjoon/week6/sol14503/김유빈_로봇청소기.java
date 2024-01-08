package week6.sol14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김유빈_로봇청소기 {

    private static final int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    private static int width;
    private static int height;
    private static int count = 0;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        map = new int[height][width];

        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < width; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dir;
        int nextX = 0, nextY = 0;
        while (true) {

            if (map[y][x] == 0) {
                count++;
                map[y][x] = -1;
            }

            // 주변 4칸 중 청소하지 않은 칸이 있는 경우
            int empty = 0;
            int nextD = d;
            for (int i = 0; i < 4; i++) {
                nextD = getNext(nextD);
                dir = directions[nextD];
                nextX = x + dir[0];
                nextY = y + dir[1];

                if (map[nextY][nextX] == 0) {
                    empty++;
                    break;
                }
            }

            if (empty > 0) {
                x = nextX;
                y = nextY;
                d = nextD;
                continue;
            }

            // 주변 4칸 중 청소하지 않은 칸이 없는 경우 후진할 수 있는 경우 후진 (후진하는 칸은 청소된 칸)
            dir = directions[d];
            x = x - dir[0];
            y = y - dir[1];

            if (map[y][x] == 1) {
                break;
            }
        }

        System.out.println(count);
    }

    private static int getNext(int d) {
        if (d == 0) {
            return 3;
        }
        return d - 1;
    }
}
