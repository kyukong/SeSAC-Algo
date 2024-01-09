package week7.sol20529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class 김유빈_가장가까운세사람의심리적거리 {

    private static final List<String> allMbtis = new ArrayList<>();
    private static final Map<People, Integer> allDistances = new HashMap<>();

    private static int t;
    private static int n;
    private static List<String> mbtis;
    private static Map<String, Integer> counts;
    private static List<List<String>> peoples;
    private static int[] visited;

    static class People {

        String mbti1;
        String mbti2;

        public People(final String mbti1, final String mbti2) {
            this.mbti1 = mbti1;
            this.mbti2 = mbti2;
        }

        @Override
        public boolean equals(final Object o) {
            People people = (People)o;
            return (Objects.equals(mbti1, people.mbti1) && Objects.equals(mbti2, people.mbti2)) ||
                (Objects.equals(mbti1, people.mbti2) && Objects.equals(mbti2, people.mbti1));
        }

        @Override
        public int hashCode() {
            return Objects.hash(mbti1, mbti2);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        setAllMbtis();

        // 모든 MBTI 사이의 두 사람 거리 구하기
        for (String mbti1 : allMbtis) {
            for (String mbti2 : allMbtis) {
                allDistances.put(new People(mbti1, mbti2), getDistance(mbti1, mbti2));
            }
        }

        for (int j = 0; j < t; j++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            // 입력
            st = new StringTokenizer(br.readLine());
            mbtis = new ArrayList<>();
            counts = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String input = st.nextToken();
                int value = counts.getOrDefault(input, 0);
                if (value > 3) {
                    continue;
                }
                mbtis.add(input);
                counts.put(input, ++value);
            }
            n = mbtis.size();

            // 3개의 모든 조합 찾기
            peoples = new ArrayList<>();
            visited = new int[n];
            combination(new ArrayList<>(), 3);

            // 세 사람 사이의 최소 거리 찾기
            int result = Integer.MAX_VALUE, sum;
            for (List<String> people : peoples) {
                sum = 0;
                sum += allDistances.get(new People(people.get(0), people.get(1)));
                sum += allDistances.get(new People(people.get(1), people.get(2)));
                sum += allDistances.get(new People(people.get(0), people.get(2)));

                result = Math.min(result, sum);
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static void setAllMbtis() {
        String[] string = "ISTJ, ISFJ, INFJ, INTJ, ISTP, ISFP, INFP, INTP, ESTP, ESFP, ENFP, ENTP, ESTJ, ESFJ, ENFJ, ENTJ".split(", ");
        for (int i = 0; i < 16; i++) {
            allMbtis.add(string[i]);
        }
    }

    private static int getDistance(String mbti1, String mbti2) {
        int distance = 0;
        if (mbti1.equals(mbti2)) {
            return distance;
        }
        for (int i = 0; i < 4; i++) {
            if (mbti1.charAt(i) != mbti2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    private static void combination(List<String> arr, int r) {
        if (r == 0) {
            peoples.add(new ArrayList<>(arr));
            return;
        }

        for (int i = 0; i < mbtis.size(); i++) {
            if (visited[i] == 1) {
                continue;
            }
            arr.add(mbtis.get(i));
            visited[i] = 1;

            combination(arr, r - 1);

            arr.remove(arr.size() - 1);
            visited[i] = 0;
        }
    }
}
