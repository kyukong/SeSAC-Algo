import java.util.*;
import java.io.*;
import java.lang.*;

class Solution {
    public static class Point{
        int x;
        int y;
        public Point(){}
        public Point(int x, int y){
            this.x =x;
            this.y =y;
        }
        @Override
        public String toString(){
            return " [" + this.x +","+this.y+"]";
        }
    }
    
    
    static int[] dx={-1,1,0,0};
    static int[] dy= {0,0,-1,1};
    static int[][] board;
    static int[][] mark;
    static boolean[][] visited;
    static int answer;
    static int N,M;
    public int solution(int[][] maps) {
        answer = 0;
        board = maps;
        
        N = maps.length;
        M = maps[0].length;
        visited = new boolean[N][M];
        mark = new int[N][M];
        //System.out.println(N+" "+ M);
        bfs(new Point(0,0));
        if(mark[N-1][M-1]==0)return -1;
        answer = mark[N-1][M-1];
        return answer;
    }
    public static boolean isValid(int x, int y){
        if(x<0 || x>=N || y<0 || y>=M) return false;
        if(visited[x][y]) return false;
        return true;
    }
    public static void print(int[][] board){
        for(int i=0;i<N;i++){
            System.out.println(Arrays.toString(board[i]));
        }
    }
    public static void bfs(Point p){
        boolean flag = false;
        Queue<Point> queue  = new LinkedList<>();
        visited[p.x][p.y]=true;
        queue.add(p);
        int t =1;
        while(!queue.isEmpty()){
            int size= queue.size();
           // System.out.println(queue);
            for(int s=0;s<size;s++){
            Point tempPoint = queue.poll();
            mark[tempPoint.x][tempPoint.y] = t;
            if(tempPoint.x == N-1 && tempPoint.y == M-1) {
                flag =true;
                break;
            }
                for(int d=0;d<4;d++){
                    int nx = tempPoint.x+dx[d];
                    int ny = tempPoint.y+dy[d];
                    if(isValid(nx,ny)){
                        if(board[nx][ny]==1)
                        {
                        queue.add(new Point(nx,ny));
                        visited[nx][ny]=true;
                       
                        }
                    
                    }
                }
            }
            ++t;
        }
        
    }
}
