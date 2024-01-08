import java.util.*;
import java.io.*;
import java.lang.*;
class Solution {
    //static boolean[] visited;
    static String targetString;
    static String beginString;
    static String[] wArray;
    static int answer;
    public int solution(String begin, String target, String[] words) {
        targetString = target;
        beginString = begin;
        wArray = words;
        
       boolean[] visited= new boolean[wArray.length];
        boolean isWord = false;
        for(String word: wArray){
            if(word.equals(targetString)) {
                isWord= true;
                break;
            }
        }
        if(!isWord) return 0;
        answer =Integer.MAX_VALUE;
        dfs(beginString,0, visited);
        return answer;
        
    }
    public static void dfs(String curString, int count, boolean[] visited){
        if(curString.equals(targetString)){
            answer = Math.min(answer, count);
            return;
        }
        for(int i =0;i<wArray.length;i++){
            if(visited[i]) continue;
            String curWord = wArray[i];
            int diff =0;
            for(int s = 0;s<curWord.length();s++){
                if(curWord.charAt(s)!= curString.charAt(s)){
                    diff++;
                }
            }
             if(diff==1){
                 visited[i]= true;
               // System.out.println("new dfs curStirng -> new String "+  curString+" "+ curWord + " : "+ count);
                dfs(curWord, count+1, visited);
                 visited[i]=false;
             }
        }
    }
}
