package sol21608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Student {
    public int studId;
    public int[] favStud;
    Student(int studId, int[] favStud) {
        this.studId = studId;
        this.favStud = favStud;
    }
}

class Seat {
    public int row;
    public int col;

    Seat(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class 엄상은_상어_초등학교 {
    public static int[][] seat;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());

        Student[] slist = new Student[n*n];
        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(bf.readLine());
            int newStudId = Integer.parseInt(st.nextToken());
            int[] newFavStud = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            slist[i] = new Student(newStudId, newFavStud);
        }

        seat = new int [n][n];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        // 인접칸에 좋아하는 학생 가장 많은 칸
        // 인접칸에 빈 거 가장 많은 칸
        // 가장 앞쪽 칸
        int maxFav;
        int maxEmpty;
        int fav;
        int empty;
        Seat suitableSeat;
        for (int i = 0; i < n * n; i++){
            maxFav = -1;
            maxEmpty = -1;
            suitableSeat = new Seat(0, 0);
            // 학생 i가 위치 (r, c)일 때
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++){
                    if (seat[r][c] == 0) {
                        fav = 0;
                        empty = 0;
                        for (int m = 0; m < 4; m++){
                            int neighborX = r + dx[m];
                            int neighborY = c + dy[m];
                            if ((0 <= neighborX) && (neighborX < n) && (0 <= neighborY) && (neighborY < n)) {
                                for (int favS: slist[i].favStud) {
                                    if (favS == seat[neighborX][neighborY]) {
                                        fav++;
                                    }
                                }
                                if (seat[neighborX][neighborY] == 0) {
                                    empty++;
                                }
                            }
                        }
                        if (fav > maxFav) {
                            maxFav = fav;
                            suitableSeat = new Seat(r, c);
                            maxEmpty = empty;
                        }
                        else if (fav == maxFav) {
                            if (empty > maxEmpty) {
                                maxEmpty = empty;
                                suitableSeat = new Seat(r, c);
                            }
                        }
                    }
                }
            }
            seat[suitableSeat.row][suitableSeat.col] = slist[i].studId;
        }

        // 만족도 구하기
        int sum = 0;
        for (int i = 0; i < n * n; i++) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (slist[i].studId == seat[r][c]) {
                        int count = 0;
                        for (int m = 0; m < 4; m++){
                            int neighborX = r + dx[m];
                            int neighborY = c + dy[m];
                            if ((0 <= neighborX) && (neighborX < n) && (0 <= neighborY) && (neighborY < n)) {
                                for (int favS: slist[i].favStud) {
                                    if (favS == seat[neighborX][neighborY]) {
                                        count++;
                                    }
                                }
                            }
                        }
                        if (count == 1) {
                            sum += 1;
                        }
                        else if (count == 2) {
                            sum += 10;
                        }
                        else if (count == 3) {
                            sum += 100;
                        }
                        else if (count == 4) {
                            sum += 1000;
                        }
                    }
                }
            }
        }
        System.out.println(sum);
    }
}
