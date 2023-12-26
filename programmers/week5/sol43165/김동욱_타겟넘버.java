import java.io.*;
import java.util.*;
import java.lang.*;
class Solution {
    static int[] array;
    static int tarNum;
    static int answer;
    static int N;
    public int solution(int[] numbers, int target) {
        answer = 0;
        array =numbers;
        tarNum = target;
        
        N = numbers.length;
        dfs(0,0);
        
        return answer;
    }
    
    public static void dfs(int ind, int curNum){
       if ( ind >= N){
           if( tarNum == curNum){
               answer++;
           }
           return;
       }
    
        dfs(ind+1, array[ind] + curNum);
        dfs(ind+1, (-1 * array[ind]) + curNum);
        
    }
}
