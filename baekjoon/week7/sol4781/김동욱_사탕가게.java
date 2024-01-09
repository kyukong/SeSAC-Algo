package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김동욱_사탕가게 {
    static StringBuilder sb;
    static int[] weights;
    static int[] profits;
    static int N, M;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = (int) (Double.parseDouble(st.nextToken()) * 100+0.5); 
            if (N == 0 && M == 0)
                break;
            weights = new int[N + 1];
            profits = new int[N + 1];
            dp = new int[M + 1]; 
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                profits[i] = Integer.parseInt(st.nextToken());
                weights[i] = (int) (Double.parseDouble(st.nextToken()) * 100+0.5); 
            }
            for (int i = 1; i <= N; i++) {
                for (int w = weights[i]; w <= M; w++) {
                    dp[w] = Math.max(dp[w], dp[w - weights[i]] + profits[i]);
                }
            }
            sb.append(dp[M] + "\n");
        }
        System.out.println(sb);
    }
}
