package week5.sol150368;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class 김유빈_이모티콘할인행사 {

    private static final int[] discountRates = {10, 20, 30, 40};

    private List<List<Integer>> discounts;
    private Queue<Result> results;

    public static void main(String[] args) {
        김유빈_이모티콘할인행사 김유빈이모티콘할인행사 = new 김유빈_이모티콘할인행사();

        int[] result1 = 김유빈이모티콘할인행사.solution(new int[][] {{40, 10000}, {25, 10000}}, new int[] {7000, 9000});
        System.out.println(Arrays.toString(result1));
        System.out.println(Arrays.equals(result1, new int[] {1, 5400}));

        int[] result2 = 김유빈이모티콘할인행사.solution(new int[][] {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}}, new int[] {1300, 1500, 1600, 4900});
        System.out.println(Arrays.toString(result2));
        System.out.println(Arrays.equals(result2, new int[] {4, 13860}));
    }

    static class Result implements Comparable<Result> {

        int plus;
        int profit;

        public Result(int plus, int profit) {
            this.plus = plus;
            this.profit = profit;
        }

        @Override
        public int compareTo(Result other) {
            if (plus != other.plus) {
                return other.plus - plus;
            }
            return other.profit - profit;
        }
    }

    public int[] solution(int[][] users, int[] emoticons) {

        discounts = new ArrayList<>();
        results = new PriorityQueue<>();

        combination(new ArrayList<>(), emoticons.length);

        int limit, minimum, price, total, plus, profit;
        for (List<Integer> discount : discounts) {
            plus = 0;
            profit = 0;
            for (int[] user : users) {
                limit = user[0];
                minimum = user[1];
                total = 0;
                for (int j = 0; j < emoticons.length; j++) {
                    if (limit > discount.get(j)) {
                        continue;
                    }
                    price = emoticons[j] * (100 - discount.get(j)) / 100;
                    total += price;
                }

                if (total >= minimum) {
                    plus += 1;
                } else {
                    profit += total;
                }
            }
            results.add(new Result(plus, profit));
        }

        Result result = results.poll();
        return new int[] {result.plus, result.profit};
    }

    private void combination(List<Integer> list, int r) {
        if (r == 0) {
            discounts.add(new ArrayList<>(list));
            return;
        }

        for (int discountRate : discountRates) {
            list.add(discountRate);
            combination(list, r - 1);
            list.remove(list.size() - 1);
        }
    }
}
