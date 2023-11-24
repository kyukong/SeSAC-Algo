package 코테스터디.week3;

import java.io.*;
import java.util.*;

public class 이상민_타임머신 { // https://www.acmicpc.net/problem/11657

    static class Edge{
        int start;
        int end;
        int time;
        public Edge(int start, int end, int time){
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 도시의 개수
        int m = Integer.parseInt(st.nextToken()); // 버스 노선의 개수

        ArrayList<Edge> list = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.add(new Edge(a, b, c));
        }

        long[] result = new long[n+1];

        Arrays.fill(result, Long.MAX_VALUE);
        result[1] = 0;
        for(int i=1; i<=n; i++){

            for(Edge edge : list){

                // 한번도 방문한 적 없으면 패스
                if(result[edge.start] == Long.MAX_VALUE) continue;

                // 버스 도착점까지의 최소거리가 버스시작 + 시간 보다 크면 갱신
                if(result[edge.end] > result[edge.start] + edge.time){
                    result[edge.end] = result[edge.start] + edge.time;

                    // n 번째 작업에서 값이 변경되면 무한히 되돌아 갈 수 있다는 뜻임
                    if(i == n){
                        System.out.println(-1);
                        System.exit(0);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=2; i<=n; i++){
            if(result[i] == Long.MAX_VALUE){
                sb.append(-1+"\n");
            }else{
                sb.append(result[i]+"\n");
            }
        }
        System.out.println(sb);
    }
}
