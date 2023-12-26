import java.util.*;
import java.io.*;
import java.lang.*;
class Solution {
    
    public static class Goal implements Comparable<Goal>{
        int users;
        int price;
        public Goal(){}
        public Goal(int users, int price){
            this.users= users;
            this.price= price;
        }
        @Override
        public String toString(){
            return "[ "+this.users+","+this.price+" ]";
        }
        @Override
        public int compareTo(Goal o){
            if(o.users == this.users){
                return o.price- this.price;
            }
            return o.users- this.users;
        }
    }
    static int[] emoticonSaleRates;
    static int[] emoticonArray;
    static int count;
    static int[][] userArray;
    static PriorityQueue<Goal> pq;
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        emoticonArray = emoticons;
        userArray = users;
        count =0;
        pq = new PriorityQueue<>();
        emoticonSaleRates = new int[emoticons.length];
        dfs(0);
        //System.out.println(count);
        Goal goal = pq.poll();
        answer[0]= goal.users;
        answer[1]=goal.price;
        return answer;
    }
    public static void dfs(int idx){
        if(idx>= emoticonArray.length){
            // 시뮬레이션 시작
           // System.out.println(Arrays.toString(emoticonSaleRates));
            int emojiPlus = 0;
            int totalProfit = 0;
            for(int[] u : userArray){
                int money =u[1];
                int emojiMoney = 0;
                int userRate = u[0];
                for(int i=0;i<emoticonSaleRates.length;i++){
                    if(emoticonSaleRates[i]>=userRate){
                       // System.out.println(1.0- (emoticonSaleRates[i] *0.01));
                        emojiMoney += emoticonArray[i] * (1.0 - (emoticonSaleRates[i]*0.01));
                    }
                }
                if(emojiMoney >= money){
                    emojiPlus++;
                    continue;
                }
                totalProfit += emojiMoney;
            }
            pq.add(new Goal(emojiPlus,totalProfit));
            //count++;
            return;
        }
        emoticonSaleRates[idx]= 10;
        dfs(idx+1);
        emoticonSaleRates[idx]= 20;
        dfs(idx+1);
        emoticonSaleRates[idx]= 30;
        dfs(idx+1);
        emoticonSaleRates[idx]= 40;
        dfs(idx+1);
        
        return;
    }
}
