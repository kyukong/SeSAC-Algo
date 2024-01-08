import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = -1;
        
        int len = triangle.length;
        for(int i=1;i<len ;i++){
            for(int j=0;j<i+1;j++){
                if(j==0){
                    triangle[i][j]+=triangle[i-1][j];
                }
                else if(i==j){
                    triangle[i][j]+=triangle[i-1][j-1];
                }
                else
                    triangle[i][j]+=Math.max(triangle[i-1][j-1],triangle[i-1][j]);
            }
        }
        // for(int i=0;i<len;i++){
        //     System.out.println(Arrays.toString(triangle[i]));
        // }
        for(int a: triangle[len-1]){
            if(a>answer){
                answer= a;
            }
        }
        return answer;
    }
}
