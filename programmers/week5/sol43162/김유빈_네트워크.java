package week5.sol43162;

import java.util.*;

class 김유빈_네트워크 {

    private int[] area;
    private Map<Integer, List<Integer>> connections = new HashMap<>();

    public int solution(int n, int[][] computers) {

        area = new int[n];
        List<Integer> connection;
        for (int i = 0; i < n; i++) {

            area[i] = i;

            connection = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (i == j || computers[i][j] == 0) {
                    continue;
                }
                connection.add(j);
            }
            connections.put(i, connection);
        }

        int com, parent1, parent2;
        for (Map.Entry<Integer, List<Integer>> now : connections.entrySet()) {
            com = now.getKey();

            for (int other : now.getValue()) {
                parent1 = find(com);
                parent2 = find(other);

                if (parent1 == parent2) {
                    continue;
                }

                for (int i = 0; i < n; i++) {
                    if (area[i] == parent2) {
                        area[i] = parent1;
                    }
                }
            }
        }

        return Arrays.stream(area).distinct().toArray().length;
    }

    private int find(int value) {
        if (value == area[value]) {
            return value;
        }
        return area[value] = find(area[value]);
    }
}
