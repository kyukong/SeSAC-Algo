import java.util.*;
import java.io.*;
import java.lang.*;


class Solution {
    static int answer;
    static int[] selected;
    static int[] num;
    static boolean[] visited;
    
    static int[][] dArray;
    static int N;
    static int tiredNess;
    public int solution(int k, int[][] dungeons) {
    answer= 0;
    tiredNess= k;
    N = dungeons.length;
    dArray = dungeons;
    visited = new boolean[N];
    selected = new int[N];
    num = new int[N];
    for(int i=0;i<N;i++){
        num[i]=i;
    }
   // System.out.println(Arrays.toString(num));
    perm(0);    
    return answer;
    }
    public static void perm(int idx){
       if( idx == N){
           System.out.println(Arrays.toString(selected));
           ArrayList<Integer> arrayList = new ArrayList<>();
           for(int i : selected){
               arrayList.add(i);
           }
          // 로직 수행
          simulation(arrayList);
           return;
       } 
       for(int i =0;i<N;i++){
           if(visited[i]) continue;
           selected[idx]= num[i];
           visited[i]= true;
           perm(idx+1);
           visited[i]=false;
       }
        
    }
    public static void simulation(ArrayList<Integer> arrayList){
        
        int startTiredNess = tiredNess;
        int tempAns =0;
        for(int i : arrayList){
            if(dArray[i][0] > startTiredNess) continue;
            startTiredNess-=dArray[i][1];
            tempAns++;
        }
        answer = Math.max(answer,tempAns);
    }
}
