package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 김동욱_톱니바퀴(2) {
    static int T, K;
    static ArrayList<Integer>[] gears;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        gears = new ArrayList[T];
        for (int i = 0; i < T; i++) {
            gears[i] = new ArrayList<>();
            String line = br.readLine();
            for (char c : line.toCharArray()) {
                gears[i].add(c - '0');
            }
        }

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int gearIndex = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            visited = new boolean[T];
            spin(gearIndex, direction);
        }

        int result = 0;
        for (ArrayList<Integer> gear : gears) {
            if (gear.get(0) == 1) {
                result++;
            }
        }

        System.out.println(result);
    }

    private static void spin(int gearIndex, int dir) {
        visited[gearIndex] = true;
        int leftGear = gearIndex - 1;
        int rightGear = gearIndex + 1;

        if (leftGear >= 0 && !visited[leftGear] && gears[gearIndex].get(6) != gears[leftGear].get(2)) {
        	spin(leftGear, -dir);
        }

        if (rightGear < T && !visited[rightGear] && gears[gearIndex].get(2) != gears[rightGear].get(6)) {
        	spin(rightGear, -dir);
        }

        if (dir == 1) {
            Integer removed = gears[gearIndex].remove(gears[gearIndex].size() - 1);
            gears[gearIndex].add(0, removed);
        } else {
            Integer removed = gears[gearIndex].remove(0);
            gears[gearIndex].add(removed);
        }
    }
}