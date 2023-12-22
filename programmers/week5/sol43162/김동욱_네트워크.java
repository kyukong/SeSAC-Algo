import java.util.*;
class Solution {
    static boolean[] visited; // 0 번 부터 시작
    static int[][] cArray;
    static int N;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n]; 
        cArray = computers;
        N= n;
        for(int i=0;i<n;i++){
            
                if(!visited[i]){
                // System.out.println(Arrays.toString(visited));
                dfs(i);
                answer++;
                }
            
        }
       // System.out.println(answer);
        return answer;
    }
    public static void dfs(int start){
    visited[start]= true;
    for(int i=0;i<N;i++){
        if(i==start || visited[i]) continue;
        if(cArray[start][i]==1){
            dfs(i);
        }
    }
    }
}
