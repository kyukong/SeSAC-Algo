package week5.sol42883;

public class 이상민_큰수만들기 {

    public String solution(String number, int k) {

        StringBuilder sb = new StringBuilder();

        int len = number.length() - k;
        char[] str = number.toCharArray();

        int index = 0;
        for (int i = 0; i < len; i++) {
            int max = 0;
            for (int j = index; j <= k + i; j++) { // 해당 자리에 값이 위치할 수 있는 최대 :: k + i
                if (max < str[j] - '0') {
                    max = str[j] - '0';
                    index = j + 1;
                }
            }
            sb.append(max);
        }
        String answer = String.valueOf(sb);
        return answer;
    }
}
