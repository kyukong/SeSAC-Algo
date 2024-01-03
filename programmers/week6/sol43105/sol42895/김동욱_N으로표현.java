import java.io.*;
import java.lang.*;
import java.util.*;
class Solution {
    public int solution(int N, int number) {
        int answer =0;
        HashSet[] d = new HashSet[11];
        for(int i=0;i<11;i++){
            d[i] = new HashSet<Integer>();
        }
        d[1].add(N);
        if(number == N){
            return 1;
        }
        for(int i=2;i<9;i++){
            
            d[i].add(getNum(i,N));
            for(int k=1;k<i;k++){
                HashSet<Integer> h1 = d[k];
                HashSet<Integer> h2 = d[i-k];
                for(int a : h1){
                    for(int b: h2){
                        
                        d[i].add(a+b);
                        d[i].add(a-b);
                        if(a ==0 || b ==0) continue;
                        d[i].add(a*b);
                        d[i].add(a/b);
                    }
                }

            }
            HashSet<Integer> curHash = d[i];
            for(int r : curHash){
                if(r== number){
                    return i;
                }
            }
        }
       // System.out.println(Arrays.toString(d));
        return -1;
    }
    public static int getNum(int idx, int num){
        String temp = "";
        for(int i=0;i<idx;i++){
            temp+="1";
        }
        int r= Integer.valueOf(temp)*num;
        
        return r;
    }
}
