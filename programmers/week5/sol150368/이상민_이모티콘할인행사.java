import java.util.*;

public class 이상민_이모티콘할인행사 {
    public int[] solution(int[][] users, int[] emoticons) {

        // 이모티콘 할인율 비율 구하기
        combi = new int[emoticons.length];
        emoticonCombi(users, emoticons, 0);

        Result ans = pq.poll();
        int[] answer = {ans.count, ans.sumMoney};
        return answer; // 이모티콘 플러스 서비스 가입수, 이모티콘 매출액
    }

    static int[] discount = {10,20,30,40};
    static int[] combi;
    static PriorityQueue<Result> pq = new PriorityQueue<>();

    static void emoticonCombi(int[][] users, int[] emoticons, int index){

        if(index == emoticons.length){

            // 정해진 할인률에 따라 이모티콘을 구매하는지, 이모티콘 플러스에 가입하는지 판별
            int count = 0;  // 이모티콘 플러스에 가입한 인원
            int sumMoney = 0;

            for(int i=0; i<users.length; i++){
                int limit = users[i][0];
                int money = users[i][1];

                int b = 0;
                for(int j=0; j<index; j++){
                    if(discount[combi[j]] >= limit){ // 자신의 기준 이상 할인할 때 구매 진행
                        int cal = emoticons[j] * (100 - discount[combi[j]]) / 100;

                        if(money - cal > 0){
                            b += cal;
                            money -= cal;
                            sumMoney += cal;
                        }else{
                            count++;
                            sumMoney -= b;
                            break;
                        }
                    }
                }
            }

            if(count == 0 && sumMoney == 0) return;
            pq.add(new Result(count, sumMoney));
            // System.out.println(count +" : "+sumMoney);
            return;
        }

        for(int i=0; i<=3; i++){
            combi[index] = i;
            emoticonCombi(users, emoticons, index+1);
        }
    }

    static class Result implements Comparable<Result>{

        int count, sumMoney;

        public Result(int count, int sumMoney){
            this.count = count;
            this.sumMoney = sumMoney;
        }

        @Override
        public int compareTo(Result r){

            if(this.count > r.count){
                return -1;
            }else if(this.count == r.count){
                return r.sumMoney - this.sumMoney;
            }else{
                return 1;
            }
        }
    }
}
