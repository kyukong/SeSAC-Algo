package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 교실 - N * N
 * 학생 수 - N^2
 * (1,1) ~ (N,N)
 * 가장 좋아하는 학생 4명 고르기
 * - 한 칸에 학생 한 명
 *
 * 1. 비어있는 칸 중에서 좋아하는 학생이 가장 많은 칸으로 자리 정함
 * 2. 1을 만족 -> 비어있는 칸이 가장 많은 칸으로 자리 정함
 * 3. 2 만족하는 여러개 칸 -> 행의 번호가 가장 작은 칸 -> 열의 번호가 가장 작은 칸
 *
 * N = 3,  학생 수 = 9
 *
 * 츌력 : 학생의 만족도
 * */
public class 이상민_상어초등학교 { // https://www.acmicpc.net/problem/21608

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int N;
    static int[][] result;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        // 학생의 번호와 그 학생이 좋아하는 학생 4명의 번호가 주어짐

        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        result = new int[N][N];
        check = new boolean[N*N + 1];

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int me = Integer.parseInt(st.nextToken());
            Set<Integer> friends = new TreeSet<>();
            for (int j = 0; j < 4; j++) {
                friends.add(Integer.parseInt(st.nextToken()));
            }
            map.put(me, friends);
            SortStudent(me, friends); // 학생 자리 배정하기
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(result[i][j] +" ");
            }
            System.out.println();
        }

        // 학생의 만족도 구하기
        int ans = 0;
        for(int x=0; x<N; x++){
            for(int y=0; y<N; y++){
                int student = result[x][y];
                int count = 0;

                Set<Integer> friends = map.get(student);
                for (int h = 0; h < 4; h++) {
                    int getX = x + dx[h];
                    int getY = y + dy[h];
                    if (0 <= getX && getX < N && 0 <= getY && getY < N) {
                        int num = result[getX][getY];
                        if(friends.contains(num)) count++;
                    }
                }

                if(count == 0) ans += 0;
                else if(count == 1) ans += 1;
                else if(count == 2) ans += 10;
                else if(count == 3) ans += 100;
                else ans += 1000;

            }
        }

        System.out.println(ans);
    }

    public static void SortStudent(int student, Set<Integer> friends){
        List<Node> condition = new ArrayList<>();
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {

                if(result[x][y] == 0){
                    int nearStudent = 0; // 인접 학생 수
                    int nearEmpty = 0; // 비어있는 칸이 가장 많은

                    // 비어있는 칸 중에서 좋아하는 학생이 인접한 칸의 수 구하기
                    for(int like : friends) { // 좋아하는 학생
                        if(check[like]){ // 좋아하는 학생의 자리가 이미 배정이 완료된 상태라면
                            for (int h = 0; h < 4; h++) { // 현재 x,y의 상/하/좌/우 에 좋아하는 학생이 존재하는지 탐색
                                int getX = x + dx[h];
                                int getY = y + dy[h];
                                if (0 <= getX && getX < N && 0 <= getY && getY < N) {
                                    if (result[getX][getY] == like) { // 빈칸일 때
                                        nearStudent++;
                                    }
                                }
                            }

                        }
                    }

                    // x, y 일때 인접 칸이 비어있을 경우
                    for (int h = 0; h < 4; h++) { // 상, 하, 좌, 우 탐색해 temp가 들어갈 수 있는 위치 찾기
                        int getX = x + dx[h];
                        int getY = y + dy[h];
                        if (0 <= getX && getX < N && 0 <= getY && getY < N) {
                            if (result[getX][getY] == 0) { // 빈칸일 때
                                nearEmpty++;
                            }
                        }
                    }
                    condition.add(new Node(x, y, nearStudent, nearEmpty));
                }

            }
        }

        // 정렬 : nearStudent -> nearEmpty -> x -> y

        Collections.sort(condition, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) { // node1 - node2 < 0 --> 오름차순
                // nearStudent 비교
                if (node1.nearStudent != node2.nearStudent) return node2.nearStudent - node1.nearStudent;

                if (node1.nearEmpty != node2.nearEmpty) return node2.nearEmpty - node1.nearEmpty;

                if (node1.x != node2.x) return node1.x - node2.x;

                return node1.y - node2.y;
            }
        });

        int x = condition.get(0).getX();
        int y = condition.get(0).getY();
        result[x][y] = student;
        check[student] = true;
    }

    // 정렬 수 : nearStudent -> nearEmpty -> x -> y
    public static class Node {
        int x;
        int y;
        int nearStudent; // 인접 학생 수
        int nearEmpty; // 인접한 칸 중에서 비어있는 칸이 가장 많은 칸

        public Node(int x, int y, int nearStudent, int nearEmpty){
            this.x = x;
            this.y = y;
            this.nearStudent = nearStudent;
            this.nearEmpty = nearEmpty;
        }

        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    }
}
