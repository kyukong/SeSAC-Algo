public class 이상민_타켓넘버 {

    static int answer = 0;
    static int target1 = 0;

    public int solution(int[] numbers, int target) {
        target1 = target;
        find(numbers, 0, 0);
        return answer;
    }

    static void find(int[] numbers, int index, int result){
        if(index == numbers.length){
            if(result == target1){
                answer++;
            }
            return;
        }

        find(numbers, index+1, numbers[index] + result);
        find(numbers, index+1, -numbers[index] + result);
    }
}
