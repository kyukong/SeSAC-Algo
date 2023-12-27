package week5.sol43162;

import java.io.*;
import java.util.*;

public class 이상민_네트워크 {

    static ArrayList<Integer>[] adj;
    static boolean[] check;
    static int answer = 0;

    public int solution(int n, int[][] computers) {

        adj = new ArrayList[n+1];
        check = new boolean[n+1];
        for(int i=0; i<=n; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i != j && computers[i][j] == 1){
                    adj[i+1].add(j+1);
                }
            }
        }

        for(int i=1; i<=n; i++){
            if(!check[i]){
                answer++;
                dfs(i);
            }
        }

        return answer;
    }

    static void dfs(int i){
        if(check[i]) return;
        check[i] = true;

        for(Integer temp : adj[i]){
            if(!check[temp]){
                dfs(temp);
            }
        }
    }
}
