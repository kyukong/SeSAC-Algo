package week5.sol42883;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class 김유빈_큰수만들기 {

    public String solution(String number, int k) {

        List<Integer> results = new ArrayList<>();
        List<String> numbers = new ArrayList<>();
        int length = number.length() - k;
        int count = k;

        // 주어진 문자열을 쉽게 가져올 수 있도록 리스트로 변환
        for (char num : number.toCharArray()) {
            numbers.add(String.valueOf(num));
        }

        int now, before, last;
        for (int i = 0; i < number.length(); i++) {
            now = Integer.parseInt(numbers.get(i));

            if (results.isEmpty()) {
                results.add(now);
                continue;
            }

            while (!results.isEmpty()) {
                last = results.size() - 1;
                before = results.get(last);
                if (count > 0 && before < now) {
                    results.remove(last);
                    count--;
                } else {
                    break;
                }
            }
            if (results.size() < length) {
                results.add(now);
            }
        }
        return results.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
