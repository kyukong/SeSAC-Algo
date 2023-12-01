package com.example.java.baekjoon.week4.sol1916;

import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int m;
    private static int[] visited;
    private static int[] result;
    private static Map<Integer, List<int[]>> arr = new HashMap<>(); // 배열 : 도착 노드, 가중치 (양수)
    private static int departure;
    private static int arrival;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        visited = new int[n + 1];
        result = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr.put(i, new ArrayList<>());
            result[i] = Integer.MAX_VALUE;
        }

        // 인접리스트 만들기
        StringTokenizer st;
        int start, end, weight;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            arr.get(start).add(new int[] {end, weight});
        }

        st = new StringTokenizer(br.readLine());
        departure = Integer.parseInt(st.nextToken());
        arrival = Integer.parseInt(st.nextToken());

        // 다익스트라
        int min, minIndex = 0;
        result[departure] = 0;
        for (int i = 0; i < n; i++) {
            // 최소 노드 찾기
            min = Integer.MAX_VALUE;
            for (int j = 1; j <= n; j++) {
                if (visited[j] == 1) {
                    continue;
                }
                if (result[j] < min) {
                    min = result[j];
                    minIndex = j;
                }
            }

            // 현재를 기준으로 result 업데이트 및 visited 관리
            List<int[]> nodes = arr.get(minIndex);
            for (int[] node : nodes) {
                if (result[minIndex] + node[1] < result[node[0]]) {
                    result[node[0]] = result[minIndex] + node[1];
                }
            }
            visited[minIndex] = 1;
        }

        System.out.println(result[arrival]);
    }
}
