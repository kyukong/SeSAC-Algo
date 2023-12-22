import java.io.*;
import java.util.*;
import java.lang.*;
class Solution {
    public static class Point{
        int x;
        int y;
        public Point(){}
        public Point(int x, int y){
            this.x = x;
            this.y =y ;
        }
        
        public String toString(){
            return "[ "+ this.x+ ","+ this.y+" ]";
        }
    }
    static int T;
    static ArrayList<Point> list;
    static boolean [] visited;
    static int[] answer;
    public int[] solution(int brown, int yellow) {
        if(yellow == 1){
            answer = new int[2];
            answer[0]=3;
            answer[1]=3;
            return answer;
        }
        list = new ArrayList<>();
        answer = new int[2];
        T = yellow;
        visited = new boolean[T+1];
        for(int i=1;i<=T;i++){
            if(visited[i]) continue;
            if(T%i==0){
                list.add(new Point(i,T/i));
                visited[i]=true;
                visited[T/i]= true;
            }
        }
        for(Point p : list){
            int max = p.x >=p.y ? p.x : p.y;
            int min = p.x <p.y? p.x : p.y;
            int M= max;
            int N = min;
            if( ((M+N)*2) +4  == brown){
                answer[0]= M+2;
                answer[1]=N+2; break;
            }
        }
        return answer;
    }
}
