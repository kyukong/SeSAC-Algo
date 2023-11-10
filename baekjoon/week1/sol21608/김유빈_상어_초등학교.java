package sol21608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 김유빈_상어_초등학교 {

    private static int n;
    private static int[][] map;
    private static Map<Integer, List<Integer>> students = new HashMap<>();
    private static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static class Seat implements Comparable<Seat> {

        int x;
        int y;
        int preferCount;
        int emptyCount;

        public Seat(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void prefer() {
            preferCount++;
        }

        public void empty() {
            emptyCount++;
        }

        @Override
        public int compareTo(Seat other) {
            // 좋아하는 학생이 많은 칸
            if (this.preferCount != other.preferCount) {
                if (this.preferCount > other.preferCount) {
                    return 1;
                }
                return -1;
            }

            // 비어있는 칸이 가장 많은 칸
            if (this.emptyCount != other.emptyCount) {
                if (this.emptyCount > other.emptyCount) {
                    return 1;
                }
                return -1;
            }

            // 행 비교
            if (this.y != other.y) {
                if (this.y < other.y) {
                    return 1;
                }
                return -1;
            }

            // 열 비교
            if (this.x != other.x) {
                if (this.x < other.x) {
                    return 1;
                }
                return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        // 1. 자리 배치
        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());

            students.put(student, new ArrayList<>());

            // 친구 선호도 조사
            for (int j = 0; j < 4; j++) {
                students.get(student).add(Integer.parseInt(st.nextToken()));
            }

            // 적절한 위치 선택
            Seat seat = null;
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    // 이미 다른 학생이 선택한 경우
                    if (map[y][x] != 0) {
                        continue;
                    }

                    Seat current = new Seat(x, y);

                    for (int[] direction : directions) {
                        int nextX = x + direction[0];
                        int nextY = y + direction[1];

                        if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= n) {
                            continue;
                        }

                        // 인접한 좋아하는 학생의 수 구하기
                        if (students.get(student).contains(map[nextY][nextX])) {
                            current.prefer();
                        }

                        // 인접한 빈 칸의 수 구하기
                        if (map[nextY][nextX] == 0) {
                            current.empty();
                        }
                    }

                    if (seat == null) {
                        seat = current;
                        continue;
                    }

                    if (seat.compareTo(current) < 0) {
                        seat = current;
                    }
                }
            }
            map[seat.y][seat.x] = student;
        }

        // 2. 만족도 구하기
        int result = 0;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                Seat seat = new Seat(x, y);

                for (int[] direction : directions) {
                    int nextX = x + direction[0];
                    int nextY = y + direction[1];

                    if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= n) {
                        continue;
                    }

                    // 인접한 좋아하는 학생의 수 구하기
                    if (students.get(map[y][x]).contains(map[nextY][nextX])) {
                        seat.prefer();
                    }
                }
                if (seat.preferCount == 0) {
                    continue;
                }
                result += (int)Math.pow(10, seat.preferCount - 1);
            }
        }
        System.out.println(result);
    }
}
