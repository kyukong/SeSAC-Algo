package week5.sol42842;

public class 이상민_카펫 {
    public int[] solution(int brown, int yellow) {

        int row = 0;
        int col = 0;

        for(int i=1; i<=yellow; i++){

            if(yellow%i !=0) continue;

            int num = yellow/i;
            int needBrown = (num*2) + (i*2) + 4;

            if(needBrown == brown){
                row = num + 2;
                col = i + 2;

                if(row >= col){
                    break;
                }
            }
        }

        int[] answer = {row, col};
        return answer;
    }
}
