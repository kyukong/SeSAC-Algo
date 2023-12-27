package algo;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class 김동욱_2048 {
	static int N;
	static int[][] board;
	static int answer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		board = new int[N][N];
		answer= 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				board[i][j]= sc.nextInt();
			}
		} // end input
		
		// simulation
		dfs(0,board);
		
		System.out.println(answer);
		
	}
	public static void dfs(int count, int[][] board) {
		if(count==5) {
			check(board);
			return;
		}
		// 백업
		int[][] copy = copy(board);
		int[][] temp = new int[N][N];
		
		temp = up(copy);
		dfs(count+1, temp);
		temp = down(copy);
		dfs(count+1, temp);
		temp = left(copy);
		dfs(count+1, temp);
		temp= right(copy);
		dfs(count+1,temp);
		
	}
	public static void check(int[][]board) {
		int m = -1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				m= Math.max(m, board[i][j]);
			}
		}
		answer = Math.max(answer, m);
	}
	public static void print(int[][] board) {
		for(int i=0;i<N;i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println("=========");
	}
	public static int[][] copy(int[][] board){
		int[][] copy = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				copy[i][j] = board[i][j];
			}
		}
		return copy;
	}
	public static int[][] up(int[][] board) {
		int[][] returnBoard = new int[N][N];
		// 큐에 넣어줌
		Deque<Integer> deque = new ArrayDeque<>();
		Deque<Integer> newQueue = new ArrayDeque<>();
		for(int j = 0;j<N;j++) {
			deque = new ArrayDeque<>();
			newQueue = new ArrayDeque<>();
			for(int i =0;i<N;i++) {
				int temp = board[i][j];
				if(temp==0) continue;
				deque.addLast(temp);
			}
			while(!deque.isEmpty()) {
				int curNum = deque.poll();
				if(deque.isEmpty()) {
					newQueue.addLast(curNum);
					break;
				} 
				if(curNum == deque.peekFirst()) {
					deque.poll();
					newQueue.addLast(curNum*2);
					continue;
				}
				newQueue.addLast(curNum);
			}
			// newQueue
			if(newQueue.size()<N) {
				while(newQueue.size()<N) {
					newQueue.addLast(0);
				}
			}
			for(int i=0;i<N;i++) {
				returnBoard[i][j]= newQueue.pollFirst();
			}
			
		}
		return returnBoard;
	}
	public static int[][] down(int[][] board) {
		int[][] returnBoard = new int[N][N];
		// 큐에 넣어줌
		Deque<Integer> deque = new ArrayDeque<>();
		Deque<Integer> newQueue = new ArrayDeque<>();
		for(int j = 0;j<N;j++) {
			deque = new ArrayDeque<>();
			newQueue = new ArrayDeque<>();
			for(int i =0;i<N;i++) {
				int temp = board[i][j];
				if(temp==0) continue;
				deque.addFirst(temp);
			}
			
			while(!deque.isEmpty()) {
				int curNum = deque.poll();
				if(deque.isEmpty()) {
					newQueue.addLast(curNum);
					break;
				} 
				if(curNum == deque.peekFirst()) {
					deque.poll();
					newQueue.addLast(curNum*2);
					continue;
				}
				newQueue.addLast(curNum);
			}
			// newQueue
			if(newQueue.size()<N) {
				while(newQueue.size()<N) {
					newQueue.addLast(0);
				}
			}
			//System.out.println(newQueue);
			for(int i=0;i<N;i++) {
				returnBoard[i][j]= newQueue.pollLast();
			}
			
		}
		return returnBoard;
	}
	public static int[][] left(int[][] board) {
		int[][] returnBoard = new int[N][N];
		// 큐에 넣어줌
		Deque<Integer> deque = new ArrayDeque<>();
		Deque<Integer> newQueue = new ArrayDeque<>();
		for(int i = 0;i<N;i++) {
			deque = new ArrayDeque<>();
			newQueue = new ArrayDeque<>();
			for(int j =0;j<N;j++) {
				int temp = board[i][j];
				if(temp==0) continue;
				deque.addLast(temp);
			}
			while(!deque.isEmpty()) {
				int curNum = deque.poll();
				if(deque.isEmpty()) {
					newQueue.addLast(curNum);
					break;
				} 
				if(curNum == deque.peekFirst()) {
					deque.poll();
					newQueue.addLast(curNum*2);
					continue;
				}
				newQueue.addLast(curNum);
			}
			// newQueue
			if(newQueue.size()<N) {
				while(newQueue.size()<N) {
					newQueue.addLast(0);
				}
			}
			for(int j=0;j<N;j++) {
				returnBoard[i][j]= newQueue.pollFirst();
			}
			
		}
		return returnBoard;
	}
	public static int[][] right(int[][] board) {
		int[][] returnBoard = new int[N][N];
		// 큐에 넣어줌
		Deque<Integer> deque = new ArrayDeque<>();
		Deque<Integer> newQueue = new ArrayDeque<>();
		for(int i = 0;i<N;i++) {
			deque = new ArrayDeque<>();
			newQueue = new ArrayDeque<>();
			for(int j =0;j<N;j++) {
				int temp = board[i][j];
				if(temp==0) continue;
				deque.addFirst(temp);
			}
			
			while(!deque.isEmpty()) {
				int curNum = deque.poll();
				if(deque.isEmpty()) {
					newQueue.addLast(curNum);
					break;
				} 
				if(curNum == deque.peekFirst()) {
					deque.poll();
					newQueue.addLast(curNum*2);
					continue;
				}
				newQueue.addLast(curNum);
			}
			// newQueue
			if(newQueue.size()<N) {
				while(newQueue.size()<N) {
					newQueue.addLast(0);
				}
			}
			//System.out.println(newQueue);
			for(int j=0;j<N;j++) {
				returnBoard[i][j]= newQueue.pollLast();
			}
			
		}
		return returnBoard;
	}
	
}
