package week5.sol1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 김유빈_리모컨 {

    private static int N;
    private static int M;
    private static List<String> buttons;

    private static int result1; // +-
    private static int result2 = Integer.MAX_VALUE; // 번호

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        buttons = new ArrayList<>();
        if (M != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                String s = st.nextToken();
                buttons.add(s);
            }
        }

        // +- 버튼으로만 이동하는 경우
        result1 = Math.abs(N - 100);

        // 번호로 이동하는 경우
        // - 방향으로 이동
        int num;
        boolean isBroken;
        for (int i = 0; i <= N; i++) {
            num = N - i;
            isBroken = false;
            for (String button : buttons) {
                if (String.valueOf(num).contains(button)) {
                    isBroken = true;
                    break;
                }
            }

            if (isBroken) {
                continue;
            }

            int length = String.valueOf(num).length();
            result2 = Math.min(result2, Math.abs(N - num) + length);
            break;
        }

        // + 방향으로 이동
        for (int i = 0; i <= Math.pow(10, String.valueOf(N).length()) - 1; i++) {
            num = N + i;
            isBroken = false;
            for (String button : buttons) {
                if (String.valueOf(num).contains(button)) {
                    isBroken = true;
                    break;
                }
            }

            if (isBroken) {
                continue;
            }

            int length = String.valueOf(num).length();
            result2 = Math.min(result2, Math.abs(N - num) + length);
            break;
        }

        System.out.println(Math.min(result1, result2));
    }
}
