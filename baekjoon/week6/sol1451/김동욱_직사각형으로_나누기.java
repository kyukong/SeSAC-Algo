package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김동욱_직사각형으로_나누기 {
	static int N, M;
	static int answer;
	static int[][] boardOne;
	static int[][] boardTwo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		boardOne = new int[N][M];
		boardTwo = new int[M][N];
		answer = 0;
		for (int i = 0; i < N; i++) {
			String[] temp = br.readLine().split("");
			for (int j = 0; j < temp.length; j++) {
				int num = Integer.parseInt(temp[j]);
				boardOne[i][j] = num;
				boardTwo[j][i] = num;

			}
		} // end input
		first();
		second();
		third();
		fourth();
		fifth();
		sixth();

		System.out.println(answer);
	}

	
	public static int sum(int start, int end, int[][] board) {

		int result = 0;
		
		for (int i = start; i < end; i++) {
			for (int j = 0; j < board[i].length; j++) {
				result += board[i][j];
			}
		}

		return result;
	}
	
	

	public static void first() {

		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N - 1; j++) {

				answer = Math.max(answer, (sum(0, i, boardOne) * sum(i, j, boardOne) * sum(j, N, boardOne)));
			}
		}
	}

	public static void second() {

		for (int i = 0; i < M - 1; i++) {
			for (int j = i + 1; j < M - 1; j++) {

				answer = Math.max(answer, (sum(0, i, boardTwo) * sum(i, j, boardTwo) * sum(j, M, boardTwo)));
			}
		}
	}

	public static void third() {

		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M; j++) {
				int sum1 = 0;
				int sum2 = 0;

				for (int k = 0; k <= i; k++) {
					for (int l = 0; l < j; l++) {
						sum1 += boardOne[k][l];
					}

					for (int l = j; l < M; l++) {
						sum2 += boardOne[k][l];
					}
				}
				if (sum1 == 0 || sum2 == 0)
					continue;
				answer = Math.max(answer, sum1 * sum2 * sum(i, N, boardOne));

			}
		}
	}

	public static void fourth() {

		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M; j++) {
				int sum1 = 0;
				int sum2 = 0;

				for (int k = i + 1; k < N ; k++) {
					for (int l = 0; l < j; l++) {
						sum1 += boardOne[k][l];
					}

					for (int l = j; l < M; l++) {
						sum2 += boardOne[k][l];
					}
				}
				if (sum1 == 0 || sum2 == 0)
					continue;

				answer = Math.max(answer, sum1 * sum2 * sum(0, i, boardOne));

			}
		}
	}

	public static void fifth() {

		for (int i = 0; i < M - 1; i++) {
			for (int j = 0; j < N; j++) {
				int sum1 = 0;
				int sum2 = 0;

				for (int k = 0; k <= i; k++) {
					for (int l = 0; l < j; l++) {
						sum1 += boardTwo[k][l];
					}

					for (int l = j; l < N; l++) {
						sum2 += boardTwo[k][l];
					}
				}
				if (sum1 == 0 || sum2 == 0)
					continue;
				answer = Math.max(answer, sum1 * sum2 * sum(i, M, boardTwo));

			}
		}
	}

	public static void sixth() {

		for (int i = 0; i < M - 1; i++) {
			for (int j = 0; j < N; j++) {
				int sum1 = 0;
				int sum2 = 0;

				for (int k = i + 1; k < M; k++) {
					for (int l = 0; l < j; l++) {
						sum1 += boardTwo[k][l];
					}

					for (int l = j; l < N; l++) {
						sum2 += boardTwo[k][l];
					}
				}
				if (sum1 == 0 || sum2 == 0)
					continue;
				answer = Math.max(answer, sum1 * sum2 * sum(0, i, boardTwo));

			}
		}
	}
}
