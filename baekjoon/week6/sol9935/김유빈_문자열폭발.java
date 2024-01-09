package week6.sol9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 김유빈_문자열폭발 {

    private static final Stack<Character> stack = new Stack<>();

    private static String string;
    private static String explosion;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        string = br.readLine();
        explosion = br.readLine();

        // 문자열 순회하여 폭발 문자열 찾기
        for (int i = 0; i < string.length(); i++) {
            stack.push(string.charAt(i));

            if (stack.size() < explosion.length()) {
                continue;
            }

            int index;
            boolean contains = true;
            for (int j = 0; j < explosion.length(); j++) {
                index = stack.size() - explosion.length() + j;
                if (explosion.charAt(j) != stack.get(index)) {
                    contains = false;
                    break;
                }
            }
            if (!contains) {
                continue;
            }

            for (int j = 0; j < explosion.length(); j++) {
                stack.pop();
            }
        }

        // stack 에 남아있는 문자열 출력
        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < stack.size(); i++) {
                sb.append(stack.get(i));
            }
            System.out.println(sb);
        }
    }
}
