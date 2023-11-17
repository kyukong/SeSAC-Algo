package sol1890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김유빈_점프 {

    private static int n;
    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        dp = new long[n][n];
        dp[0][0] = 1;

        // 배열 초기화
        int jump;
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                jump = Integer.parseInt(st.nextToken());

                // 이동할 수 없는 경우 종료
                if (dp[y][x] == 0 || jump == 0) {
                    continue;
                }

                // 오른쪽 이동
                if (x + jump < n) {
                    dp[y][x + jump] += dp[y][x];
                }

                // 아래 이동
                if (y + jump < n) {
                    dp[y + jump][x] += dp[y][x];
                }
            }
        }
        System.out.println(dp[n - 1][n - 1]);
    }
}
