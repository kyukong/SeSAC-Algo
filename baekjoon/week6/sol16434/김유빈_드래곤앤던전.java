package week6.sol16434;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김유빈_드래곤앤던전 {

    private static int n;
    private static long attack;
    private static long[][] rooms2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        attack = Long.parseLong(st.nextToken());
        rooms2 = new long[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                rooms2[i][j] = Long.parseLong(st.nextToken());
            }
        }

        boolean win;
        long start = 1L, end = (long) 9e18, hp;
        while (start <= end) {
            hp = (start + end) / 2;
            win = fight(hp, attack);
            if (win) {
                end = hp - 1;
            } else {
                start = hp + 1;
            }
        }
        System.out.println(start);
    }

    private static boolean fight(long hp, long attack) {
        long[] room;
        long a, h;
        long hpMax = hp;
        for (int i = 0; i < rooms2.length; i++) {
            room = rooms2[i];
            a = room[1];
            h = room[2];

            if (room[0] == 1) {
                long count = h / attack;
                if (h % attack == 0L) {
                    hp -= a * (count - 1);
                } else {
                    hp -= a * count;
                }
            } else {
                attack += a;
                hp += h;
                if (hpMax < hp) {
                    hp = hpMax;
                }
            }

            if (hp <= 0) {
                return false;
            }
        }
        return true;
    }
}
