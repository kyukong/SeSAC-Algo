import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 빈 공간과 벽
 * - 불이 동, 서, 남, 북 으로 퍼져나감 (벽에는 불이 붙지 않음)
 * - 불이 옮겨진 칸 or 불이 이제 붙으려는 칸으로 이동 X
 *
 * . : 빈공간
 * # : 벽
 * @ : 상근이의 시작 위치
 * * : 불
 *
 * 출력 : 각 테스트 케이스마다 빌딩을 탈출하는데 가장 빠른 시간 출력
 *
 * - 불이 한번에 이동이 되어야 함
 * */
public class 이상민_불 { // https://www.acmicpc.net/problem/5427

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int w,h;
    static String[][] graph;
    static boolean[][] visited;
    static int[][] result;
    static Queue<int[]> fire;
    static Queue<int[]> sang;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(testCase-->0){
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken()); // 너비
            h = Integer.parseInt(st.nextToken()); // 높이

            graph = new String[h][w];
            visited = new boolean[h][w];
            result = new int[h][w];
            fire = new LinkedList<>();

            re = 1_000_000;

            int startX = 0;
            int startY = 0;

            for(int i=0; i<h; i++){
                char[] str = br.readLine().toCharArray();
                for(int j=0; j<w; j++){
                    graph[i][j] = String.valueOf(str[j]);
                    if(graph[i][j].equals("@")){
                        startX = i;
                        startY = j;
                    }else if(graph[i][j].equals("*")){
                        fire.add(new int[]{i,j});
                        visited[i][j] = true;
                    }else if(graph[i][j].equals("#")){
                        visited[i][j] = true;
                    }
                }
            }

            bfs(startX,startY);

            // 테두리에 0이 아닌 값이 존재할 때, 가장 작은 값에 +1 을 하는 것이 정답
            // 0이라면 IMPOSSIBLE
//            for(int i=0; i<h; i++){
//                for(int j=0; j<w; j++){
//                    System.out.print(result[i][j]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();

            if(re == 1_000_000){
                sb.append("IMPOSSIBLE").append("\n");
            }else {
                sb.append(re+1).append("\n");
            }
        }
        System.out.println(sb);
    }

    static int re;

    private static void bfs(int startX, int startY) {
        sang = new LinkedList<>();
        sang.add(new int[]{startX, startY});

        while (!fire.isEmpty()){
            // 불 이동
            int fireSize = fire.size();
            for(int i=0; i<fireSize; i++){
                int[] getFire = fire.poll();
                for(int a=0; a<4; a++) {
                    int getX = getFire[0] + dx[a];
                    int getY = getFire[1] + dy[a];
                    if(0<=getX && getX<h && 0<=getY && getY<w){
                        if(!visited[getX][getY] && graph[getX][getY].equals(".")){
                            graph[getX][getY] = "*";
                            visited[getX][getY] = true;
                            fire.add(new int[]{getX,getY});
                        }
                    }
                }
            }

            int sangSize = sang.size();
            for(int a=0; a<sangSize; a++){
                int[] temp = sang.poll();
                int x = temp[0];
                int y = temp[1];

                for(int i=0; i<4; i++){
                    int getX = x + dx[i];
                    int getY = y + dy[i];

                    if(0<=getX && getX<h && 0<=getY && getY<w){
                        if(!visited[getX][getY] && graph[getX][getY].equals(".")){
                            result[getX][getY] = result[x][y] + 1;
                            sang.add(new int[]{getX,getY});

                            if(( getX == 0 || getX == h-1 ) && 0<=getY && getY<w){
                                if(result[getX][getY] != 0) re = Math.min(re, result[getX][getY]);
                            }else if((getY == 0 || getY == w-1 ) && 0<=getX && getX<h){
                                if(result[getX][getY] != 0) re = Math.min(re, result[getX][getY]);
                            }
                        }
                    }
                }
            }

        }
    }
}
