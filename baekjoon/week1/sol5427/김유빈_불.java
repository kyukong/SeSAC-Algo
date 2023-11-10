package sol5427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김유빈_불 {

    private static int col;
    private static int row;
    private static char[][] map;
    private static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static char dog;
    private static StringBuilder sb = new StringBuilder();
    private static Queue<Node> queueFire;
    private static Queue<Node> queueDogs;
    private static int[][] visitedFire;
    private static int[][] visitedDogs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int testCase = Integer.parseInt(st.nextToken());
        // todo: 팀원들에게 물어보기,,

        for (int i = 0; i < testCase; i++) {
            st = new StringTokenizer(br.readLine());
            col = Integer.parseInt(st.nextToken());
            row = Integer.parseInt(st.nextToken());

            map = new char[row][col];
            dog = '1';
            queueFire = new LinkedList<>();
            queueDogs = new LinkedList<>();
            visitedFire = new int[row][col];
            visitedDogs = new int[row][col];

            // 배열 초기화
            String line;
            for (int y = 0; y < row; y++) {
                st = new StringTokenizer(br.readLine());
                line = st.nextToken();
                for (int x = 0; x < col; x++) {
                    char str = line.charAt(x);
                    map[y][x] = str;
                    if (str == '@') {
                        map[y][x] = dog;
                        queueDogs.offer(new Node(x, y));
                        visitedDogs[y][x] = 1;
                    } else if (str == '*') {
                        queueFire.offer(new Node(x, y));
                        visitedFire[y][x] = 1;
                    }
                }
            }
            bfs();
        }
        System.out.println(sb);
    }

    private static void bfs() {
        int size;
        while (!queueDogs.isEmpty()) {
            // 상근이 위치 이동 및 다음 이동 경로 저장
            dog = (char)((int)dog + 1);

            size = queueDogs.size();
            for (int i = 0; i < size; i++) {
                Node point = queueDogs.poll();

                for (int[] direction : directions) {
                    int nextX = point.x + direction[0];
                    int nextY = point.y + direction[1];

                    if (nextX < 0 || nextY < 0 || nextX >= col || nextY >= row) {
                        continue;
                    }
                    if (map[nextY][nextX] != '.' || visitedDogs[nextY][nextX] == 1) {
                        continue;
                    }
                    map[nextY][nextX] = dog;
                    queueDogs.offer(new Node(nextX, nextY));
                    visitedDogs[nextY][nextX] = 1;

                    // 테두리에 도착하면 성공
                    if (nextX == 0 || nextY == 0 || nextX == col - 1 || nextY == row - 1) {
                        sb.append(dog);
                        sb.append("\n");
                        return;
                    }
                }
            }

            System.out.println("상근이 이동");
            for (int i = 0; i < row; i++) {
                System.out.println(map[i]);
            }

            // 불 위치 이동 및 다음 불길 저장
            size = queueFire.size();
            for (int i = 0; i < size; i++) {
                Node point = queueFire.poll();

                for (int[] direction : directions) {
                    int nextX = point.x + direction[0];
                    int nextY = point.y + direction[1];

                    if (nextX < 0 || nextY < 0 || nextX >= col || nextY >= row) {
                        continue;
                    }
                    if (map[nextY][nextX] == '#' || visitedFire[nextY][nextX] == 1) {
                        continue;
                    }
                    map[nextY][nextX] = '*';
                    queueFire.offer(new Node(nextX, nextY));
                    visitedFire[nextY][nextX] = 1;
                }
            }

            System.out.println("불 이동");
            for (int i = 0; i < row; i++) {
                System.out.println(map[i]);
            }

            System.out.println("===");
        }
        sb.append("IMPOSSIBLE");
        sb.append("\n");
    }

    private static class Node {

        int x;
        int y;

        public Node(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
}
