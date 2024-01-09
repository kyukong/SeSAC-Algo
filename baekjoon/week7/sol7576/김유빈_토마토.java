package week7.sol7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 김유빈_토마토 {

    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static int width;
    private static int height;
    private static int[][] board;
    private static int result = 0;
    private static int total;
    private static List<int[]> tomatoes = new ArrayList<>();
    private static List<int[]> added = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());
        board = new int[height][width];
        total = width * height;

        for (int y = 0; y < height; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < width; x++) {
                board[y][x] = Integer.parseInt(st.nextToken());
                if (board[y][x] != 0) {
                    total--;
                }
                if (board[y][x] == 1) {
                    tomatoes.add(new int[] {x, y});
                }
            }
        }

        bfs();

        System.out.println(result);
    }

    private static void bfs() {

        int after;
        while (true) {

            if (total == 0) {
                return;
            }
            after = total;
            result++;

            // 상화좌우 익히기
            int nextX, nextY;
            added = new ArrayList<>();
            for (int[] tomato : tomatoes) {
                for (int[] direction : directions) {
                    nextX = tomato[0] + direction[0];
                    nextY = tomato[1] + direction[1];

                    if (nextX < 0 || nextY < 0 || nextX >= width || nextY >= height) {
                        continue;
                    }
                    if (board[nextY][nextX] != 0) {
                        continue;
                    }
                    board[nextY][nextX] = 1;
                    after--;
                    added.add(new int[] {nextX, nextY});
                }
            }

            tomatoes = added;

            if (total == after) {
                result = -1;
                break;
            }
            if (after == 0) {
                break;
            }
            total = after;
        }
    }
}
