import java.util.Arrays;

public static String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        char[] numberArray = number.toCharArray();

        char firstDigit = numberArray[0];
        int firstPos = 0;

        for (int x = 1; x < numberArray.length - 1; x++) {
            if (numberArray[x] > numberArray[x - 1] && numberArray[x] >= numberArray[x + 1]) {
                firstDigit = (char) Math.max(firstDigit, numberArray[x]);
                firstPos = x;
                k = k - firstPos;
                break;
            }
        }

        char[] newNumber = new char[numberArray.length - firstPos];

        for (int i = 0; i < newNumber.length; i++) {
            newNumber[i] = numberArray[firstPos + i];
        }

        for (int x = 1; x < newNumber.length - 2; x++) {
            if (newNumber[x] < newNumber[x - 1] && newNumber[x] < newNumber[x + 1]) {
                for (int j = x; j < newNumber.length - 1; j++) {
                    newNumber[j] = newNumber[j + 1];
                }
                k--;
            }

            if (k == 0) {
                break;
            }
        }

        if (k != 0) {
            Arrays.sort(newNumber);
            char[] temp = Arrays.copyOf(newNumber, newNumber.length - k);
            newNumber = temp;
        }

        for (char x : newNumber) {
            answer.append(x);
        }

        return answer.toString();
    }
