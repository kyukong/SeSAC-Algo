package week5.sol43165;

public class 김유빈_타겟넘버 {

    private int[] numbers;
    private int target;
    private int result;

    public static void main(String[] args) {
        김유빈_타겟넘버 solution = new 김유빈_타겟넘버();

        System.out.println(solution.solution(new int[] {1, 1, 1, 1, 1}, 3));
        System.out.println(solution.solution(new int[] {4, 1, 2, 1}, 4));
    }

    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        this.result = 0;

        dfs(0, 0);

        return result;
    }

    private void dfs(int now, int i) {

        if (i >= numbers.length) {
            if (now == target) {
                result++;
            }
            return;
        }

        dfs(now + numbers[i], i + 1);
        dfs(now - numbers[i], i + 1);
    }
}
