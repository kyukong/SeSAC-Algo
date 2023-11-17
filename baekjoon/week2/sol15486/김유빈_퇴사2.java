package sol15486;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김유빈_퇴사2 {

    private static int n;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        dp = new int[n + 1];

        int time, profit;
        int max = -1;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            time = Integer.parseInt(st.nextToken());
            profit = Integer.parseInt(st.nextToken());

            if (max < dp[i]) {
                max = dp[i];
            }

            if (i + time > n) {
                continue;
            }

            dp[i + time] = Math.max(dp[i + time], max + profit);
        }
        System.out.println(Math.max(max, dp[n]));
    }
}
