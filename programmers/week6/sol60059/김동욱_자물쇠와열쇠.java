import java.util.*;
import java.lang.*;
import java.io.*;
class Solution {
    static int[][] board;
    static int N,M;
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        N = lock.length;
        M = key.length;
        board = new int[N+(M*2)-2][N+(M*2)-2];
        for(int i=0 ; i<N;i++){
            for(int j=0;j<N;j++){
                board[i+(M-1)][j+(M-1)] = lock[i][j];
            }
        }
        // key 도입하면서 4번 spin 후 이동
        for(int i=0;i<M+N-1;i++){
            for(int j=0;j<M+N-1;j++){
                int[][] copyKey = key;
                
                for(int d=0;d<4;d++){
                    int[][] copyBoard = copy(board);
                    copyKey = spin(copyKey);
                    copyBoard = checkKey(copyBoard,copyKey,i,j);
                   // print(copyBoard);
                    if(isFit(copyBoard)) {
                        return true;
                    }
                }
            }
        }
        return answer;
    }
    public static int[][] checkKey(int[][] copyBoard, int[][] copyKey,int x , int y){
        int xInd = 0;
        int yInd =0;
        for(int i = x;i<x+M;i++ ){
            yInd =0;
            for(int j = y ; j<y+M;j++){
               // System.out.println(xInd+" "+yInd);
                copyBoard[i][j] +=copyKey[xInd][yInd++];
                
            }
            xInd++;
        }
        return copyBoard;
    }
    public static int[][] copy(int[][] board){
        int[][] copy = new int[N+(M*2)-2][N+(M*2)-2];
        for(int i=0; i<board.length;i++){
            for(int j=0;j<board.length;j++){
                copy[i][j]= board[i][j];
            }
        }
        return copy;
    }
    public static int[][] spin(int[][] board){
        int[][] returnBoard = new int[M][M];
        for(int j=0;j<M;j++){
    
            Deque<Integer> deque = new ArrayDeque<>();
            for(int i =0;i<M;i++){
                deque.add(board[i][j]);
            }
            
            int ind = M-1;
            while(!deque.isEmpty()){
                returnBoard[j][ind--] = deque.poll();
            }
        }
        return returnBoard;
    }
    
    
    public static void print(int[][] board){
        for(int i=0;i<board.length;i++){
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println();
    }
    public static boolean isFit(int[][] board){
        
        for(int i=M-1; i<M+N-1;i++){
            for(int j=M-1;j<M+N-1;j++){
                if(board[i][j]!=1) return false;
                
            }
        }
        return true;
    }
    
}
