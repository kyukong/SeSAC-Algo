import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 김유빈_틱택토 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String line;
        String[] commands;
        String[][] board = new String[3][3];

        while (true) {

            line = br.readLine();
            if (line.equals("end")) {
                break;
            }

            commands = line.split("");

            int x, y;
            int count = 0;
            int countX = 0, countO = 0, countEmpty = 0;
            for (String command : commands) {
                x = count % 3;
                y = count / 3;
                if (command.equalsIgnoreCase("x")) {
                    countX++;
                    board[y][x] = "x";
                } else if (command.equalsIgnoreCase("o")) {
                    countO++;
                    board[y][x] = "o";
                } else {
                    countEmpty++;
                    board[y][x] = ".";
                }
                count++;
            }

            // x 가 이긴 경우
            if (countX == countO + 1 && isBingo(board, "x") > 0 && isBingo(board, "o") == 0) {
                sb.append("valid").append("\n");
                continue;
            }

            // o 가 이긴 경우
            if (countX == countO && isBingo(board, "o") > 0 && isBingo(board, "x") == 0) {
                sb.append("valid").append("\n");
                continue;
            }

            // 게임이 종료된 경우
            if (countX == 5 && countO == 4 && isBingo(board, "x") == 0 && isBingo(board, "o") == 0) {
                sb.append("valid").append("\n");
                continue;
            }

            sb.append("invalid").append("\n");
        }
        System.out.println(sb);
    }

    private static int isBingo(String[][] board, String turn) {
        int count = 0;
        String first, second, third;

        // 가로세로 빙고 확인
        for (int i = 0; i < 3; i++) {
            first = board[i][0];
            second = board[i][1];
            third = board[i][2];

            if (first.equals(turn) && first.equals(second) && second.equals(third)) {
                count++;
            }

            first = board[0][i];
            second = board[1][i];
            third = board[2][i];

            if (first.equals(turn) && first.equals(second) && second.equals(third)) {
                count++;
            }
        }

        // 대각선 빙고 확인 (왼쪽에서 오른쪽)
        first = board[0][0];
        second = board[1][1];
        third = board[2][2];

        if (first.equals(turn) && first.equals(second) && second.equals(third)) {
            count++;
        }

        // 대각선 빙고 확인 (오른쪽에서 왼쪽)
        first = board[0][2];
        second = board[1][1];
        third = board[2][0];

        if (first.equals(turn) && first.equals(second) && second.equals(third)) {
            count++;
        }

        return count;
    }
}
