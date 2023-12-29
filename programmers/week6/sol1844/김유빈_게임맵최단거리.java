package week6.sol1844;

import java.util.*;

class 김유빈_게임맵최단거리 {

    private static int[][] visited;
    private static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int solution(int[][] maps) {

        int height = maps.length;
        int width = maps[0].length;

        visited = new int[height][width];

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        visited[0][0] = 1;

        int nextx, nexty;
        while (!q.isEmpty()) {

            int[] point = q.poll();

            for (int[] direction : directions) {
                nextx = point[1] + direction[1];
                nexty = point[0] + direction[0];

                if (nextx < 0 || nexty < 0 || nextx >= width || nexty >= height) {
                    continue;
                }
                if (maps[nexty][nextx] == 0) {
                    continue;
                }
                if (visited[nexty][nextx] != 0) {
                    continue;
                }

                visited[nexty][nextx] = visited[point[0]][point[1]] + 1;

                if (nextx == width - 1 && nexty == height - 1) {
                    break;
                }
                q.offer(new int[] {nexty, nextx});
            }
        }

        int result = visited[height - 1][width - 1];
        if (result == 0) {
            return -1;
        }
        return result;
    }
}
