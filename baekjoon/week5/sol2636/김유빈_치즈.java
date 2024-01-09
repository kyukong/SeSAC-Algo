package week5.sol2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김유빈_치즈 {

    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static int width;
    private static int height;
    private static int[][] board;
    private static int cheese = 0;
    private static int round;
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        board = new int[height][width];

        for (int y = 0; y < height; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < width; x++) {
                board[y][x] = Integer.parseInt(st.nextToken());
                if (board[y][x] == 1) {
                    cheese++;
                }
            }
        }

        while (cheese != 0) {
            round++;
            result = cheese;
            bfs();
        }

        System.out.println(round);
        System.out.println(result);
    }

    private static void bfs() {

        int nextX, nextY;
        int[][] visited = new int[height][width];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        visited[0][0] = 1;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();

            for (int[] direction : directions) {
                nextX = point[0] + direction[0];
                nextY = point[1] + direction[1];

                if (nextX < 0 || nextY < 0 || nextX >= width || nextY >= height) {
                    continue;
                }
                if (visited[nextY][nextX] == 1) {
                    continue;
                }
                visited[nextY][nextX] = 1;

                if (board[nextY][nextX] == 0) {
                    queue.offer(new int[] {nextX, nextY});
                    continue;
                }
                cheese--;
                board[nextY][nextX] = 0;
            }
        }
    }
}
