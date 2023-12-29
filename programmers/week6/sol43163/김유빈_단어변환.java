package week6.sol43163;

import java.util.*;

class 김유빈_단어변환 {

    private int result = Integer.MAX_VALUE;
    private String[] words;
    private String target;

    public int solution(String begin, String target, String[] words) {
        this.words = words;
        this.target = target;

        dfs(begin, new int[words.length], 0);

        if (result == Integer.MAX_VALUE) {
            return 0;
        }
        return result;
    }

    private void dfs(String word, int[] visited, int count) {
        if (word.equals(target)) {
            result = Math.min(result, count);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (visited[i] == 1) {
                continue;
            }

            // 문자가 하나만 다를 경우 선택
            int diff = 0;
            for (int j = 0; j < words[i].length(); j++) {
                if (word.charAt(j) == words[i].charAt(j)) {
                    continue;
                }
                diff += 1;
            }
            if (diff != 1) {
                continue;
            }

            visited[i] = 1;
            dfs(words[i], visited, count + 1);
            visited[i] = 0;
        }
    }
}
