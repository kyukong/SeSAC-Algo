package week7.sol1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 김유빈_알파벳 {

    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static int width;
    private static int height;
    private static String[][] board;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        board = new String[height][width];

        String[] line;
        for (int i = 0; i < height; i++) {
            line = br.readLine().split("");
            for (int j = 0; j < width; j++) {
                board[i][j] = line[j];
            }
        }

        int[][] visited = new int[height][width];
        visited[0][0] = 1;

        HashMap<String, Integer> alpha = new HashMap<>();
        alpha.put(board[0][0], 1);

        dfs(0, 0, visited, 1, alpha);

        System.out.println(result);
    }

    private static void dfs(int x, int y, int[][] visited, int count, Map<String, Integer> alpha) {

        int nextX, nextY, alphaCount;
        for (int[] direction : directions) {
            nextX = x + direction[0];
            nextY = y + direction[1];

            if (nextX < 0 || nextY < 0 || nextX >= width || nextY >= height) {
                result = Math.max(result, count);
                continue;
            }
            if (visited[nextY][nextX] == 1) {
                result = Math.max(result, count);
                continue;
            }

            alphaCount = alpha.getOrDefault(board[nextY][nextX], 0);
            if (alphaCount > 0) {
                result = Math.max(result, count);
                continue;
            }

            visited[nextY][nextX] = 1;
            alpha.put(board[nextY][nextX], alphaCount + 1);

            dfs(nextX, nextY, visited, count + 1, alpha);

            visited[nextY][nextX] = 0;
            alpha.put(board[nextY][nextX], 0);
        }
    }
}
