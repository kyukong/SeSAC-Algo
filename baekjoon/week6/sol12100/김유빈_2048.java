package week6.sol12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Consumer;

public class 김유빈_2048 {

    private static int n;
    private static int[][] board;
    private static int result = 0;

    enum Orders {

        LEFT(board -> { moveLeft(board);mergeLeft(board);moveLeft(board); }),
        RIGHT(board -> { moveRight(board);mergeRight(board);moveRight(board); }),
        UP(board -> { moveUp(board);mergeUp(board);moveUp(board); }),
        DOWN(board -> { moveDown(board);mergeDown(board);moveDown(board); })
        ;

        private final Consumer<int[][]> move;

        Orders(Consumer<int[][]> move) {
            this.move = move;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, board);

        System.out.println(result);
    }

    private static void dfs(int count, int[][] board) {
        if (count >= 5) {
            find(board);
            return;
        }

        int[][] copied;
        for (Orders orders : Orders.values()) {
            copied = copy(board);
            orders.move.accept(copied);
            dfs(count + 1, copied);
        }
    }

    private static void find(int[][] board) {
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                result = Math.max(result, board[y][x]);
            }
        }
    }

    private static int[][] copy(int[][] origin) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            copy[i] = origin[i].clone();
        }
        return copy;
    }

    private static void moveLeft(int[][] board) {
        List<Integer> numbers;
        for (int y = 0; y < n; y++) {
            numbers = new ArrayList<>();
            for (int x = 0; x < n; x++) {
                if (board[y][x] != 0) {
                    numbers.add(board[y][x]);
                }
                board[y][x] = 0;
            }
            for (int i = 0; i < numbers.size(); i++) {
                board[y][i] = numbers.get(i);
            }
        }
    }

    private static void moveRight(int[][] board) {
        List<Integer> numbers;
        for (int y = 0; y < n; y++) {
            numbers = new ArrayList<>();
            for (int x = n - 1; x >= 0; x--) {
                if (board[y][x] != 0) {
                    numbers.add(board[y][x]);
                }
                board[y][x] = 0;
            }
            for (int i = 0; i < numbers.size(); i++) {
                board[y][n - 1 - i] = numbers.get(i);
            }
        }
    }

    private static void moveUp(int[][] board) {
        List<Integer> numbers;
        for (int x = 0; x < n; x++) {
            numbers = new ArrayList<>();
            for (int y = 0; y < n; y++) {
                if (board[y][x] != 0) {
                    numbers.add(board[y][x]);
                }
                board[y][x] = 0;
            }
            for (int i = 0; i < numbers.size(); i++) {
                board[i][x] = numbers.get(i);
            }
        }
    }

    private static void moveDown(int[][] board) {
        List<Integer> numbers;
        for (int x = 0; x < n; x++) {
            numbers = new ArrayList<>();
            for (int y = n - 1; y >= 0; y--) {
                if (board[y][x] != 0) {
                    numbers.add(board[y][x]);
                }
                board[y][x] = 0;
            }
            for (int i = 0; i < numbers.size(); i++) {
                board[n - 1 - i][x] = numbers.get(i);
            }
        }
    }

    private static void mergeLeft(int[][] board) {
        for (int y = 0; y < n; y++) {
            int index = 0;
            while (index + 1 < n) {
                if (board[y][index] != board[y][index + 1]) {
                    index++;
                    continue;
                }
                board[y][index] *= 2;
                board[y][index + 1] = 0;

                index += 2;
            }
        }
    }

    private static void mergeRight(int[][] board) {
        for (int y = 0; y < n; y++) {
            int index = n - 1;
            while (index - 1 >= 0) {
                if (board[y][index] != board[y][index - 1]) {
                    index--;
                    continue;
                }
                board[y][index] *= 2;
                board[y][index - 1] = 0;

                index -= 2;
            }
        }
    }

    private static void mergeUp(int[][] board) {
        for (int x = 0; x < n; x++) {
            int index = 0;
            while (index + 1 < n) {
                if (board[index][x] != board[index + 1][x]) {
                    index++;
                    continue;
                }
                board[index][x] *= 2;
                board[index + 1][x] = 0;

                index += 2;
            }
        }
    }

    private static void mergeDown(int[][] board) {
        for (int x = 0; x < n; x++) {
            int index = n - 1;
            while (index - 1 >= 0) {
                if (board[index][x] != board[index - 1][x]) {
                    index--;
                    continue;
                }
                board[index][x] *= 2;
                board[index - 1][x] = 0;

                index -= 2;
            }
        }
    }
}
