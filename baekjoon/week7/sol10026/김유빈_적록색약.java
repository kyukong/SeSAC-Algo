package week7.sol10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class 김유빈_적록색약 {

    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static int N;
    private static String[][] board1;
    private static String[][] board2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board1 = new String[N][N];
        board2 = new String[N][N];

        String[] input;
        for (int y = 0; y < N; y++) {
            input = br.readLine().split("");
            for (int x = 0; x < N; x++) {
                board1[y][x] = input[x];

                if (input[x].equals("G")) {
                    board2[y][x] = "R";
                } else {
                    board2[y][x] = input[x];
                }
            }
        }

        int result1 = bfs(board1);
        int result2 = bfs(board2);

        System.out.println(result1 + " " + result2);
    }

    private static int bfs(String[][] board) {
        int[][] area = new int[N][N];
        Queue<int[]> queue = new LinkedList<>();

        int count = 0;
        int nextX, nextY;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (area[y][x] != 0) {
                    continue;
                }
                queue.offer(new int[] {x, y});
                area[y][x] = ++count;

                while (!queue.isEmpty()) {
                    int[] point = queue.poll();

                    for (int[] direction : directions) {
                        nextX = point[0] + direction[0];
                        nextY = point[1] + direction[1];

                        if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
                            continue;
                        }
                        if (area[nextY][nextX] != 0) {
                            continue;
                        }
                        if (!Objects.equals(board[nextY][nextX], board[point[1]][point[0]])) {
                            continue;
                        }

                        queue.offer(new int[] {nextX, nextY});
                        area[nextY][nextX] = area[point[1]][point[0]];
                    }
                }
            }
        }

        return count;
    }
}
