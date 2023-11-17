package sol12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 김유빈_평범한배낭 {

    private static int n;
    private static int max;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        max = Integer.parseInt(st.nextToken());
        dp = new int[n + 1][max + 1];

        int weight, value;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            weight = Integer.parseInt(st.nextToken());
            value = Integer.parseInt(st.nextToken());

            for (int w = 1; w <= max; w++) {
                if (w >= weight) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weight] + value);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println(dp[n][max]);
    }
}
